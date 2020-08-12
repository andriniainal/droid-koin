package dev.andriniainal.droidkoin.data.remote.api

import dev.andriniainal.droidkoin.model.ArticleList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewAPIService {
    @GET("v2/everything?q=bitcoin&from=2020-08-01&sortBy=publishedAt")
    suspend fun getArticles(@Query("apiKey") apiKey: String): Response<ArticleList>

    companion object {
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "1ae9784e0d694c5ca803a3a50bcb0e11"
    }
}