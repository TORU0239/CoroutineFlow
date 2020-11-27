package sg.toru.coroutineflow.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.Response
import sg.toru.coroutineflow.data.NetworkResponse
import sg.toru.coroutineflow.datasource.Item
import sg.toru.coroutineflow.datasource.MainDataSource

class MainRepository {
    private val dataSource: MainDataSource = MainDataSource()
    fun getData() = dataSource.fetchData()
            .flowOn(Dispatchers.IO)
            .filter { (it % 2) == 0 }
            .onEach { delay(200L) }
            .map { it.toString() }


    suspend fun fetchInformation(index:Int = 1) = dataSource.fetchInformation(index)
    suspend fun fetchInformation2(index:Int = 1) = object: NetworkResponse<Item>(){
        override suspend fun fetchFromNetwork(): Response<Item> = dataSource.fetchInformation2(index)
    }.asFlow()

}