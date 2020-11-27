package sg.toru.coroutineflow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import sg.toru.coroutineflow.databinding.ActivityMainBinding
import sg.toru.coroutineflow.datasource.MainDataSource
import sg.toru.coroutineflow.repository.MainRepository
import sg.toru.coroutineflow.usecase.MainUseCase
import sg.toru.coroutineflow.util.onClick

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val mainUseCase:MainUseCase by lazy {
        MainUseCase()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeMainInformation()

        binding.btnFlow
            .onClick()
            .onEach {
                mainUseCase.getData()
                        .cancellable()
                        .onCompletion {
                            Toast.makeText(this@MainActivity, "completed", Toast.LENGTH_SHORT).show()
                        }
                        .collect {
                            val prev = binding.txtCenter.text
                            binding.txtCenter.text = "$prev\n$it"
                        }
            }

            .launchIn(lifecycleScope)
    }

    private fun observeMainFlow(){
        lifecycleScope.launch {
            mainUseCase.getData().collect{
                val prev = binding.txtCenter.text
                binding.txtCenter.text = "$prev\n$it"
            }
        }
    }

    private fun observeMainInformation() {
        lifecycleScope.launch {
            mainUseCase.getInformation()
                    .flowOn(Dispatchers.IO)
                    .catch {
                        Log.e("Toru", "exceptional case")
                    }
                    .collect { item ->
                        binding.txtCenter.text = "${item.title} / ${item.userId}"
                    }

        }
    }
}