package io.github.artemnazarov.winesapp

import androidx.recyclerview.widget.DiffUtil
import io.github.artemnazarov.winesapp.data.Wine

class WinesDiffUtilCallback(private val oldList: List<Wine>, private val newList: List<Wine>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val nu = newList[newItemPosition]
        return when {
            old.id == nu.id -> true
            old.wine == nu.wine -> true
            else -> false
        }
    }

}