package dev.sample.authentication.feature.content.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.support.DaggerFragment
import dev.sample.authentication.R
import dev.sample.authentication.entity.User
import kotlinx.android.synthetic.main.fragment_content.user_name
import kotlinx.android.synthetic.main.fragment_content.user_photo
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

    private var userObserver: Observer<User> = Observer { _ -> updateUi() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ContentViewModel::class.java]

        // TODO put back data binding

        val contentView: View = inflater.inflate(R.layout.fragment_content, container, false)

        // TODO put it back
        // updateUi()

        return contentView
    }

    override fun onStart() {
        super.onStart()

        // TODO delete it
        updateUi()

        viewModel.userData.observe(this, userObserver)
    }

    override fun onStop() {
        super.onStop()

        viewModel.userData.removeObserver(userObserver)
    }

    private fun updateUi() {
        if (user_name == null) return

        user_name.text = viewModel.userData.value?.name ?: ""

        // TODO do it properly
        Glide.with(this@ContentFragment)
                .setDefaultRequestOptions(RequestOptions().apply {
                    //placeholder(R.drawable.ic_account_circle) // TODO show something while loading
                    error(R.drawable.ic_account_circle)
                    circleCrop()
                })
                .load(viewModel.userData.value?.photoUrl ?: Uri.EMPTY)
                .into(user_photo)
    }

}
