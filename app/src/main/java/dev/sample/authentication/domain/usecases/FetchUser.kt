package dev.sample.authentication.domain.usecases

import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.domain.model.User
import javax.inject.Inject

/**
 * Interactor (use case) for retrieving user's data.
 */
interface FetchUser {
    fun execute(forceRefresh: Boolean = false): User
}

class DefaultFetchUser @Inject constructor(private val repository: UserRepository) : FetchUser {
    private var userCache: User

    init {
        userCache = repository.fetchUser()
    }

    override fun execute(forceRefresh: Boolean) : User {
        if(forceRefresh) {
            userCache = repository.fetchUser()
        }
        return userCache
    }
}
