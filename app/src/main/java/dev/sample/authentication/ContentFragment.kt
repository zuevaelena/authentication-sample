package dev.sample.authentication

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_content.do_logout
import kotlinx.android.synthetic.main.fragment_content.goto_login
import kotlinx.android.synthetic.main.fragment_content.username
import com.firebase.ui.auth.AuthUI
import dev.sample.authentication.model.User

class ContentFragment : Fragment() {
    private lateinit var viewModel: ContentViewModel

    companion object {
        private const val SIGN_IN_REQUEST = 19

        @JvmStatic
        fun newInstance() = ContentFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)

        viewModel.getUser().observe(this, Observer { user -> initUi(user) })

        viewModel.init()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            SIGN_IN_REQUEST -> {
                if(resultCode == RESULT_OK) {
                    viewModel.init()

                } else {
                    // TODO hmm?
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun initUi(user: User) {
        username.text = user.name

        do_logout.visibility = if(user.isLoggedIn()) View.VISIBLE else View.GONE
        goto_login.visibility = if(user.isLoggedIn()) View.GONE else View.VISIBLE

        if(user.isLoggedIn()) {
            // TODO attach do_logout on click
            do_logout.setOnClickListener { _ ->
                AuthUI.getInstance()
                        .signOut(requireContext())
                        .addOnCompleteListener {
                            viewModel.init()
                        }
            }

        } else {
            goto_login.setOnClickListener {
                // Choose authentication providers
                val providers = listOf(
                        AuthUI.IdpConfig.GoogleBuilder().build() // Google auth

                        // TODO implement bellow authentication ways, consider if need to add more
                        //, AuthUI.IdpConfig.EmailBuilder().build()
                        //, AuthUI.IdpConfig.FacebookBuilder().build()
                        //, AuthUI.IdpConfig.TwitterBuilder().build()
                )

                // Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        SIGN_IN_REQUEST)
            }
        }
    }
}
