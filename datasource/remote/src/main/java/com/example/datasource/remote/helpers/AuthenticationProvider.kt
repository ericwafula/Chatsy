package com.example.datasource.remote.helpers

object AuthenticationProvider {
    var token: String? = null
        private set

    fun setToken(value: String) {
        token = value
    }
}
