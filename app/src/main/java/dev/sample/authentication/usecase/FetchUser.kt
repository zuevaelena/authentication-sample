package dev.sample.authentication.usecase

import androidx.lifecycle.LiveData
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.entity.User
import javax.inject.Inject


/**
 * Interactor (use case) for retrieving user's data.
 */
interface FetchUser {
    fun execute(): LiveData<User>
}

class DefaultFetchUser @Inject constructor(private val repository: UserRepository) : FetchUser {

    override fun execute() = repository.fetchUser(true)

}