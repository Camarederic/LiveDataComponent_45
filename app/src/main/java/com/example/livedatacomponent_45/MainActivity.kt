package com.example.livedatacomponent_45

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var liveDataString = MutableLiveData<String>()
    lateinit var observer: Observer<String>

    private val myLiveData = MyLiveData()

    private var liveDataString2 = MutableLiveData<String>()
    private var liveDataInt = MutableLiveData<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        liveDataString2.value = "liveDataString"
        liveDataInt.value = 1

        test_text.text= liveDataString.value + " string"
        test_text1.text = liveDataInt.value.toString() + " int"


        button.setOnClickListener {
            liveDataString2 = Transformations.map(liveDataInt){
                it.toString()
            } as MutableLiveData<String>

            liveDataString.observe(this, Observer {
                test_text.text = it
            })
        }


        observer = Observer {
            test_text1.text = it
        }

        myLiveData.observe(this, Observer {

        })

//        button.setOnClickListener {
//            myLiveData.setValueToLiveData(edit_text1.text.toString())
//        }

        CoroutineScope(Dispatchers.IO).launch {
            liveDataString.postValue("Hello live data")
        }

    }

    override fun onStart() {
        super.onStart()
        myLiveData.observe(this, observer)
    }

    override fun onStop() {
        super.onStop()
        myLiveData.removeObserver(observer)
    }
}