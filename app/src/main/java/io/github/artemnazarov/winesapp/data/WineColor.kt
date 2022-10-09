package io.github.artemnazarov.winesapp.data

import androidx.room.Entity

enum class WineColor(
    private val color: String
) {
    RED("red"),
    WHITE("white"),
    SPARKLING("sparkling"),
    ROSE("rose"),
    DESSERT("dessert"),
    PORT("port"),
}