package io.github.artemnazarov.winesapp.data

import androidx.room.*
import io.github.artemnazarov.winesapp.dto.WineDto

@Entity(
    tableName = "wines",
)
data class Wine(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val winery: String,
    @ColumnInfo(name = "name")
    val wine: String,
    val location: String,
    @ColumnInfo(name = "image_src")
    val imageSrc: String,
    @Embedded
    val rating: Rating,
    val color: WineColor
) {
    constructor(dto: WineDto, color: WineColor) : this(
        0,
        dto.winery,
        dto.wine,
        dto.location,
        dto.imageSrc,
        Rating(dto.rating),
        color
    )
}
