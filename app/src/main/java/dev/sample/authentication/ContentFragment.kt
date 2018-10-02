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
import dev.sample.authentication.model.User

class ContentFragment : Fragment() {
    private lateinit var viewModel: ContentViewModel

    private var userObserver: Observer<User> = Observer { user -> updateUi(user) }

    companion object {
        private const val SIGN_IN_REQUEST = 19

        @JvmStatic
        fun newInstance() = ContentFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        viewModel.getUser().observe(this, userObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            SIGN_IN_REQUEST -> {
                if(resultCode != RESULT_OK) {
                    // TODO handle an error?
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.getUser().removeObserver(userObserver)
    }

    private fun updateUi(user: User) {
        username.text = user.name

        do_logout.visibility = if(user.isLoggedIn()) View.VISIBLE else View.GONE
        goto_login.visibility = if(user.isLoggedIn()) View.GONE else View.VISIBLE

        if(user.isLoggedIn()) {
            do_logout.setOnClickListener { _ ->
                viewModel.logout(requireContext())
            }

        } else {
            goto_login.setOnClickListener {
                startActivityForResult(viewModel.getSignInIntent(), SIGN_IN_REQUEST)
            }
        }
    }
}
