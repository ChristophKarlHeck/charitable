package de.hka.charitable.ui.historie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistorieViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is historie Fragment"
    }
    val text: LiveData<String> = _text
}