package com.ecommerce.test

import com.ecommerce.model.Product
import com.ecommerce.services.*
import kotlinx.serialization.json.Json
import kotlin.test.*
class ProductFilterTest {
    @Test
    fun `Simple numeric filter`() {
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")

        val products = listOf(trousers, skirt)

        val numericFilter = ProductFilter("price", ProductFilterOperator.GT, "11")

        val result = numericFilter.apply(products)

        assertEquals(1, result.size)
        assertEquals(skirt, result.toList()[0])
    }

    @Test
    fun `Simple string filter`() {
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")

        val products = listOf(trousers, skirt)

        val productFilter = ProductFilter("name", ProductFilterOperator.IN, "ro")

        val result = productFilter.apply(products)

        assertEquals(1, result.size)
        assertEquals(trousers, result.toList()[0])
    }

    @Test
    fun `Apply compound numeric criterion`() {
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")
        val jeans = Product(3, "Jeans", 12.0, "L", "Red")

        val products = listOf(trousers, skirt, jeans)

        val compoundProductFilter = CompoundProductFilter(listOf(
            ProductFilter("price", ProductFilterOperator.GT, "11"),
            ProductFilter("id", ProductFilterOperator.LT, "3")
        ))

        val result = compoundProductFilter.apply(products)

        assertEquals(1, result.size)
        assertEquals(skirt, result.toList()[0])
    }

    @Test
    fun `Apply compound mixed criterion`() {
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")
        val jeans = Product(3, "Jeans", 12.0, "L", "Red")

        val products = listOf(trousers, skirt, jeans)

        val compoundProductFilter = CompoundProductFilter(listOf(
            ProductFilter("price", ProductFilterOperator.GT, "11"),
            ProductFilter("name", ProductFilterOperator.IN, "ea")
        ))

        val result = compoundProductFilter.apply(products)

        assertEquals(1, result.size)
        assertEquals(jeans, result.toList()[0])
    }

    @Test
    fun `Apply compound string criterion`() {
        val trousers = Product(1, "Trousers", 10.0, "M", "Green")
        val skirt = Product(2, "Skirt", 12.0, "S", "Red")
        val jeans = Product(3, "Jeans", 12.0, "L", "Red")

        val products = listOf(trousers, skirt, jeans)

        val compoundProductFilter = CompoundProductFilter(listOf(
            ProductFilter("color", ProductFilterOperator.EQUAL, "Red"),
            ProductFilter("name", ProductFilterOperator.IN, "ea")
        ))

        val result = compoundProductFilter.apply(products)

        assertEquals(1, result.size)
        assertEquals(jeans, result.toList()[0])
    }

    @Test
    fun `Compound filter deserialization`() {
        val encodedString = "[{\"fieldName\":\"price\",\"operator\":\"GT\",\"value\":\"11\"},{\"fieldName\":\"name\",\"operator\":\"IN\",\"value\":\"ea\"}]"
        val compoundProductFilter = CompoundProductFilter(Json.decodeFromString<List<ProductFilter>>(encodedString))

        val firstFilter = ProductFilter("price", ProductFilterOperator.GT, "11")
        val secondFilter = ProductFilter("name", ProductFilterOperator.IN, "ea")

        assertEquals(2, compoundProductFilter.size)
        assertEquals(firstFilter.fieldName, compoundProductFilter[0].fieldName)
        assertEquals(firstFilter.operator, compoundProductFilter[0].operator)
        assertEquals(firstFilter.value, compoundProductFilter[0].value)
        assertEquals(secondFilter.fieldName, compoundProductFilter[1].fieldName)
        assertEquals(secondFilter.operator, compoundProductFilter[1].operator)
        assertEquals(secondFilter.value, compoundProductFilter[1].value)
    }
}