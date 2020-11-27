package sg.toru.coroutineflow.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import sg.toru.coroutineflow.repository.MainRepository

class MainUseCase(private val dispatcher:CoroutineDispatcher = Dispatchers.IO) {
    private val repository: MainRepository = MainRepository()
    fun getData() = repository.getData()

    suspend fun getInformation(index:Int = 1) = flowOf(repository.fetchInformation(index))

    suspend fun getInfo(index:Int = 1) =
        callbackFlow{
            val response = repository.fetchInformation2(index)
            response.collect {
                offer(it)
            }
            awaitClose()
        }
}