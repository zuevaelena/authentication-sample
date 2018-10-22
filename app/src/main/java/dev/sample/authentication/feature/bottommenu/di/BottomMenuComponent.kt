package dev.sample.authentication.feature.bottommenu.di

import dagger.Component
import dev.sample.authentication.di.ViewModelModule
import dev.sample.authentication.feature.bottommenu.ui.BottomSheetMenuFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, BottomMenuFragmentModule::class, BottomMenuViewModelModule::class])
interface BottomMenuComponent {
    fun inject(bottomSheetMenuFragment: BottomSheetMenuFragment)
}
