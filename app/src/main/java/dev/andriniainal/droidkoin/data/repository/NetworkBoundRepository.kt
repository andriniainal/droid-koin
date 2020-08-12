package dev.andriniainal.droidkoin.data.repository

import androidx.annotation.MainThread
import dev.andriniainal.droidkoin.utils.State
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class NetworkBoundRepository<REQUEST> {
    fun asFlow() = flow<State<REQUEST>> {
        emit(State.loading())
        try {
            val apiResponse = fetchFromRemote()
            val remotePosts = apiResponse.body()
            if (apiResponse.isSuccessful && remotePosts != null) {
                emit(State.success(remotePosts))
            } else {
                emit(State.error(apiResponse.message()))
            }
        } catch (e: Exception) {
            emit(State.error("Network error! Can't get latest posts."))
            e.printStackTrace()
        }
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}