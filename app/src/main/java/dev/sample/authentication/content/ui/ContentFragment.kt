package dev.sample.authentication.content.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.sample.authentication.R
import dev.sample.authentication.databinding.FragmentContentBinding
import dev.sample.authentication.content.di.DaggerContentFragmentComponent
import dev.sample.authentication.entity.User
import javax.inject.Inject


/**
 * UI-controller of Content screen.
 */
class ContentFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = ContentFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ContentViewModel

    private lateinit var binding: FragmentContentBinding

    private var userObserver: Observer<User> = Observer { user -> binding.user = user }

    override fun onAttach(context: Context?) {
        DaggerContentFragmentComponent.builder().build().inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ContentViewModel::class.java]

        // TODO reconsider this
        viewModel.userData.observe(this, userObserver)

        binding.apply {
            setLifecycleOwner(this@ContentFragment)

            user = viewModel.userData.value

            // TODO do it properly
            Glide.with(this@ContentFragment)
                    .setDefaultRequestOptions(RequestOptions().apply{
                        //placeholder(R.drawable.ic_account_circle) // TODO show something while loading
                        error(R.drawable.ic_account_circle)
                        circleCrop()
                    })
                    .load(viewModel.userData.value?.photoUrl)
                    .into(userPhoto)
        }

        return binding.root
    }

}
