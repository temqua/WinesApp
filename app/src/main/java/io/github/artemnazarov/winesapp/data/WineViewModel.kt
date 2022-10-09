package io.github.artemnazarov.winesapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.*

class WineViewModel(
    private val repository: WineRepository
) : ViewModel() {

    val data: LiveData<List<Wine>> = repository.all.asLiveData()
    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.value = "Exception handled: ${throwable.localizedMessage}"
        loading.postValue(false)
    }

    fun initDatabase() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val red = repository.getAllRed()
            withContext(Dispatchers.Main) {
                if (red.isSuccessful) {
                    val wines = red.body()?.map { wineDto -> Wine(wineDto, WineColor.RED) }
                    wines?.let { repository.insertAll(it) }
                }
            }
            val white = repository.getAllWhite()
            withContext(Dispatchers.Main) {
                if (white.isSuccessful) {
                    val wines = white.body()?.map { wineDto -> Wine(wineDto, WineColor.WHITE) }
                    wines?.let { repository.insertAll(it) }
                }
            }
            val rose = repository.getAllRose()
            withContext(Dispatchers.Main) {
                if (rose.isSuccessful) {
                    val wines = rose.body()?.map { wineDto -> Wine(wineDto, WineColor.ROSE) }
                    wines?.let { repository.insertAll(it) }
                }
            }
            val sparkling = repository.getAllSparkling()
            withContext(Dispatchers.Main) {
                if (sparkling.isSuccessful) {
                    val wines =
                        sparkling.body()?.map { wineDto -> Wine(wineDto, WineColor.SPARKLING) }
                    wines?.let { repository.insertAll(it) }
                }
            }
            val port = repository.getAllSparkling()
            withContext(Dispatchers.Main) {
                if (port.isSuccessful) {
                    val wines = port.body()?.map { wineDto -> Wine(wineDto, WineColor.PORT) }
                    wines?.let { repository.insertAll(it) }
                }
            }
            val dessert = repository.getAllDessert()
            withContext(Dispatchers.Main) {
                if (dessert.isSuccessful) {
                    val wines = dessert.body()?.map { wineDto -> Wine(wineDto, WineColor.DESSERT) }
                    wines?.let { repository.insertAll(it) }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}