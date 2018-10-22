package dev.sample.authentication.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.sample.authentication.feature.bottommenu.di.BottomMenuFragmentModule
import dev.sample.authentication.feature.bottommenu.di.BottomMenuViewModelModule
import dev.sample.authentication.feature.bottommenu.ui.BottomMenuFragment
import dev.sample.authentication.feature.content.di.ContentFragmentModule
import dev.sample.authentication.feature.content.di.ContentViewModelModule
import dev.sample.authentication.feature.content.ui.ContentFragment

@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        ContentFragmentModule::class,
        ContentViewModelModule::class
    ])
    internal abstract fun bindContentFragment(): ContentFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [
        BottomMenuFragmentModule::class,
        BottomMenuViewModelModule::class
    ])
    internal abstract fun bindBottomMenuFragment(): BottomMenuFragment

}
