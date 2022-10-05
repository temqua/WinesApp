package io.github.artemnazarov.winesapp.data

import io.github.artemnazarov.winesapp.dto.RatingDto

data class Rating(
    val average: Double,
    val reviews: String
) {
    constructor(dto: RatingDto) : this(dto.average.toDouble(), dto.reviews)
}