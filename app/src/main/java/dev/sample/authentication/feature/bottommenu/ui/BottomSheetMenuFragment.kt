package dev.sample.authentication.feature.bottommenu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dev.sample.authentication.R
import kotlinx.android.synthetic.main.fragment_bottom_sheet_menu.bottom_sheet_navigation

class BottomSheetMenuFragment : BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom_sheet_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bottom_sheet_navigation.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.goto_content -> Toast.makeText(requireContext(), "Go to content view", Toast.LENGTH_SHORT).show()
                R.id.goto_user_info -> Toast.makeText(requireContext(), "Go to user info view", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}
