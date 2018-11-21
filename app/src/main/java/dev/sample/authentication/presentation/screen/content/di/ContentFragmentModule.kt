package dev.sample.authentication.presentation.screen.content.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dev.sample.authentication.di.viewmodel.ViewModelKey
import dev.sample.authentication.presentation.screen.content.ui.ContentViewModel

@Module
internal abstract class ContentFragmentModule {

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [ContentViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(ContentViewModel::class)
    abstract fun bindContentViewModel(viewModel: ContentViewModel): ViewModel

}
