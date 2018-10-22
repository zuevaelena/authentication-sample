package dev.sample.authentication.feature.content.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.sample.authentication.entity.User
import dev.sample.authentication.usecase.FetchUser
import dev.sample.authentication.usecase.ObserveAuthState
import javax.inject.Inject

/**
 * Data-handling business logic for Content screen.
 */
class ContentViewModel @Inject constructor(
        private val fetchUser: FetchUser
        , observeAuthState: ObserveAuthState) : ViewModel() {

    var userData: LiveData<User>
        private set

    init {
        userData = fetchUser.execute()

        observeAuthState.execute { userData = fetchUser.execute() }
    }

}
