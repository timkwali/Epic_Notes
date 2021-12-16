package com.timkwali.epicnotes.presentation.utils

import android.util.Log
import android.view.View
import android.widget.TextView
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
                .parse("${c.get(Calendar.DAY_OF_MONTH)}-${c.get(Calendar.MONTH)}-${c.get(Calendar.YEAR)}")
                .time

            if(selectedDate < today) {
                textView.findFragment<Fragment>().showSnackBar("Please select a valid date!")
            } else {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.time = Date(selectedDate)
                var day: String = calendar.get(Calendar.DAY_OF_MONTH).toString()
                day = if(day.toInt() < 10) "0$day" else day
                var month: String = calendar.get(Calendar.MONTH).toString()
                month = if(month.toInt() + 1 < 10) "0${month.toInt() + 1}" else ((month.toInt() + 1).toString())
                val year = calendar.get(Calendar.YEAR)
                textView.text = "$day-$month-$year"
            }
            Log.d("sdjkfa", "selected -> $selectedDate \ntoday-> $today")
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

    fun setUpDate(date: String): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT).parse(date);
        val day = date.substring(0, 2)
        var dayOfWeek = when (sdf.day) {
            0 -> "Sunday"
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            else -> ""
        }
        val month = when (date.substring(3, 5)) {
            "01" -> "January"
            "02" -> "February"
            "03" -> "March"
            "04" -> "April"
            "05" -> "May"
            "06" -> "June"
            "07" -> "July"
            "08" -> "August"
            "09" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> ""
        }
        val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        dayOfWeek = if(today == day.toInt()) "Today" else dayOfWeek
        return "$dayOfWeek $day, $month"
    }

    fun dateToTimeStamp(date: String): Long {
        return SimpleDateFormat("dd-MM-yyyy").parse(date).time
    }
}