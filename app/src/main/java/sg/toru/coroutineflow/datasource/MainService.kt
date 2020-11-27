package sg.toru.coroutineflow.datasource

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {
    @GET("{index}")
    suspend fun getTest(@Path("index") index:Int): Item
}

data class Item(
    val userId:String,
    val id:Int,
    val title:String,
    val completed:Boolean
)