package io.github.artemnazarov.winesapp.data.remote

import io.github.artemnazarov.winesapp.dto.WineDto
import retrofit2.Response
import retrofit2.http.GET

interface WineService {

    @GET("wines/reds")
    suspend fun getAllRed(): Response<List<WineDto>>

    @GET("wines/whites")
    suspend fun getAllWhite(): Response<List<WineDto>>

    @GET("wines/rose")
    suspend fun getAllRose(): Response<List<WineDto>>

    @GET("wines/sparkling")
    suspend fun getAllSparkling(): Response<List<WineDto>>

    @GET("wines/port")
    suspend fun getAllPort(): Response<List<WineDto>>

    @GET("wines/dessert")
    suspend fun getAllDessert(): Response<List<WineDto>>

    companion object {
        var service: WineService? = null

        fun getInstance(): WineService {

            if (service == null) {
                service = RetrofitInstance.instance.create(WineService::class.java)
            }
            return service!!
        }
    }
}