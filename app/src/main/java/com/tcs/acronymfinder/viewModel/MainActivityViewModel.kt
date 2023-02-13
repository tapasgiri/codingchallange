package com.tcs.acronymfinder.viewModel

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.acronymfinder.model.Lfs
import com.tcs.acronymfinder.model.RecyclerList
import com.tcs.acronymfinder.network.RetroInstance
import com.tcs.acronymfinder.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
     var recyclerListLiveData:MutableLiveData<RecyclerList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun getRecyclerListObserver():MutableLiveData<RecyclerList>{
        return recyclerListLiveData
    }

    fun makeApiCall(text: String) {
        viewModelScope.launch(Dispatchers.IO){
             val retroInstance = RetroInstance.getRetroInstance().
                                 create(RetroService::class.java)
             val response = retroInstance.getData(text)
             Log.d("api response :",response.toString())
             if(response.size>0)
              recyclerListLiveData.postValue(response[0])
        }
    }
}

