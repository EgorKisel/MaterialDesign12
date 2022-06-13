package com.example.materialdesign.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.BuildConfig
import com.example.materialdesign.model.PictureOfTheDayRetrofitImpl
import com.example.materialdesign.model.PictureOfTheDayServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureOfTheDayViewModel(
    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofitImpl: PictureOfTheDayRetrofitImpl = PictureOfTheDayRetrofitImpl()
) : ViewModel() {
    fun getLiveDataForViewToObserve() = liveDataForViewToObserve
    fun sendServerRequest(){
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        retrofitImpl.getRetrofitImpl().getPictureOfTheDay(BuildConfig.NASA_API_KEY).enqueue(callback)
    }
    private val callback = object : Callback<PictureOfTheDayServerResponseData>{
        override fun onResponse(
            call: Call<PictureOfTheDayServerResponseData>,
            response: Response<PictureOfTheDayServerResponseData>
        ) {
            if (response.isSuccessful){
                response.body()?.let {
                    liveDataForViewToObserve.postValue(AppState.Success(it))
                }
            }else{
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveDataForViewToObserve.value =
                        AppState.Error(Throwable("Unidentified error"))
                } else {
                    liveDataForViewToObserve.value =
                        AppState.Error(Throwable(message))
                }
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayServerResponseData>, t: Throwable) {
            liveDataForViewToObserve.value = AppState.Error(t)
        }

    }
}