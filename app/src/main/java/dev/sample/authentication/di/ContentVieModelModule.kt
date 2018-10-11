package dev.sample.authentication.di

import dagger.Module
import dagger.Provides
import dev.sample.authentication.FirebaseSignInHandler
import dev.sample.authentication.SignInHandler

@Module
class ContentViewModelModule {

    @Provides
    fun provideSignInHandler() : SignInHandler = FirebaseSignInHandler()

}