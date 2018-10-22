package dev.sample.authentication.feature.bottommenu.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.sample.authentication.R
import dev.sample.authentication.entity.User
import dev.sample.authentication.feature.bottommenu.di.DaggerBottomMenuComponent
import kotlinx.android.synthetic.main.fragment_bottom_sheet_menu.bottom_sheet_navigation
import kotlinx.android.synthetic.main.widget_user_card.do_logout
import kotlinx.android.synthetic.main.widget_user_card.goto_login
import kotlinx.android.synthetic.main.widget_user_card.user_name
import kotlinx.android.synthetic.main.widget_user_card.user_photo
import javax.inject.Inject

class BottomSheetMenuFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: BottomMenuViewModel

    private var userObserver: Observer<User> = Observer { _ -> updateHeader() }

    override fun onAttach(context: Context?) {
        DaggerBottomMenuComponent.builder().build().inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BottomMenuViewModel::class.java)

        val menuView: View = inflater.inflate(R.layout.fragment_bottom_sheet_menu, container, false)

        return menuView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottom_sheet_navigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.goto_content -> Toast.makeText(requireContext(), "Go to content view", Toast.LENGTH_SHORT).show()
                R.id.goto_user_info -> Toast.makeText(requireContext(), "Go to user info view", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()

        updateHeader()

        viewModel.userData.observe(this, userObserver)
    }

    override fun onPause() {
        viewModel.userData.removeObserver(userObserver)

        super.onPause()
    }

    private fun updateHeader() {
        if (user_name == null) return

        user_name.text = viewModel.userData.value?.name ?: ""

        goto_login.visibility = if (viewModel.userData.value?.isLoggedIn() == true) View.GONE else View.VISIBLE
        do_logout.visibility = if (viewModel.userData.value?.isLoggedIn() == true) View.VISIBLE else View.GONE

        goto_login.setOnClickListener { startActivity(viewModel.getSignInIntent()) }
        do_logout.setOnClickListener { viewModel.logOut(requireContext()) }

        Glide.with(this)
                .setDefaultRequestOptions(RequestOptions().apply {
                    //placeholder(R.drawable.ic_account_circle) // TODO show something while loading
                    error(R.drawable.ic_account_circle)
                    circleCrop()
                })
                .load(viewModel.userData.value?.photoUrl ?: Uri.EMPTY)
                .into(user_photo)
    }

}
