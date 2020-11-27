package sg.toru.coroutineflow.datasource

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainService {
    @GET("{index}")
    suspend fun getTest(@Path("index") index:Int):Item


    @GET("{index}")
    suspend fun getInformation(@Path("index") index:Int): Response<Item>
}

data class Item(
    val userId:String,
    val id:Int,
    val title:String,
    val completed:Boolean
)