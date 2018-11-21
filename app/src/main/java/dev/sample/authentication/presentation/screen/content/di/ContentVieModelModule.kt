package dev.sample.authentication.presentation.screen.content.di

import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dev.sample.authentication.BuildConfig
import dev.sample.authentication.data.DefaultNewsRepository
import dev.sample.authentication.data.NewsRepository
import dev.sample.authentication.data.UserRepository
import dev.sample.authentication.data.remote.NewsApiRequestInterceptor
import dev.sample.authentication.data.remote.NewsApiService
import dev.sample.authentication.data.remote.RemoteNewsRepository
import dev.sample.authentication.presentation.screen.content.usecases.DefaultFetchPage
import dev.sample.authentication.presentation.screen.content.usecases.FetchPage
import dev.sample.authentication.domain.usecases.DefaultFetchUser
import dev.sample.authentication.domain.usecases.FetchUser
import dev.sample.authentication.domain.usecases.ObserveAuthState
import dev.sample.authentication.domain.usecases.ObserveFirebaseAuthState
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module(includes = [ContentFragmentModule::class])
class ContentViewModelModule {

    @Provides
    fun provideUserFetcher(userRepository: UserRepository): FetchUser = DefaultFetchUser(userRepository)

    @Provides
    fun provideAuthStateObserver(firebaseAuth: FirebaseAuth): ObserveAuthState = ObserveFirebaseAuthState(firebaseAuth)

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(NewsApiRequestInterceptor(BuildConfig.NEWS_API_KEY))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create()))
            .baseUrl(BuildConfig.NEWS_API_BASE_URL)
            .build()

    @Provides
    fun provideRemoteNewsRepository(retrofit: Retrofit): RemoteNewsRepository = RemoteNewsRepository(retrofit.create(NewsApiService::class.java))

    @Provides
    fun provideNewsRepository(remoteRepository: RemoteNewsRepository): NewsRepository = DefaultNewsRepository(remoteRepository)

    @Provides
    fun providePageFetcher(newsRepository: NewsRepository): FetchPage = DefaultFetchPage(newsRepository)

}
