package com.jetbrains.handson.httpapi.models

import kotlinx.serialization.Serializable

val orderStorage = mutableListOf<Order>()

@Serializable
data class Order(val number: String, val contents: List<OrderItem>)

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)
