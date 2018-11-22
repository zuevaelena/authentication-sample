package dev.sample.authentication.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.data.FirebaseUserRepository
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.domain.usecases.DefaultFetchUser
import dev.sample.authentication.domain.usecases.FetchUser
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideUserRepository(firebaseAuth: FirebaseAuth): UserRepository =
            FirebaseUserRepository(firebaseAuth)

    @Singleton
    @Provides
    fun provideUserFetcher(userRepository: UserRepository): FetchUser = DefaultFetchUser(userRepository)
}
