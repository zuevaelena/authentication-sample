package dev.sample.authentication.feature.content.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.usecase.DefaultFetchUser
import dev.sample.authentication.usecase.FetchUser
import dev.sample.authentication.usecase.ObserveAuthState
import dev.sample.authentication.usecase.ObserveFirebaseAuthState

@Module(includes = [ContentFragmentModule::class])
class ContentViewModelModule {

    @Provides
    fun provideUserFetcher(userRepository: UserRepository): FetchUser = DefaultFetchUser(userRepository)

    @Provides
    fun provideAuthStateObserver(firebaseAuth: FirebaseAuth): ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth)

}
