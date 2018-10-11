package dev.sample.authentication.di

import dagger.Component
import dev.sample.authentication.ui.content.ContentFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, ContentViewModelModule::class])
interface ContentFragmentComponent {

    fun inject(contentFragment: ContentFragment)

}