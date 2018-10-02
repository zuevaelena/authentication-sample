package dev.sample.authentication.model

data class User(val userId: String, val name: String) {

    companion object {
        private const val ANONYMOUS_USER_ID = "______no-user______"
        private const val ANONYMOUS_USER_NAME = "Unknown"

        fun anonymousUserId() = ANONYMOUS_USER_ID
        fun anonymousUserName() = ANONYMOUS_USER_NAME
    }

    fun isLoggedIn(): Boolean = userId != ANONYMOUS_USER_ID

}