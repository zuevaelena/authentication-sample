package dev.sample.authentication.feature.content.di

import dagger.Component
import dev.sample.authentication.di.ViewModelModule
import dev.sample.authentication.feature.content.ui.ContentFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, ContentFragmentModule::class, ContentViewModelModule::class])
interface ContentFragmentComponent {

    fun inject(contentFragment: ContentFragment)

}
