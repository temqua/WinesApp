package io.github.artemnazarov.winesapp.data

import io.github.artemnazarov.winesapp.dto.WineDto

data class Wine(
    val id: Int,
    val winery: String,
    val wine: String,
    val location: String,
    val imageSrc: String,
    val rating: Rating
) {
    constructor(dto: WineDto) : this(
        dto.id,
        dto.winery,
        dto.wine,
        dto.location,
        dto.imageSrc,
        Rating(dto.rating)
    )
}
