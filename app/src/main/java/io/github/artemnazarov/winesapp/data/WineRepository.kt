package io.github.artemnazarov.winesapp.data

import io.github.artemnazarov.winesapp.data.remote.WineService
import kotlinx.coroutines.flow.Flow

class WineRepository(private val service: WineService, private val dao: WineDao) {

    suspend fun getAllRed() = service.getAllRed()
    suspend fun getAllWhite() = service.getAllWhite()
    suspend fun getAllRose() = service.getAllRose()
    suspend fun getAllSparkling() = service.getAllSparkling()
    suspend fun getAllPort() = service.getAllPort()
    suspend fun getAllDessert() = service.getAllDessert()

    val all: Flow<List<Wine>> = dao.getAll()
    val red: Flow<List<Wine>> = dao.getAllRed()
    val rose: Flow<List<Wine>> = dao.getAllRose()
    val white: Flow<List<Wine>> = dao.getAllWhite()
    val sparkling: Flow<List<Wine>> = dao.getAllSparkling()
    val dessert: Flow<List<Wine>> = dao.getAllDessert()
    val port: Flow<List<Wine>> = dao.getAllPort()

    suspend fun insert(vararg wines: Wine) = dao.insert(*wines)

    suspend fun insertAll(wines: List<Wine>) = dao.insertAll(wines)

    suspend fun delete(wine: Wine) = dao.delete(wine)

    suspend fun deleteAll() = dao.deleteAll()

}