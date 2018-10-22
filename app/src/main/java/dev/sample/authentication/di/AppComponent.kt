package dev.sample.authentication.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.sample.authentication.SampleAuthApplication
import dev.sample.authentication.di.fragment.FragmentBindingModule
import dev.sample.authentication.di.viewmodel.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    AppModule::class,
    FragmentBindingModule::class
])
interface AppComponent : AndroidInjector<SampleAuthApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<SampleAuthApplication>()
}
