package com.apolisrises.hiltexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.products.observe(this) {
            Log.d("Response", "Products is : ${it.products}")
        }
        viewModel.error.observe(this){
            Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getProducts()
    }
}