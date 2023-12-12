package com.capstone.beruang.ui.management.allocation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.capstone.beruang.R
import com.capstone.beruang.databinding.ActivityDetailAllocationBinding
import com.capstone.beruang.databinding.ActivityEditBinding
import com.capstone.beruang.ui.management.CalendarDateModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DetailAllocationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAllocationBinding
    private val sdf = SimpleDateFormat("MMMM yyyy", Locale.ENGLISH)
    private val cal = Calendar.getInstance(Locale.ENGLISH)
    private val currentDate = Calendar.getInstance(Locale.ENGLISH)
    private val dates = ArrayList<Date>()
    /*private lateinit var adapter: CalendarAdapter*/
    private val calendarList2 = ArrayList<CalendarDateModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAllocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAction()
        setUpClickListener()
        setUpCalendar()
        renderLineChart()
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

    }
}