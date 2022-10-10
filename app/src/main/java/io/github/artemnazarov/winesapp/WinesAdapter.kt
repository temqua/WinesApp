package io.github.artemnazarov.winesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.artemnazarov.winesapp.data.Wine
import io.github.artemnazarov.winesapp.databinding.ItemWinesBinding

class WinesAdapter :
    RecyclerView.Adapter<WinesAdapter.ViewHolder>(), Filterable {

    private var wines = mutableListOf<Wine>()
    private var filteredWines = mutableListOf<Wine>()

    fun setWinesList(updatedWines: List<Wine>) {
        val diffResult = DiffUtil.calculateDiff(WinesDiffUtilCallback(wines, updatedWines))
        wines.clear()
        wines.addAll(updatedWines)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(binding: ItemWinesBinding) : RecyclerView.ViewHolder(binding.root) {
        val logoView = binding.image
        val nameView = binding.wineName
        val ratingView = binding.wineRating
        val locationView = binding.wineLocation
        val wineryView = binding.wineryName
        val ratingsCountView = binding.wineRatingsCount
        val colorView = binding.wineColor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemWinesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wine = wines[position]
        holder.locationView.text = wine.location
        holder.wineryView.text = wine.winery
        holder.ratingsCountView.text = wine.rating.reviews
        holder.ratingView.rating = wine.rating.average.toFloat()
        holder.nameView.text = wine.wine
        holder.colorView.text = wine.color.toString()
        Glide.with(holder.logoView.context).load(wine.imageSrc).into(holder.logoView)
    }

    override fun getItemCount(): Int = wines.size
    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            if (constraint == null || constraint.isEmpty()) {
                filterResults.count = wines.size
                filterResults.values = wines
            } else {
                val searchStr = constraint.toString().lowercase()
                val searchResult = wines.filter { it.wine == searchStr }
                filterResults.values = searchResult
                filterResults.count = searchResult.size
            }
            return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            val diffResult = DiffUtil.calculateDiff(
                WinesDiffUtilCallback(
                    filteredWines,
                    results!!.values as List<Wine>
                )
            )
            filteredWines.clear()
            filteredWines.addAll(results.values as List<Wine>)
            diffResult.dispatchUpdatesTo(this@WinesAdapter)
        }

    }

}