package dev.sample.authentication.model

import android.net.Uri

data class User(val id: String, val name: String, var photoUrl: Uri) {
    companion object {
        private const val ANONYMOUS_USER_ID = "______no-user______"
        private const val ANONYMOUS_USER_NAME = "Anonymous"

        fun anonymousUserId() = ANONYMOUS_USER_ID
        fun anonymousUserName() = ANONYMOUS_USER_NAME
    }

    fun isLoggedIn(): Boolean = id != ANONYMOUS_USER_ID
}