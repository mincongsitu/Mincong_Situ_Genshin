package com.example.mincong_situ_genshin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.mincong_situ_genshin.api.GenshinRepository
import com.example.mincong_situ_genshin.api.GenshinRepositoryImpl
import com.example.mincong_situ_genshin.api.GenshinService
import com.example.mincong_situ_genshin.viewmodel.GenshinViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object DI {

    private val service = Retrofit.Builder()
        .baseUrl("https://api.genshin.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpClient())
        //used to log network calls to track responses and errors
        .build()
        .create(GenshinService::class.java)


    private fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideRepository() = GenshinRepositoryImpl(service)
    private fun provideDispatcher() = Dispatchers.IO

    fun provideViewModel(storeOwner: ViewModelStoreOwner): GenshinViewModel{
        return ViewModelProvider(storeOwner, object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return GenshinViewModel(provideRepository(), provideDispatcher()) as T
            }
        })[GenshinViewModel::class.java]
    }
}