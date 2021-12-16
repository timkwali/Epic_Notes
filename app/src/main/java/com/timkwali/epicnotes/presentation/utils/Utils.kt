package com.timkwali.epicnotes.presentation.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun showDatePicker(fragmentManager: FragmentManager, tag: String, ) {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .build()
        picker.addOnPositiveButtonClickListener {  }
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