package com.ecommerce.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val size: String,
    val color: String,
    val inStock: Boolean = true,
    val discontinued: Boolean = false,
    val description: String = "",
    val brand: String = "",
    val sku: String = "",
    val category: String = "",
    val length: Int = 0,
    val width: Int = 0,
    val weight: Int = 0,
    val materialComposition: String = "",
    val organic: Boolean = false
)



