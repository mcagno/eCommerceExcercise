package com.ecommerce.services

import com.ecommerce.model.Product
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.reflect.full.createType

import kotlin.reflect.full.memberProperties

interface IProductFilter {
    fun apply(products: Collection<Product>): Collection<Product>
}
@Serializable
class CompoundProductFilter() : IProductFilter, MutableList<ProductFilter> by mutableListOf() {

    constructor(filters: Collection<ProductFilter>) : this() {
        addAll(filters)
    }
    override fun apply(products: Collection<Product>): Collection<Product> {
        var result : Collection<Product> = products.toList()
        for (filter in asIterable())
            result = filter.apply(result)
        val testString = Json.encodeToString(this.toList())
        return result
    }
}

@Serializable
enum class ProductFilterOperator {EQUAL, GT, GTE, LT, LTE, IN}

@Serializable
class ProductFilter(val fieldName: String, val operator: ProductFilterOperator, val value: String) : IProductFilter {
    override fun apply(products: Collection<Product>) : Collection<Product> {
        val propertyPointer = Product::class.memberProperties.find { it.name == fieldName }
        if (propertyPointer?.returnType == String::class.createType() ) {
            return when (operator) {
                ProductFilterOperator.EQUAL -> {
                    products.filter { propertyPointer.get(it) as String == value }
                }
                ProductFilterOperator.IN -> {
                    products.filter { (propertyPointer.get(it) as String).contains(value) }
                }
                else -> throw IllegalArgumentException("Not supported operator $operator in string-based filter")
            }
        }
        return when (operator) {
            ProductFilterOperator.EQUAL -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() == value.toDouble() }
            ProductFilterOperator.GTE -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() >= value.toDouble() }
            ProductFilterOperator.GT -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() > value.toDouble() }
            ProductFilterOperator.LTE -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() <= value.toDouble() }
            ProductFilterOperator.LT -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() < value.toDouble() }
            else -> throw IllegalArgumentException("Not supported operator $operator in numeric-based filter ")
        }


    }
}

/*


@Serializable
class NumericProductFilter(override val fieldName: String, val operator: ProductFilterOperator,  val value: Int) : BaseProductFilter() {
    override val type: ProductFilterType = ProductFilterType.NUMERIC
    override fun apply(products: Collection<Product>) : Collection<Product> {
        val propertyPointer = Product::class.memberProperties.find { it.name == fieldName }
        val result : Collection<Product> = when (operator) {
            NumericProductFilterOperator.EQUAL -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() == value.toDouble() }
            NumericProductFilterOperator.GTE -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() >= value.toDouble() }
            NumericProductFilterOperator.GT -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() > value.toDouble() }
            NumericProductFilterOperator.LTE -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() <= value.toDouble() }
            NumericProductFilterOperator.LT -> products.filter { (propertyPointer!!.get(it) as Number).toDouble() < value.toDouble() }
        }

        return result
    }
}



abstract class BaseProductFilter: IProductFilter {
    abstract val type: ProductFilterType
    abstract val fieldName: String
}
@Serializabl
enum class ProductFilterType {
    @SerialName("numeric")
    NUMERIC,
    @SerialName("string")
    STRING
}


object ProductFilterSerializer :
    JsonContentPolymorphicSerializer<BaseProductFilter>(
        BaseProductFilter::class
    ) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<BaseProductFilter> {
        return when (element.jsonObject["type"]?.jsonPrimitive?.content) {
            "numeric" -> NumericProductFilter.serializer()
            "string" -> StringProductFilter.serializer()
            else -> throw Exception("ERROR: No Serializer found. Serialization failed.")
        }
    }
}*/

