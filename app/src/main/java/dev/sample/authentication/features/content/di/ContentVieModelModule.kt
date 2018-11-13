package dev.sample.authentication.features.content.di

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
import dev.sample.authentication.features.content.usecases.FetchNewsApiPage
import dev.sample.authentication.features.content.usecases.FetchPage
import dev.sample.authentication.usecases.DefaultFetchUser
import dev.sample.authentication.usecases.FetchUser
import dev.sample.authentication.usecases.ObserveAuthState
import dev.sample.authentication.usecases.ObserveFirebaseAuthState
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
            .addInterceptor(NewsApiRequestInterceptor("38d07d37d36b4fc8bdc0a29a20a84428")) // TODO move it from here
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
            .baseUrl("https://newsapi.org/v2/") // TODO move it from here
            .build()

    @Provides
    fun provideRemoteNewsRepository(retrofit: Retrofit): RemoteNewsRepository = RemoteNewsRepository(retrofit.create(NewsApiService::class.java))

    @Provides
    fun provideNewsRepository(remoteRepository: RemoteNewsRepository): NewsRepository = DefaultNewsRepository(remoteRepository)

    @Provides
    fun providePageFetcher(newsRepository: NewsRepository): FetchPage = FetchNewsApiPage(newsRepository)

}
