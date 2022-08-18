package com.apolisrises.hiltexample.data.remote

import javax.inject.Inject

class Repository @Inject constructor(val apiService: ApiService) {

    suspend fun getProducts() = apiService.getProducts()
}