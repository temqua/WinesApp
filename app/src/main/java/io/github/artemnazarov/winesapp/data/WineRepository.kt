package io.github.artemnazarov.winesapp.data

class WineRepository(private val service: WineService) {
    suspend fun getReds() = service.getReds()
}