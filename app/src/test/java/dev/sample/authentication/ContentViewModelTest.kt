package dev.sample.authentication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.sample.authentication.features.content.ui.ContentViewModel
import org.junit.Rule
import org.junit.rules.TestRule

/**
 * Unit tests for [ContentViewModel].
 */
class ContentViewModelTest {

    private lateinit var viewModel: ContentViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

}
