package com.timkwali.epicnotes.presentation.utils

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun showDatePicker(fragmentManager: FragmentManager, tag: String, textView: TextView) {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .build()
        picker.addOnPositiveButtonClickListener {
            val selectedDate = picker.selection?.toInt()
            val c = Calendar.getInstance()
            val today = SimpleDateFormat("dd-MM-yyyy")
                .parse("${c.get(Calendar.DAY_OF_MONTH)}-${c.get(Calendar.MONTH)}-${c.get(Calendar.YEAR)}")
                .time

            if (selectedDate != null) {
                if(selectedDate < today) {
                    textView.findFragment<Fragment>().showSnackBar("Please select a valid date!")
                } else {
                    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                    calendar.time = Date(it)
                    textView.text = "${calendar.get(Calendar.DAY_OF_MONTH)}-" +
                            "${calendar.get(Calendar.MONTH) + 1}-${calendar.get(Calendar.YEAR)}"
                }
            }
        }
        picker.show(fragmentManager, tag)
    }

    fun showTimePicker(fragmentManager: FragmentManager, tag: String, textView: TextView) {
        val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setTitleText("Select Appointment time")
                .build()
//        picker.addOnPositiveButtonClickListener { textView.text = picker.get }
        picker.show(fragmentManager, tag)
    }

    fun Fragment.showSnackBar(
        message: String,
        duration: Int = 3000,
        view: View = requireView()
    ) {
        Snackbar.make(view, message, duration).show()
    }
}