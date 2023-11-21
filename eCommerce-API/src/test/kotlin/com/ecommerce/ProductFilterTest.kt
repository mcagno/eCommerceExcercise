package com.ecommerce


import com.ecommerce.mock.MockedProductRepository
import com.ecommerce.services.ProductService
import com.ecommerce.model.Product
import com.ecommerce.services.ProductFilter
import kotlin.test.*

class ProductFilterTest {

    @Test
    fun `Validate simple filter`() {
        val filterString = "Name = test"
        val productFilter = ProductFilter(filterString);

        val result = productFilter.validate();

        assertTrue(result);
    }
}