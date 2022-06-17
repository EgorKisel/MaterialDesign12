package com.example.materialdesign.utils

import com.example.materialdesign.R

class Parameters {
    var resetFragment: Boolean = false
    var theme: Int = R.style.Theme_MaterialDesign

    companion object{
        @Volatile
        private var INSTANCE: Parameters? = null
        fun getInstance(): Parameters{
            synchronized(this){
                var instance = INSTANCE
                if (instance==null){
                    instance = Parameters()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}