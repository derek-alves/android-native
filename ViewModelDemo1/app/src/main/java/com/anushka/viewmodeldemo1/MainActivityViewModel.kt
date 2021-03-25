package com.anushka.viewmodeldemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    private var count = MutableLiveData<Int>(0)

    val countData: LiveData<Int>
        get() = count

    fun getUpdatedCount() {
        count.value = (count.value)?.plus(1)
    }
}