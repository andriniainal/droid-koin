package dev.andriniainal.droidkoin.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.andriniainal.droidkoin.data.remote.api.NewAPIService
import dev.andriniainal.droidkoin.data.repository.ArticlesRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(NewAPIService.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(NewAPIService::class.java)
    }
    single {
        ArticlesRepository(get())
    }
}