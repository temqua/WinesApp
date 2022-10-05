package io.github.artemnazarov.winesapp.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class WineViewModel(private val repository: WineRepository) : ViewModel() {

    val reds = MutableLiveData<List<Wine>>()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.value = "Exception handled: ${throwable.localizedMessage}"
        loading.postValue(false)
    }

    fun getReds() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = repository.getReds()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val body = response.body()
                    val wines: MutableList<Wine> = mutableListOf()
                    body?.forEach { wines.add(Wine(it)) }
                    reds.postValue(wines)
                }
            }
            loading.postValue(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}