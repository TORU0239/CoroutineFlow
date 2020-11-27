package sg.toru.coroutineflow.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sg.toru.coroutineflow.util.url
import java.util.concurrent.TimeUnit

object ApiModule {
    private val okhttp = OkHttpClient().newBuilder()
                            .readTimeout(5000, TimeUnit.MILLISECONDS)
                            .writeTimeout(5000, TimeUnit.MILLISECONDS)
                            .connectTimeout(5000, TimeUnit.MILLISECONDS)
                            .addNetworkInterceptor(
                                HttpLoggingInterceptor().apply {
                                    setLevel(HttpLoggingInterceptor.Level.BODY)
                                }
                            )
                            .build()


    val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .client(okhttp)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}