package dev.sample.authentication.features.content.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.usecases.DefaultFetchUser
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.usecases.ObserveAuthState
import dev.sample.authentication.usecases.ObserveFirebaseAuthState

@Module(includes = [ContentFragmentModule::class])
class ContentViewModelModule {

    @Provides
    fun provideUserFetcher(userRepository: UserRepository): FetchUser = DefaultFetchUser(userRepository)

    @Provides
    fun provideAuthStateObserver(firebaseAuth: FirebaseAuth): ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth)

}
