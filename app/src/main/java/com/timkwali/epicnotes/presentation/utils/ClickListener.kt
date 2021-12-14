package com.timkwali.epicnotes.presentation.utils

interface ClickListener<T> {
    fun onItemClick(item: T, position: Int)
}