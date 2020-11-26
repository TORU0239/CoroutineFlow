package sg.toru.coroutineflow.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import sg.toru.coroutineflow.datasource.MainDataSource

class MainRepository(private val dataSource: MainDataSource) {
    fun getData() = dataSource.fetchData()
                                    .filter { (it % 2) == 0 }
                                    .map { it.toString() }
                                    .flowOn(Dispatchers.IO)
}