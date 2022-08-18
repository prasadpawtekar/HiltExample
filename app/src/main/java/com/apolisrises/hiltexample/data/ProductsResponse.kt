package com.apolisrises.hiltexample.data

data class ProductsResponse(
    val message: String,
    val products: List<Product>,
    val status: Int
)