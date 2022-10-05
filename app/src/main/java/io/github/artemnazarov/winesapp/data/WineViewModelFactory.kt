package io.github.artemnazarov.winesapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WineViewModelFactory(private val repository: WineRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(WineViewModel::class.java))
            WineViewModel(repository) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}