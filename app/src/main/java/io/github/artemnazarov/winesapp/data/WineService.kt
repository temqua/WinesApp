package io.github.artemnazarov.winesapp.data

import io.github.artemnazarov.winesapp.dto.WineDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface WineService {

    @GET("wines/reds")
    suspend fun getReds(): Response<List<WineDto>>

    @GET("wines/whites")
    suspend fun getWhites(): Response<List<WineDto>>

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