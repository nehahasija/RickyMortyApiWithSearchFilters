package com.rickymorty.androiddemo.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rickymorty.androiddemo.api.Repository

class SharedViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     return SharedViewModel(repository) as T
    }
}