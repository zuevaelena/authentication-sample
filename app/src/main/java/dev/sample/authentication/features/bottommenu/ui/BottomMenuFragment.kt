package dev.sample.authentication.features.bottommenu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dev.sample.authentication.R
import dev.sample.authentication.databinding.FragmentBottomSheetMenuBinding
import dev.sample.authentication.databinding.WidgetUserCardBinding
import dev.sample.authentication.ui.DaggerBottomSheetDialogFragment
import javax.inject.Inject

class BottomMenuFragment : DaggerBottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var menuViewModel: BottomMenuViewModel
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

        menuViewModel = ViewModelProviders.of(this, viewModelFactory).get(BottomMenuViewModel::class.java)
        headerBinding.apply {
            viewModel = menuViewModel

            gotoLogin.setOnClickListener { startActivity(viewModel?.getSignInIntent()) }
            doLogout.setOnClickListener { viewModel?.logOut(requireContext()) }
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

}
