package io.github.artemnazarov.winesapp.dto

import com.google.gson.annotations.SerializedName

data class WineDto(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("winery")
    val winery: String,
    @field:SerializedName("wine")
    val wine: String,
    @field:SerializedName("location")
    val location: String,
    @field:SerializedName("image")
    val imageSrc: String,
    @field:SerializedName("rating")
    val rating: RatingDto
)