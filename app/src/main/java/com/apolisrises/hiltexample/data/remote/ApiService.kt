package com.apolisrises.hiltexample.data.remote

import com.apolisrises.hiltexample.data.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("Product")
    suspend fun getProducts(): Response<ProductsResponse>
}