package io.github.artemnazarov.winesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import io.github.artemnazarov.winesapp.data.Wine
import io.github.artemnazarov.winesapp.databinding.ItemWinesBinding

class WineListAdapter(
    context: Context,
    var data: MutableList<Wine>,
) : ArrayAdapter<Wine>(context, 0, data) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val binding = ItemWinesBinding.inflate(LayoutInflater.from(context), parent, false)
        val logoView = binding.image
        val nameView = binding.wineName
        val ratingView = binding.wineRating
        val locationView = binding.wineLocation
        val wineryView = binding.wineryName
        val ratingsCountView = binding.wineRatingsCount
        val wine = data[position]
        locationView.text = wine.location
        wineryView.text = wine.winery
        ratingsCountView.text = wine.rating.reviews
        ratingView.rating = wine.rating.average.toFloat()
        nameView.text = wine.wine
        Glide.with(logoView.context).load(wine.imageSrc).into(logoView)
        return binding.root
    }

    fun setWinesList(updatedWines: List<Wine>) {
        data.clear()
        data.addAll(updatedWines)
    }
}