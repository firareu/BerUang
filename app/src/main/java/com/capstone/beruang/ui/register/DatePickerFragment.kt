package com.capstone.beruang.ui.register

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class DatePickerFragment(private val ed_dob: TextInputEditText) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    private lateinit var datePickerListener: DatePickerListener

    fun setDatePickerListener(listener: DatePickerListener) {
        datePickerListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = "$year-${month + 1}-$dayOfMonth"
        datePickerListener.onDateSet(selectedDate)
        ed_dob.setText(selectedDate)
    }
}
