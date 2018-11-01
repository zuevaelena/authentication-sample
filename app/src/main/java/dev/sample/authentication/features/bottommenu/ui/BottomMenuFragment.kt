package dev.sample.authentication.features.bottommenu.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dev.sample.authentication.R
import dev.sample.authentication.databinding.FragmentBottomSheetMenuBinding
import dev.sample.authentication.databinding.WidgetUserCardBinding
import dev.sample.authentication.features.bottommenu.usecase.SignInError
import dev.sample.authentication.features.bottommenu.usecase.SignInFailure
import dev.sample.authentication.features.bottommenu.usecase.SignOutError
import dev.sample.authentication.ui.DaggerBottomSheetDialogFragment
import javax.inject.Inject


class BottomMenuFragment : DaggerBottomSheetDialogFragment() {

    companion object {
        private const val SIGN_IN_REQUEST = 42
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var headerViewModel: BottomMenuViewModel
    private lateinit var menuBinding: FragmentBottomSheetMenuBinding
    private lateinit var headerBinding: WidgetUserCardBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        menuBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_bottom_sheet_menu
                , container
                , false)
        menuBinding.setLifecycleOwner(this)

        headerBinding = DataBindingUtil.inflate(inflater
                , R.layout.widget_user_card
                , menuBinding.root as ViewGroup
                , false)
        headerBinding.setLifecycleOwner(this)

        headerViewModel = ViewModelProviders.of(this, viewModelFactory).get(BottomMenuViewModel::class.java)
        headerBinding.apply {
            viewModel = headerViewModel

            gotoLogin.setOnClickListener { gotoLogIn() }
            doLogout.setOnClickListener { gotoLogOut() }
        }

        menuBinding.bottomSheetNavigation.addHeaderView(headerBinding.root)

        return menuBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        menuBinding.bottomSheetNavigation.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.goto_content -> Toast.makeText(requireContext(), "Go to content view", Toast.LENGTH_SHORT).show()
                R.id.goto_user_info -> Toast.makeText(requireContext(), "Go to user info view", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            SIGN_IN_REQUEST -> processLogInResult(resultCode, data)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun gotoLogIn() {
        startActivityForResult(headerViewModel.getSignInIntent(), SIGN_IN_REQUEST)
    }


    private fun processLogInResult(resultCode: Int, data: Intent?) {
        val signInResult = headerViewModel.getSignInResult(resultCode, data)

        if (signInResult is SignInFailure) {
            val errorMessageResource: Int = when (signInResult.error) {
                SignInError.NO_NETWORK -> R.string.error_no_network
                else -> R.string.error_default
            }
            Toast.makeText(requireContext(), errorMessageResource
                    , Toast.LENGTH_LONG)
                    .show()
        }
    }

    private fun gotoLogOut() {
        AlertDialog.Builder(requireContext())
                .setCancelable(false)
                .setTitle(R.string.logout_confirmation_title)
                .setMessage(R.string.logout_confirmation_message)
                .setNegativeButton(R.string.logout_confirmation_no) { dialog, _ -> dialog.dismiss() }
                .setPositiveButton(R.string.logout_confirmation_yes) { dialog, _ ->
                    dialog.dismiss()
                    doLogOut()
                }
                .show()
    }

    private fun doLogOut() {
        val signOutResult = headerViewModel.signOut(requireContext())

        if(signOutResult is SignOutError) {
            Toast.makeText(requireContext(), R.string.error_default
                    , Toast.LENGTH_LONG)
                    .show()
        }
    }

}
