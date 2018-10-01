package dev.sample.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val ARG_USER_ID = "user_id"

class ContentFragment : Fragment() {
    private var userId: String? = null
    private lateinit var viewModel: ContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(ARG_USER_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userId: String? = arguments?.getString(ARG_USER_ID)
        viewModel = ContentViewModel(userId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(userId: String?) =
                ContentFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_USER_ID, userId)
                    }
                }
    }
}
