package dev.sample.authentication.presentation.screen.bottommenu.di

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.presentation.screen.bottommenu.usecase.FirebaseSignIn
import dev.sample.authentication.presentation.screen.bottommenu.usecase.FirebaseSignOut
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignIn
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignOut
import dev.sample.authentication.domain.usecases.DefaultFetchUser
import dev.sample.authentication.domain.usecases.FetchUser
import dev.sample.authentication.domain.usecases.ObserveAuthState
import dev.sample.authentication.domain.usecases.ObserveFirebaseAuthState

@Module
class BottomMenuViewModelModule {

    @Provides
    fun provideFirebaseAuthUi(): AuthUI = AuthUI.getInstance()

    @Provides
    fun provideSignIn(authUi: AuthUI): SignIn = FirebaseSignIn(authUi)

    @Provides
    fun provideSignOut(authUi: AuthUI): SignOut = FirebaseSignOut(authUi)

    @Provides
    fun provideUserFetcher(userRepository: UserRepository): FetchUser = DefaultFetchUser(userRepository)

    @Provides
    fun provideAuthStateObserver(firebaseAuth: FirebaseAuth): ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth)
}
