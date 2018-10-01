package dev.sample.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_content.do_logout
import kotlinx.android.synthetic.main.fragment_content.goto_login
import kotlinx.android.synthetic.main.fragment_content.username


private const val ARG_USER_ID = "user_id"

class ContentFragment : Fragment() {
    private var userId: String? = null
    private lateinit var viewModel: ContentViewModel

    companion object {
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
            userId = it.getString(ARG_USER_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userId: String? = arguments?.getString(ARG_USER_ID)

        viewModel = ViewModelProviders.of(this).get(ContentViewModel::class.java)
        viewModel.init(userId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onStart() {
        super.onStart()

        username.text = viewModel.getUser().name
        do_logout.visibility = if(viewModel.getUser().isLoggedIn()) View.VISIBLE else View.GONE
        goto_login.visibility = if(viewModel.getUser().isLoggedIn()) View.GONE else View.VISIBLE
    }
}
