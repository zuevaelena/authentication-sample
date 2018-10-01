package dev.sample.authentication.model

data class User(
        var name: String = "Unknown"
        , var isLoggedIn: Boolean = false)