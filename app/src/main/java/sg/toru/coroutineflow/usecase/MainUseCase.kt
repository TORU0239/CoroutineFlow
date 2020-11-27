package sg.toru.coroutineflow.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOf
import sg.toru.coroutineflow.repository.MainRepository

class MainUseCase(private val dispatcher:CoroutineDispatcher) {
    private val repository: MainRepository = MainRepository()
    fun getData() = repository.getData()

    suspend fun getInformation() = flowOf(repository.fetchInformation())
}