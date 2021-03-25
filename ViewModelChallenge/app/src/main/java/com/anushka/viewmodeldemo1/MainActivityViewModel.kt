package com.anushka.viewmodeldemo1

import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private var total = 0

    fun getTotal():Int{
        return total
    }

    fun setTotal(value:Int){
        total += value
    }
}