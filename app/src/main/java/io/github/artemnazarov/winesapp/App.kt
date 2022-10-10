package io.github.artemnazarov.winesapp

import android.app.Application
import android.util.Log
import io.github.artemnazarov.winesapp.data.Wine
import io.github.artemnazarov.winesapp.data.WineColor
import io.github.artemnazarov.winesapp.data.WineRepository
import io.github.artemnazarov.winesapp.data.remote.WineService
import kotlinx.coroutines.*

class App : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { WineRepository(WineService.getInstance(), database.wineDao()) }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("WinesApp", "Exception handled: ${throwable.localizedMessage}")
    }

    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    private fun initDatabase() {
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
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

}