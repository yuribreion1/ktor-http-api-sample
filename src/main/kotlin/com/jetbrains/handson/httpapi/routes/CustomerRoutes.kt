package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.Customer
import com.jetbrains.handson.httpapi.models.customerStorage
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}

fun Route.customerRouting() {

    route("/customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customer found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText("Missing or malformed id", status = HttpStatusCode.BadRequest)
            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText("No customer with the $id", status = HttpStatusCode.NotFound)
            call.respond(customer)
        }
        post {
            val customer = call.receive<List<Customer>>()
            if (customer.size == 1) {
                customerStorage.add(customer[0])
            } else {
                customerStorage.addAll(customer)
            }
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}