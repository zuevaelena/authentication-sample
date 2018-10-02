package dev.sample.authentication.model

data class User(
        val userId: String = NOT_LOGGED_IN_USER_ID
        , val name: String = NOT_LOGGED_IN_USER_NAME) {

    companion object {
        private const val NOT_LOGGED_IN_USER_ID = "______no-user______"
        private const val NOT_LOGGED_IN_USER_NAME = "Unknown"

        fun emptyUserId() = NOT_LOGGED_IN_USER_ID
        fun emptyUserName() = NOT_LOGGED_IN_USER_NAME
    }

    fun isLoggedIn(): Boolean = userId != NOT_LOGGED_IN_USER_ID

}