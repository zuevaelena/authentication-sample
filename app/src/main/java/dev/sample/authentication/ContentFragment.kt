package dev.sample.authentication

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_content.do_logout
import kotlinx.android.synthetic.main.fragment_content.goto_login
import kotlinx.android.synthetic.main.fragment_content.username
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth


private const val ARG_USER_ID = "user_id"

class ContentFragment : Fragment() {
    private var userId: String? = null
    private lateinit var viewModel: ContentViewModel

    companion object {
        private const val SIGN_IN_REQUEST = 19

        @JvmStatic
        fun newInstance(userId: String?) =
                ContentFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_USER_ID, userId)
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // TODO not necessary anymore?
            userId = it.getString(ARG_USER_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userId: String? = arguments?.getString(ARG_USER_ID)

        viewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        viewModel.init(FirebaseAuth.getInstance().currentUser)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            SIGN_IN_REQUEST -> {
                if(resultCode == RESULT_OK) {
                    viewModel.init(FirebaseAuth.getInstance().currentUser)
                    initUi()

                } else {
                    // TODO hmm?
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    private fun initUi() {
        username.text = viewModel.getUser().name

        do_logout.visibility = if(viewModel.getUser().isLoggedIn()) View.VISIBLE else View.GONE
        goto_login.visibility = if(viewModel.getUser().isLoggedIn()) View.GONE else View.VISIBLE

        if(viewModel.getUser().isLoggedIn()) {
            // TODO attach do_logout on click
            do_logout.setOnClickListener { _ ->
                AuthUI.getInstance()
                        .signOut(requireContext())
                        .addOnCompleteListener {
                            viewModel.init(FirebaseAuth.getInstance().currentUser)
                            initUi()
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
