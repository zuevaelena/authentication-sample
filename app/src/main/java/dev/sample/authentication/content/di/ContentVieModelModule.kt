package dev.sample.authentication.content.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.FirebaseSignInHandler
import dev.sample.authentication.SignInHandler
import dev.sample.authentication.content.usecase.FetchFirebaseUser
import dev.sample.authentication.content.usecase.FetchUser
import dev.sample.authentication.content.usecase.ObserveAuthState
import dev.sample.authentication.content.usecase.ObserveFirebaseAuthState
import dev.sample.authentication.data.UserRepository
import javax.inject.Singleton

@Module(includes = [ContentDataModule::class])
class ContentViewModelModule {

    @Singleton
    @Provides
    fun provideSignInHandler() : SignInHandler = FirebaseSignInHandler()

    @Singleton
    @Provides
    fun provideUserFetcher(userRepository : UserRepository) : FetchUser = FetchFirebaseUser(userRepository)

    @Singleton
    @Provides
    fun provideAuthStateObserver(firebaseAuth : FirebaseAuth, userFetcher: FetchUser) : ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth, userFetcher)

}