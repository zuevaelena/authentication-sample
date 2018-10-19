package dev.sample.authentication.feature.content.di

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.feature.content.usecase.DoFirebaseLogOut
import dev.sample.authentication.feature.content.usecase.DoLogOut
import dev.sample.authentication.feature.content.usecase.MakeFirebaseSignIntent
import dev.sample.authentication.feature.content.usecase.MakeSignInIntent
import dev.sample.authentication.feature.content.usecase.DefaultFetchUser
import dev.sample.authentication.feature.content.usecase.FetchUser
import dev.sample.authentication.feature.content.usecase.ObserveAuthState
import dev.sample.authentication.feature.content.usecase.ObserveFirebaseAuthState
import dev.sample.authentication.data.UserRepository
import javax.inject.Singleton

@Module(includes = [ContentDataModule::class])
class ContentViewModelModule {

    @Singleton
    @Provides
    fun provideSignInIntentMaker() : MakeSignInIntent = MakeFirebaseSignIntent()

    @Singleton
    @Provides
    fun provideLogOutDoer(authUi: AuthUI) : DoLogOut = DoFirebaseLogOut(authUi)

    @Singleton
    @Provides
    fun provideUserFetcher(userRepository : UserRepository) : FetchUser = DefaultFetchUser(userRepository)

    @Singleton
    @Provides
    fun provideAuthStateObserver(firebaseAuth : FirebaseAuth) : ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth)

}
