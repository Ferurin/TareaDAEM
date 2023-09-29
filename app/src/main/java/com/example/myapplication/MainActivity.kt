package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getProductosCall = getDummyJsonRetrofit()
            .create(ApiService::class.java)
            .getProductos()

        CoroutineScope(Dispatchers.IO).launch {
            val respuestaProductos = getProductosCall.await()
            withContext(Dispatchers.Main)
            {
                respuestaProductos?.let {
                    val gsonPretty = GsonBuilder().setPrettyPrinting().create()
                    val respuestaJson = gsonPretty.toJson(respuestaProductos)
                    Log.i(TAG, "Productos")
                    Log.i(TAG, respuestaJson)
                }
            }
        }
    }
}

fun getDummyJsonRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl ("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}