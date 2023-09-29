package com.example.myapplication

data class RespuestaProductos (
    val products: List<Producto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)