package com.tcs.acronymfinder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcs.acronymfinder.model.Lfs
import com.tcs.acronymfinder.model.RecyclerList
import com.tcs.acronymfinder.repository.AcronymRepository
import com.tcs.acronymfinder.util.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AcronymRepository): ViewModel() {
    private val _items = MutableLiveData<NetworkResult<List<RecyclerList>>>()
    val items: LiveData<NetworkResult<List<RecyclerList>>>
        get() = _items

    fun getItems(newText: String) {
        viewModelScope.launch {
            val result = repository.getData(newText)
            _items.postValue(result)
        }
    }
}

