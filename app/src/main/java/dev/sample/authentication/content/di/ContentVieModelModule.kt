package dev.sample.authentication.content.di

import dagger.Module
import dagger.Provides
import dev.sample.authentication.FirebaseSignInHandler
import dev.sample.authentication.SignInHandler
import dev.sample.authentication.data.FirebaseUserRepository
import dev.sample.authentication.data.UserRepository
import javax.inject.Singleton

@Module
class ContentViewModelModule {

    @Singleton
    @Provides
    fun provideSignInHandler() : SignInHandler = FirebaseSignInHandler()

    @Singleton
    @Provides
    fun provideUserRepository() : UserRepository = FirebaseUserRepository()

}