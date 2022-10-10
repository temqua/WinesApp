package io.github.artemnazarov.winesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.github.artemnazarov.winesapp.data.*
import io.github.artemnazarov.winesapp.databinding.FragmentAddWineBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddWineFragment : Fragment() {

    private var binding: FragmentAddWineBinding? = null
    private val viewModel: WineViewModel by viewModels {
        WineViewModelFactory((requireActivity().application as App).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddWineBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.wine_color_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val wineSpinner = binding?.wineSpinner
        wineSpinner?.adapter = adapter
        val add = binding?.addWineButton
        val wineText = binding?.editTextTitle
        val wineryText = binding?.editTextWinery
        val locationText = binding?.editTextLocation
        val wineRating = binding?.ratingBarAddWine
        add?.setOnClickListener {
            val selectedView = wineSpinner?.selectedView as TextView
            val colorStr = selectedView.text.toString().uppercase()
            val color = WineColor.valueOf(colorStr)
            val isValid = validate(
                wineryText?.text.toString(),
                wineText?.text.toString(),
                locationText?.text.toString()
            )
            if (!isValid) {
                Snackbar.make(
                    binding!!.root,
                    "Check entered data! Fields must be filled",
                    Snackbar.LENGTH_SHORT
                )
                    .setBackgroundTint(
                        ContextCompat.getColor(
                            view.context,
                            R.color.deep_orange_A700
                        )
                    )
                    .show()
                return@setOnClickListener
            }
            val wine = Wine(
                0,
                wineryText?.text.toString(),
                wineText?.text.toString(),
                locationText?.text.toString(),
                "",
                Rating(wineRating!!.rating.toDouble(), ""),
                color
            )
            viewModel.addWine(wine)
            findNavController().navigate(R.id.action_AddWineFragment_to_WineListFragment)
        }
    }

    private fun validate(winery: String, wine: String, location: String): Boolean {
        return winery.isNotEmpty() && wine.isNotEmpty() && location.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}