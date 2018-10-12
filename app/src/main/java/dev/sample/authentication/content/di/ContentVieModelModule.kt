package dev.sample.authentication.content.di

import dagger.Module
import dagger.Provides
import dev.sample.authentication.FirebaseSignInHandler
import dev.sample.authentication.SignInHandler

@Module
class ContentViewModelModule {

    @Provides
    fun provideSignInHandler() : SignInHandler = FirebaseSignInHandler()

}