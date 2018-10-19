package dev.sample.authentication.feature.content.di

import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dev.sample.authentication.data.FirebaseUserRepository
import dev.sample.authentication.data.UserRepository
import javax.inject.Singleton

@Module
class ContentDataModule {

    @Singleton
    @Provides
    fun provideUserRepository() : UserRepository = FirebaseUserRepository()

    @Singleton
    @Provides
    fun provideFirebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseAuthUi() : AuthUI = AuthUI.getInstance()

}
