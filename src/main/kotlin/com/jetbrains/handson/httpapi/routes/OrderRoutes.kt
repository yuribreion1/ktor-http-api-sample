package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.Order
import com.jetbrains.handson.httpapi.models.orderStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.registerOrderRoutes() {
    routing {
        createOrderRoute()
        listOrdersRoute()
        getOrderRoute()
        totalizeOrderRoute()
    }
}

fun Route.createOrderRoute() {
    post("/order") {
        val order = call.receive<List<Order>>()
        if (order.size == 1) orderStorage.add(order[0])
        else orderStorage.addAll(order)
        call.respondText("Order stored correctly", status = HttpStatusCode.Created)
    }
}

fun Route.listOrdersRoute() {
    get("/order") {
        if (orderStorage.isNotEmpty()) call.respond(orderStorage)
        else call.respondText("Order no found", status = HttpStatusCode.NotFound)
    }
}

fun Route.getOrderRoute() {
    get("/order/{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText("Bad request", status = HttpStatusCode.BadRequest)
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText("Not found", status = HttpStatusCode.NotFound)
        call.respond(order)
    }
}

fun Route.totalizeOrderRoute() {
    get("/order/{id}/total") {
        val id = call.parameters["id"] ?: return@get call.respondText("Bad request", status = HttpStatusCode.BadRequest)
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText("Not found", status = HttpStatusCode.NotFound)
        val total = order.contents.sumOf { it.price * it.amount }
        call.respond(total)
    }
}