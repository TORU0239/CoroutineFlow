package sg.toru.coroutineflow.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import sg.toru.coroutineflow.datasource.MainDataSource

class MainRepository {
    private val dataSource: MainDataSource = MainDataSource()
    fun getData() = dataSource.fetchData()
            .flowOn(Dispatchers.IO)
            .filter { (it % 2) == 0 }
            .onEach { delay(200L) }
            .map { it.toString() }


    suspend fun fetchInformation(index:Int = 1) = dataSource.fetchInformation(index)
}