package sg.toru.coroutineflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import sg.toru.coroutineflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        test()
    }

    private val testFlow =  flow {
        for (i in 1..6){
            emit(i.toString())
//            delay(200)
        }
    }


    private fun test() {
        lifecycleScope.launch {
            testFlow.flowOn(Dispatchers.IO)
                    .onEach {
                        delay(200L)
                        Log.e("Toru", it)
                        val prev = binding.txtCenter.text
                        binding.txtCenter.text = "$prev\n$it"
                    }
                    .launchIn(CoroutineScope(Dispatchers.Main))
        }
    }
}