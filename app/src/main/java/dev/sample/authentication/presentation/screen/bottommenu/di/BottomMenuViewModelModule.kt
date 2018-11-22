package dev.sample.authentication.presentation.screen.bottommenu.di

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.domain.usecases.FetchUser
import dev.sample.authentication.domain.usecases.FirebaseSignIn
import dev.sample.authentication.domain.usecases.FirebaseSignOut
import dev.sample.authentication.domain.usecases.ObserveAuthState
import dev.sample.authentication.domain.usecases.ObserveFirebaseAuthState
import dev.sample.authentication.domain.usecases.SignIn
import dev.sample.authentication.domain.usecases.SignOut

@Module
class BottomMenuViewModelModule {

    @Provides
    fun provideFirebaseAuthUi(): AuthUI = AuthUI.getInstance()

    @Provides
    fun provideSignIn(authUi: AuthUI, fetchUser: FetchUser): SignIn = FirebaseSignIn(authUi, fetchUser)

    @Provides
    fun provideSignOut(authUi: AuthUI, fetchUser: FetchUser): SignOut = FirebaseSignOut(authUi, fetchUser)

    @Provides
    fun provideAuthStateObserver(firebaseAuth: FirebaseAuth): ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth)
}
