package io.github.artemnazarov.winesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WineDao {
    @Query("SELECT * FROM wines")
    fun getAll(): Flow<List<Wine>>

    @Query("SELECT * FROM wines WHERE wines.name LIKE :wine LIMIT 1")
    fun findByName(wine: String): Wine

    @Query("SELECT * FROM wines WHERE color LIKE '%red%'")
    fun getAllRed(): Flow<List<Wine>>

    @Query("SELECT * FROM wines WHERE color LIKE '%rose%'")
    fun getAllRose(): Flow<List<Wine>>

    @Query("SELECT * FROM wines WHERE color LIKE '%white%'")
    fun getAllWhite(): Flow<List<Wine>>

    @Query("SELECT * FROM wines WHERE color LIKE '%sparkling%'")
    fun getAllSparkling(): Flow<List<Wine>>

    @Query("SELECT * FROM wines WHERE color LIKE '%dessert%'")
    fun getAllDessert(): Flow<List<Wine>>

    @Query("SELECT * FROM wines WHERE color LIKE '%port%'")
    fun getAllPort(): Flow<List<Wine>>

    @Insert(onConflict = ABORT)
    suspend fun insert(vararg wines: Wine)

    @Insert(onConflict = ABORT)
    suspend fun insertAll(wines: List<Wine>)

    @Delete
    suspend fun delete(wine: Wine)

    @Query("DELETE FROM wines WHERE id > 0")
    suspend fun deleteAll()
}