package io.github.artemnazarov.winesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import io.github.artemnazarov.winesapp.data.*
import io.github.artemnazarov.winesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: WineViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            WineViewModelFactory(WineRepository(WineService.getInstance()))
        )[WineViewModel::class.java]
//        val oldAdapter = WineListAdapter(this, mutableListOf())
        val adapter = WinesAdapter()
        val recycler = binding.winesView
        recycler.adapter = adapter
        viewModel.reds.observe(this) {
            Log.d("WineActivity", "onCreate: $it")
            adapter.setWinesList(it)
        }
        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.getReds()
    }


}
