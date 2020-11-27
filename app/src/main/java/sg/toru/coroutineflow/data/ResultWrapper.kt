package sg.toru.coroutineflow.data

import android.util.Log
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

sealed class Responses<T>{
    data class Success<T>(val body:T) : Responses<T>()
    data class Failure<T>(val error:String) :Responses<T>()
    data class Exception<T>(val exception:String) :Responses<T>()

}

abstract class NetworkResponse<T>{
    fun asFlow() = flow<Responses<T>> {
        val apiResult = fetchFromNetwork()
        if(apiResult.isSuccessful){
            emit(Responses.Success(apiResult.body()!!))
        } else {
            emit(Responses.Failure(apiResult.message()))
        }
    }.catch {
        emit(Responses.Exception("Please check your connectivity!"))
    }

    protected abstract suspend fun fetchFromNetwork():Response<T>
}