package com.apolisrises.hiltexample.di

import com.apolisrises.hiltexample.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        val r = Retrofit.Builder().apply {
            baseUrl("https://psmobitech.com/fcmdemo2/index.php/")
            addConverterFactory(GsonConverterFactory.create())
            client(client)
        }.build()
        return r
    }


    @Provides
    fun providesApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

}