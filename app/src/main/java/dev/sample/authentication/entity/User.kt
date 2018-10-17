package dev.sample.authentication.entity

import android.net.Uri

data class User(val id: String, val name: String, var photoUrl: Uri? = null) {
    companion object {
        private const val ANONYMOUS_USER_ID = "______no-user______"
        private const val ANONYMOUS_USER_NAME = "Anonymous"

        fun getAnonymous() : User = User(ANONYMOUS_USER_ID, ANONYMOUS_USER_NAME)
        fun getAnonymousId() : String = ANONYMOUS_USER_ID
        fun getAnonymousName() : String = ANONYMOUS_USER_NAME
    }

    fun isLoggedIn(): Boolean = id != ANONYMOUS_USER_ID
}