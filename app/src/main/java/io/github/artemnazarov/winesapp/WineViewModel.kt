package io.github.artemnazarov.winesapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.artemnazarov.winesapp.data.Wine
import io.github.artemnazarov.winesapp.data.WineRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class WineViewModel(
    private val repository: WineRepository
) : ViewModel() {

    val data = MutableLiveData<List<Wine>>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.value = "Exception handled: ${throwable.localizedMessage}"
        loading.postValue(false)
    }

    fun init() {
        loading.postValue(true)
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            data.postValue(repository.all.first())
        }
        loading.postValue(false)
    }

    fun addWine(wine: Wine) {
        loading.postValue(true)
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            repository.insert(wine)
            data.postValue(repository.all.first())
        }
        loading.postValue(false)
    }

    fun filter(query: String?) {
        loading.postValue(true)
        job = viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val d =
                if (query == null || query.isEmpty()) repository.all.first() else repository.findByName(
                    query
                ).first()
            data.postValue(d)
        }
        loading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}