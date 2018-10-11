package dev.sample.authentication.ui.content

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dev.sample.authentication.R
import dev.sample.authentication.databinding.FragmentContentBinding
import dev.sample.authentication.di.DaggerContentFragmentComponent
import javax.inject.Inject


/**
 * UI-controller of Content screen.
 */
class ContentFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ContentViewModel

    companion object {
        @JvmStatic
        fun newInstance() = ContentFragment()
    }

    override fun onAttach(context: Context?) {
        DaggerContentFragmentComponent.builder().build().inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[ContentViewModel::class.java]

        val binding: FragmentContentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false)
        binding.apply {
            user = viewModel.user
        }

        return binding.root
    }

}
