package com.tcs.acronymfinder.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tcs.acronymfinder.repository.AcronymRepository

class MainViewModelFactory(private val repository: AcronymRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}