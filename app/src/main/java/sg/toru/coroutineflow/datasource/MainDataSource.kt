package sg.toru.coroutineflow.datasource

import kotlinx.coroutines.flow.asFlow

/*
* This class is to fetch data from local / remote.
*
* */
class MainDataSource {
    fun fetchData() = listOf(1,2,3,4,5,6).asFlow()
}