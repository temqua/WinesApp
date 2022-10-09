package io.github.artemnazarov.winesapp

import android.app.Application
import io.github.artemnazarov.winesapp.data.WineRepository
import io.github.artemnazarov.winesapp.data.remote.WineService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class App : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy { WineRepository(WineService.getInstance(), database.wineDao()) }
}