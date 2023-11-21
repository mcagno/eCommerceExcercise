package com.ecommerce.services

import com.ecommerce.infrastructure.IProductRepository
import com.ecommerce.model.Product

class ProductService(private var productRepository: IProductRepository) {

    fun getAllProducts(): Collection<Product> = productRepository.get()

    fun add(product: Product): Product = productRepository.add(product)

}