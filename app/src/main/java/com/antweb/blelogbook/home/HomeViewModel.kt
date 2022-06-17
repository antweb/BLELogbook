package com.antweb.blelogbook.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {
    private val _title = MutableStateFlow("");

    val title: StateFlow<String> = _title

    fun setTitle(value: String) {
        _title.value = value
    }
}