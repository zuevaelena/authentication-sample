package dev.sample.authentication.feature.content.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerFragment
import dev.sample.authentication.R
import dev.sample.authentication.databinding.FragmentContentBinding
import dev.sample.authentication.entity.User
import javax.inject.Inject


/**
 * UI-controller of Content screen.
 */
class ContentFragment : DaggerFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = ContentFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ContentViewModel
    private lateinit var binding: FragmentContentBinding

    private var userObserver: Observer<User> = Observer { _ -> updateUi() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ContentViewModel::class.java]

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false)
        binding.setLifecycleOwner(this@ContentFragment)

        updateUi()

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        viewModel.userData.observe(this, userObserver)
    }

    override fun onStop() {
        super.onStop()

        viewModel.userData.removeObserver(userObserver)
    }

    private fun updateUi() {
        binding.apply {
            user = viewModel.userData.value

            // TODO do it properly
            Glide.with(this@ContentFragment)
                    .setDefaultRequestOptions(RequestOptions().apply {
                        //placeholder(R.drawable.ic_account_circle) // TODO show something while loading
                        error(R.drawable.ic_account_circle)
                        circleCrop()
                    })
                    .load(viewModel.userData.value?.photoUrl)
                    .into(userPhoto)
        }
    }

}
