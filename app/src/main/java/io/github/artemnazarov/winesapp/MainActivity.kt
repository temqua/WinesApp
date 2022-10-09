package io.github.artemnazarov.winesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.github.artemnazarov.winesapp.data.WineViewModel
import io.github.artemnazarov.winesapp.data.WineViewModelFactory
import io.github.artemnazarov.winesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: WineViewModel by viewModels {
        WineViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = WinesAdapter()
        val recycler = binding.winesView
        recycler.adapter = adapter
        viewModel.data.observe(this) {
            Log.d("WineActivity", "onCreate: $it")

            adapter.setWinesList(it)
        }
        viewModel.errorMessage.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
        viewModel.initDatabase()
    }


}
