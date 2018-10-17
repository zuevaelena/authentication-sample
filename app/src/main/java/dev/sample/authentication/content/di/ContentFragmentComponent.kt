package dev.sample.authentication.content.di

import dagger.Component
import dev.sample.authentication.content.ui.ContentFragment
import dev.sample.authentication.di.ViewModelModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, ContentViewModelModule::class])
interface ContentFragmentComponent {

    fun inject(contentFragment: ContentFragment)

}