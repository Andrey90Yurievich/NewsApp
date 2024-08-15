package ru.ayuandrey.neewsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.ayuandrey.neewsapp.data.remote.dto.NewsResponse
import ru.ayuandrey.neewsapp.util.Constants.API_KEY

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") string: String,
        @Query("apiKey") apiKey: String = API_KEY
    ) : NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}