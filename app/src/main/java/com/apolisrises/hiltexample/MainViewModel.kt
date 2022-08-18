package com.apolisrises.hiltexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apolisrises.hiltexample.data.ProductsResponse
import com.apolisrises.hiltexample.data.remote.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: Repository): ViewModel() {

    val products = MutableLiveData<ProductsResponse>()
    val error = MutableLiveData<String>()

    fun getProducts() {
        viewModelScope.launch(IO) {
            try {
                val response = repository.getProducts()
                if(!response.isSuccessful) {
                    error.postValue("Failed to load data from server. Retry.")
                    return@launch
                }

                val productsResponse = response.body()

                if(productsResponse == null) {
                    error.postValue("Empty response from the server")
                    return@launch
                }

                if(productsResponse.status == 0) {
                    products.postValue(productsResponse)
                } else {
                    error.postValue(productsResponse.message)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                error.postValue("Error is : $e")
            }
        }
    }
}