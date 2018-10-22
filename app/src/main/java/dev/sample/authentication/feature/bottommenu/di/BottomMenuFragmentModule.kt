package dev.sample.authentication.feature.bottommenu.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.sample.authentication.di.ViewModelKey
import dev.sample.authentication.feature.bottommenu.ui.BottomMenuViewModel

@Module
internal abstract class BottomMenuFragmentModule {

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [BottomMenuViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(BottomMenuViewModel::class)
    abstract fun bindBottomMenuViewModel(viewModel: BottomMenuViewModel): ViewModel

}
