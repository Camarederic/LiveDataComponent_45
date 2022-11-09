package com.example.livedatacomponent_45

import androidx.lifecycle.LiveData

class MyLiveData : LiveData<String>() {

    fun setValueToLiveData(string: String) {
        value = string
    }

    override fun onActive() {
        super.onActive()
        println("onActive")
    }

    override fun onInactive() {
        super.onInactive()
        println("onInactive")
    }
}