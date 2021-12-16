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

    fun showDatePicker(fragmentManager: FragmentManager, tag: String, textView: TextView) {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .build()
        picker.addOnPositiveButtonClickListener {
            val selectedDate = it
            val c = Calendar.getInstance()
            val today = SimpleDateFormat("dd-MM-yyyy")
                .parse("${c.get(Calendar.DAY_OF_MONTH)}-${c.get(Calendar.MONTH + 1)}-${c.get(Calendar.YEAR)}")
                .time

//            if(selectedDate < today) {
//                textView.findFragment<Fragment>().showSnackBar("Please select a valid date!")
//            } else {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.time = Date(selectedDate)
                textView.text = "${calendar.get(Calendar.DAY_OF_MONTH)}-" +
                        "${calendar.get(Calendar.MONTH + 1)}-${calendar.get(Calendar.YEAR)}"
//            }
        }
        picker.show(fragmentManager, tag)
    }

    fun showTimePicker(fragmentManager: FragmentManager, tag: String, textView: TextView) {

        val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText("Select Appointment time")
                .build()
        picker.addOnPositiveButtonClickListener {
            var hour = ""
            var meridian = ""
            var minute = ""
            when {
                picker.hour > 12 -> {
                    meridian = "P.M."
                    hour = "${picker.hour - 12}"
                }
                picker.hour == 12 -> {
                    hour = "${picker.hour}"
                    meridian = "P.M."
                }
                else ->  {
                    meridian = "A.M."
                    hour = "${picker.hour}"
                    if(picker.hour < 10) hour = "0$hour"
                }
            }
            minute = if(picker.minute < 10) "0${picker.minute}" else "${picker.minute}"
            textView.text = "$hour:$minute $meridian"

        }
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