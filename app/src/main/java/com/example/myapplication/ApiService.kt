package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    fun getProductos(): Call<RespuestaProductos>
}