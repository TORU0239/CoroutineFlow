package sg.toru.coroutineflow.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        MainUseCase(MainRepository(MainDataSource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeMainFlow()
        binding.btnFlow
            .onClick()
            .onEach {
                mainUseCase.getData().collect{
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
}