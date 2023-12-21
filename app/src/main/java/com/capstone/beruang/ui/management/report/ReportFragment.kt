package com.capstone.beruang.ui.management.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.beruang.R
import com.capstone.beruang.databinding.FragmentReportBinding
import com.capstone.beruang.ui.management.CalendarDateModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ReportFragment : Fragment() {
    private var _binding: FragmentReportBinding? = null
    private val binding get() = _binding!!
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)
    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val dates = ArrayList<Date>()
    /*private lateinit var adapter: CalendarAdapter*/
    private val calendarList2 = ArrayList<CalendarDateModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*val firstChartEntity = ChartEntity(Color.WHITE, graph1)
        val secondChartEntity = ChartEntity(Color.YELLOW, graph2)

        val list = ArrayList<ChartEntity>().apply {
            add(firstChartEntity)
            add(secondChartEntity)
        }

        val lineChart = root.findViewById<LineChart>(R.id.lineChart)
        lineChart.setLegend(legendArr)
        lineChart.setList(list)*/
        setMoneyData()
        setUpAdapter()
        setUpClickListener()
        setUpCalendar()
        renderLineChart()
    }

    /*private val firstChartEntity = floatArrayOf(113000f, 183000f, 188000f, 695000f, 324000f, 230000f, 188000f, 15000f, 126000f, 5000f, 33000f)
    private val secondChartEntity = floatArrayOf(0f, 245000f, 1011000f, 1000f, 0f, 0f, 47000f, 20000f, 12000f, 124400f, 160000f)
    private val legendArr = listOf("05/21", "05/22", "05/23", "05/24", "05/25", "05/26", "05/27", "05/28", "05/29", "05/30", "05/31")
*/
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

    private fun setUpAdapter() {
        /*val spacingInPixels = resources.getDimensionPixelSize(R.dimen.single_calendar_margin)
        binding.recyclerView.addItemDecoration(HorizontalItemDecoration(spacingInPixels))
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)
        adapter = CalendarAdapter { calendarDateModel: CalendarDateModel, position: Int ->
            calendarList2.forEachIndexed { index, calendarModel ->
                calendarModel.isSelected = index == position
            }
            adapter.setData(calendarList2)
        }
        binding.recyclerView.adapter = adapter*/
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

    private fun setMoneyData() {
        val num: Int? = null
        //outcome
        val TVMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvRpoutcomenow.text = TVMoney


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"

    }
}