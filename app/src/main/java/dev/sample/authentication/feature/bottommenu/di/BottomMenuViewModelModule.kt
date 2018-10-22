package dev.sample.authentication.feature.bottommenu.di

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.data.FirebaseUserRepository
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.feature.bottommenu.usecase.DoFirebaseLogOut
import dev.sample.authentication.feature.bottommenu.usecase.DoLogOut
import dev.sample.authentication.feature.bottommenu.usecase.MakeFirebaseSignIntent
import dev.sample.authentication.feature.bottommenu.usecase.MakeSignInIntent
import dev.sample.authentication.usecase.DefaultFetchUser
import dev.sample.authentication.usecase.FetchUser
import dev.sample.authentication.usecase.ObserveAuthState
import dev.sample.authentication.usecase.ObserveFirebaseAuthState
import javax.inject.Singleton

@Module
class BottomMenuViewModelModule {
    @Singleton
    @Provides
    fun provideSignInIntentMaker(): MakeSignInIntent = MakeFirebaseSignIntent()

    @Singleton
    @Provides
    fun provideLogOutDoer(authUi: AuthUI): DoLogOut = DoFirebaseLogOut(authUi)

    @Singleton
    @Provides
    fun provideUserFetcher(userRepository: UserRepository): FetchUser = DefaultFetchUser(userRepository)

    @Singleton
    @Provides
    fun provideAuthStateObserver(firebaseAuth: FirebaseAuth): ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth)

    @Singleton
    @Provides
    fun provideUserRepository(): UserRepository = FirebaseUserRepository()

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseAuthUi(): AuthUI = AuthUI.getInstance()
}
