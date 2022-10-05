package io.github.artemnazarov.winesapp.dto

import com.google.gson.annotations.SerializedName

data class RatingDto(
    @field:SerializedName("average")
    val average: String,
    @field:SerializedName("reviews")
    val reviews: String
)