package com.capstone.beruang.ui.management.allocation.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.R
import com.capstone.beruang.data.repository.PreferenceManager
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.data.response.allocation.AllocationItem
import com.capstone.beruang.data.response.outcome.OutcomeItem
import com.capstone.beruang.data.retrofit.ApiConfig
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.databinding.ActivityDetailAllocationBinding
import com.capstone.beruang.ui.management.CalendarDateModel
import com.capstone.beruang.ui.management.allocation.add.AddOutcomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DetailAllocationActivity : AppCompatActivity(), DetailAllocationAdapter.OnItemClickCallback {
    private lateinit var binding: ActivityDetailAllocationBinding
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)
    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val dates = ArrayList<Date>()
    private lateinit var apiService: ApiService
    private lateinit var viewModel: DetailAllocationViewModel
    private lateinit var adapter: DetailAllocationAdapter
    /*private lateinit var adapter: CalendarAdapter*/
    private val calendarList2 = ArrayList<CalendarDateModel>()
    private val userRepository: UserRepository by lazy {
        UserRepository(PreferenceManager.getInstance(this))
    }
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAllocationBinding.inflate(layoutInflater)
        userId = userRepository.getUserId().toString()
        apiService = ApiConfig.getApiService() // Assuming ApiConfig.getApiService() is your way of getting ApiService instance
        viewModel = ViewModelProvider(this).get(DetailAllocationViewModel::class.java)
        viewModel.apiService = apiService // Set the apiService here

        val data: AllocationItem? = intent.getParcelableExtra(KEY_CONTENT)
        if (data != null) {
            val formattedPercent = String.format("%d %%", data.precentage?.toInt() ?: 0)
            binding.tvPercent.text = formattedPercent
            binding.tvAllocationtype.text = data.category

            val TVAmount = getString(R.string.rupiah, data.amount?.toInt() ?: 0)
            binding.tvRpallocationtype.text = TVAmount
            Log.d("DetailAllocationActivity", "Received data: $data")

            viewModel.fetchOutcomeData(data.category ?: "", userId)
            val totaldata = viewModel.fetchTotalOutcomeData(data.category ?: "", userId)
            Log.d("DetailAllocationActivity", "Received data: $data.category")

            viewModel.totalOutcomeData.observe(this, { totalOutcome ->
                val TVMoney = getString(R.string.rupiah, totalOutcome ?: 0)
                binding.tvRpoutcomenow.text = TVMoney
            })
        }

        adapter = DetailAllocationAdapter()
        binding.rvAllocation.layoutManager = LinearLayoutManager(this)
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = adapter

        setContentView(binding.root)
        setupAction()
        setUpClickListener()
        setUpCalendar()
        renderLineChart()

        setUpRecyclerView()
        loadDataFromApi(userId)
        viewModel.outcomeData.observe(this, { outcomeList ->
            adapter.submitList(outcomeList)
        })
        setMoneyData()

    }

    private fun loadDataFromApi(userId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getOutcome(userId)
                val allocations = response.outcome.orEmpty().filterNotNull() // Menghapus nilai null dari list
                    // Hapus pemanggilan adapter.submitList(allocations) dari sini

            } catch (e: Exception) {
                Log.e("EditActivity", "Error: ${e.message}")
            }
        }
    }

    private fun setMoneyData() {
        val num: Int? = null
        //outcome
        val TVMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvRpoutcomenow.text = TVMoney


    }

    private fun setUpRecyclerView() {
        adapter = DetailAllocationAdapter()
        val recyclerView: RecyclerView = findViewById(R.id.rv_Allocation)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setOnItemClickCallback(this)
    }

    private fun setUpCalendar() {
        val calendarList = ArrayList<CalendarDateModel>()
        binding.tvDateMonth.text = sdf.format(cal.time)
        val monthCalendar = cal.clone() as Calendar
        val maxDaysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        dates.clear()
        monthCalendar.set(Calendar.DAY_OF_MONTH, 1)
        while (dates.size < maxDaysInMonth) {
            dates.add(monthCalendar.time)
            calendarList.add(CalendarDateModel(monthCalendar.time))
            monthCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        calendarList2.clear()
        calendarList2.addAll(calendarList)
//        adapter.setData(calendarList)
    }

    private fun setUpClickListener() {
        binding.ivCalendarNext.setOnClickListener {
            cal.add(Calendar.MONTH, 1)
            setUpCalendar()
        }
        binding.ivCalendarPrevious.setOnClickListener {
            cal.add(Calendar.MONTH, -1)
            if (cal == currentDate)
                setUpCalendar()
            else
                setUpCalendar()
        }
    }


    fun renderLineChart() {
        /*val csvDataset = Dataset(context = applicationContext)
        csvDataset.parseCSV()
        val entries = csvDataset.models.mapIndexedTo(java.util.ArrayList<Entry>()) { i, model -> Entry(model.price, i) }
        val dataset = LineDataSet(entries, "")
        dataset.setDrawCubic(true)
        dataset.setDrawFilled(true)
        dataset.setDrawHighlightIndicators(true)
        //
        dataset.lineWidth = 1.95f
        dataset.circleRadius = 5f
        dataset.color = Color.parseColor("#EFEFFF")
        dataset.setDrawCircleHole(false)
        dataset.setDrawCircles(false)
        dataset.highLightColor = Color.WHITE
        dataset.setDrawValues(false)

        val labels = csvDataset.models.map { it.monthYear }
        val data = LineData(labels, dataset)
        linechart.setDescription("Price Chart")
        linechart.data = data
        linechart.xAxis.setDrawGridLines(false)
        linechart.axisLeft.setDrawGridLines(false)
        linechart.xAxis.axisLineColor = resources.getColor(R.color.colorPrimaryLight)//top line
        linechart.xAxis.textColor = resources.getColor(R.color.colorPrimaryLight)
        linechart.axisLeft.axisLineColor = resources.getColor(R.color.colorPrimaryLight)//left line
        linechart.axisLeft.textColor = resources.getColor(R.color.colorPrimaryLight)
        linechart.axisRight.axisLineColor = resources.getColor(R.color.colorPrimaryLight)//right line
        linechart.axisRight.textColor = resources.getColor(R.color.colorPrimaryLight)
        linechart.setDrawBorders(false)
        linechart.setDrawGridBackground(false)
        linechart.setDescriptionColor(Color.WHITE)
        linechart.isAutoScaleMinMaxEnabled = false


        //Custom marker
        linechart.setDrawMarkerViews(true)
        val markerView = CustomMarkerView(applicationContext, R.layout.marker_view, csvDataset.models)
        linechart.markerView = markerView
        linechart.setTouchEnabled(true)

        // remove axis
        //val leftAxis = linechart.axisLeft
        //leftAxis.isEnabled = false
        //val rightAxis = linechart.axisRight
        //rightAxis.isEnabled = false

        //val xAxis = linechart.xAxis
        //xAxis.isEnabled = false

        // hide legend
        val legend = linechart.legend
        legend.isEnabled = false
        //linechart.setViewPortOffsets(0f, 0f, 0f, 0f) //remove padding
        linechart.invalidate()
        linechart.animateXY(300, 300)*/
    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            finish()
        }
        binding.tvAdd.setOnClickListener {
            val intentEdit = Intent(this, AddOutcomeActivity::class.java)
            startActivity(intentEdit)
        }
    }

    companion object {
        const val KEY_CONTENT = "content"
    }

    override fun onItemClicked(data: OutcomeItem) {
        val id = data.userId
//        deleteAllocationFromApi(id)
    }
}