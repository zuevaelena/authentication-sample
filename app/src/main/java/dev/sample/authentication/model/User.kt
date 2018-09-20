package dev.sample.authentication.model

data class User(
        val name: String
        , var isLoggedIn: Boolean = false)