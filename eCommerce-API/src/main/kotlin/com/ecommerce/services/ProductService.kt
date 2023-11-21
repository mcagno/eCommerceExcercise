package com.ecommerce.services

import com.ecommerce.infrastructure.IProductRepository
import com.ecommerce.model.Product
import org.koin.core.component.KoinComponent

class ProductService(private var productRepository: IProductRepository) : KoinComponent {

    fun getAllProducts(): Collection<Product> = productRepository.get()

    fun add(product: Product): Product = productRepository.add(product)

}