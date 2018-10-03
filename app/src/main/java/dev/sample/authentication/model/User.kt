package dev.sample.authentication.model

import android.net.Uri

data class User(val userId: String, val name: String, val photoUrl: Uri) {

    companion object {
        private const val ANONYMOUS_USER_ID = "______no-user______"
        private const val ANONYMOUS_USER_NAME = "Anonymous"

        fun anonymousUserId() = ANONYMOUS_USER_ID
        fun anonymousUserName() = ANONYMOUS_USER_NAME
    }

    fun isLoggedIn(): Boolean = userId != ANONYMOUS_USER_ID

}