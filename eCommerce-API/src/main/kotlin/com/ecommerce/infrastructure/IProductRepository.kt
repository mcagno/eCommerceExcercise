package com.ecommerce.infrastructure

import com.ecommerce.model.Product

interface IProductRepository {
    fun add(product: Product) : Product
    fun get() : Collection<Product>
}
