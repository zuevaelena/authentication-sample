package dev.sample.authentication.di.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.sample.authentication.features.bottommenu.di.BottomMenuFragmentModule
import dev.sample.authentication.features.bottommenu.di.BottomMenuViewModelModule
import dev.sample.authentication.features.bottommenu.ui.BottomMenuFragment
import dev.sample.authentication.features.content.di.ContentFragmentModule
import dev.sample.authentication.features.content.di.ContentViewModelModule
import dev.sample.authentication.features.content.ui.ContentFragment

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
