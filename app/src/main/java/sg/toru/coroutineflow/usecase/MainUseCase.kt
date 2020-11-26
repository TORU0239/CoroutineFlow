package sg.toru.coroutineflow.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import sg.toru.coroutineflow.repository.MainRepository

class MainUseCase(private val repository: MainRepository) {
    fun getData() = repository.getData()
}