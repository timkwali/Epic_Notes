package com.timkwali.epicnotes.presentation.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

object BindingUtils {
    fun navigateToFragment(view: View, fragment: Int) {
        view.findNavController().navigate(fragment)
    }
}