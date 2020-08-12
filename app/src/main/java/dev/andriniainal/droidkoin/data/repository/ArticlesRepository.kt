package dev.andriniainal.droidkoin.data.repository

import dev.andriniainal.droidkoin.data.remote.api.NewAPIService
import dev.andriniainal.droidkoin.model.ArticleList
import dev.andriniainal.droidkoin.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class ArticlesRepository constructor(private val newsAPIService: NewAPIService) {
    fun getAllArticles(): Flow<State<ArticleList>> {
        return object : NetworkBoundRepository<ArticleList>() {
            override suspend fun fetchFromRemote(): Response<ArticleList> =
                newsAPIService.getArticles(NewAPIService.API_KEY)
        }.asFlow().flowOn(Dispatchers.IO)
    }
}