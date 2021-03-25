package com.derek.databindtwoaway

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {
    var name = MutableLiveData<String>()

    init {
        name.value = "Derek"
    }
}