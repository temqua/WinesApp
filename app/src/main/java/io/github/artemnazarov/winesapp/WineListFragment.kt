package io.github.artemnazarov.winesapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import io.github.artemnazarov.winesapp.databinding.FragmentWineListBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WineListFragment : Fragment() {

    private var binding: FragmentWineListBinding? = null
    private val viewModel: WineViewModel by viewModels {
        WineViewModelFactory((requireActivity().application as App).repository)
    }
    private val adapter = WinesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWineListBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding?.winesView
        recycler?.adapter = adapter
        binding?.addWine?.setOnClickListener {
            findNavController().navigate(R.id.action_WineListFragment_to_AddWineFragment)
        }
        viewModel.data.observe(viewLifecycleOwner) {
            Log.d("Wine Fragment", "onCreate: $it")
            adapter.setWinesList(it)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Snackbar.make(binding!!.root, it, Snackbar.LENGTH_SHORT).show()
        }
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
                val item = menu.findItem(R.id.action_search)
                val searchView: SearchView = item.actionView as SearchView
                searchView.queryHint = "Type wine title to search"
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
//                        adapter.filter.filter(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter.filter(newText)
                        viewModel.filter(newText)
                        return false
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.action_search) {
                    return true
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}