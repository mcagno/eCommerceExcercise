package com.ecommerce.infrastructure

import com.ecommerce.model.Product
import com.ecommerce.services.IProductFilter

interface IProductRepository {
    fun add(product: Product) : Product
    fun get() : List<Product>
    fun query(productFilter: IProductFilter): Collection<Product>
}
