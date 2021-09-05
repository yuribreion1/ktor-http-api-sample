[![official JetBrains project](https://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)


# Creating an HTTP API with Ktor

This repository is my version of the correspondent hands-on lab [Creating HTTP APIs](https://ktor.io/docs/creating-http-apis.html).

The project consist in APIs to Customer and Orders with CRUD functionalities. 

## API tests

All tests were executed using Postman, and was [documented](https://documenter.getpostman.com/view/4692048/U16gNmfL) and its results.

## Enhancements

Different of the version available on the hands-on, was used a different approach in some API's as

- A route to add new orders `createOrderRoute`
- Ability to add more than one order on the same request
- Ability to receive a list of customers on `POST` to add them
- Usage of `sumOf` instead of `map` on `OrderRoutes.kt`