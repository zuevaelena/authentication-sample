package dev.sample.authentication.feature.bottommenu.di

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.feature.bottommenu.usecase.DoFirebaseLogOut
import dev.sample.authentication.feature.bottommenu.usecase.DoLogOut
import dev.sample.authentication.feature.bottommenu.usecase.MakeFirebaseSignIntent
import dev.sample.authentication.feature.bottommenu.usecase.MakeSignInIntent
import dev.sample.authentication.usecase.DefaultFetchUser
import dev.sample.authentication.usecase.FetchUser
import dev.sample.authentication.usecase.ObserveAuthState
import dev.sample.authentication.usecase.ObserveFirebaseAuthState

@Module
class BottomMenuViewModelModule {

    @Provides
    fun provideFirebaseAuthUi(): AuthUI = AuthUI.getInstance()

    @Provides
    fun provideSignInIntentMaker(): MakeSignInIntent = MakeFirebaseSignIntent()

    @Provides
    fun provideLogOutDoer(authUi: AuthUI): DoLogOut = DoFirebaseLogOut(authUi)

    @Provides
    fun provideUserFetcher(userRepository: UserRepository): FetchUser = DefaultFetchUser(userRepository)

    @Provides
    fun provideAuthStateObserver(firebaseAuth: FirebaseAuth): ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth)
}
