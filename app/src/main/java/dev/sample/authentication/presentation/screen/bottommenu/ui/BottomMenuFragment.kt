package dev.sample.authentication.presentation.screen.bottommenu.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dev.sample.authentication.R
import dev.sample.authentication.databinding.FragmentBottomSheetMenuBinding
import dev.sample.authentication.databinding.WidgetUserCardBinding
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignInError
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignInFailure
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignOutError
import dev.sample.authentication.presentation.screen.bottommenu.usecase.SignOutResult
import dev.sample.authentication.presentation.customview.DaggerBottomSheetDialogFragment
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

    private var signOutObserver: Observer<SignOutResult> = Observer { result -> processLogOutResult(result) }

    override fun getTheme(): Int = R.style.BottomMenu

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        menuBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_bottom_sheet_menu
                , container
                , false)
        menuBinding.setLifecycleOwner(this)

        headerViewModel = ViewModelProviders.of(this, viewModelFactory).get(BottomMenuViewModel::class.java)
        headerViewModel.signoutData.observe(this, signOutObserver)

        headerBinding = DataBindingUtil.inflate(inflater
                , R.layout.widget_user_card
                , menuBinding.root as ViewGroup
                , false)
        headerBinding.setLifecycleOwner(this)
        headerBinding.viewModel = headerViewModel
        bindHeaderButtonsClicks()

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

    private fun bindHeaderButtonsClicks() {
        headerBinding.gotoLogin.setOnClickListener { gotoLogIn() }
        headerBinding.doLogout.setOnClickListener { gotoLogOut() }
    }

    private fun unbindHeaderButtonsClicks() {
        headerBinding.gotoLogin.setOnClickListener { }
        headerBinding.doLogout.setOnClickListener { }
    }

    private fun gotoLogIn() {
        unbindHeaderButtonsClicks()
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

        bindHeaderButtonsClicks()
    }

    private fun gotoLogOut() {
        unbindHeaderButtonsClicks()

        AlertDialog.Builder(requireContext())
                .setCancelable(false)
                .setTitle(R.string.logout_confirmation_title)
                .setMessage(R.string.logout_confirmation_message)
                .setNegativeButton(R.string.logout_confirmation_no) { dialog, _ ->
                    dialog.dismiss()
                    bindHeaderButtonsClicks()
                }
                .setPositiveButton(R.string.logout_confirmation_yes) { dialog, _ ->
                    dialog.dismiss()
                    headerViewModel.signOut(requireContext())
                }
                .show()
    }

    private fun processLogOutResult(signOutResult: SignOutResult) {
        if(signOutResult is SignOutError) {
            Toast.makeText(requireContext(), R.string.error_default
                    , Toast.LENGTH_LONG)
                    .show()
        }
        bindHeaderButtonsClicks()
    }

}

