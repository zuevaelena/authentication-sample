package dev.sample.authentication.presentation.screen.content.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerFragment
import dev.sample.authentication.R
import dev.sample.authentication.databinding.FragmentContentBinding
import dev.sample.authentication.domain.model.User
import javax.inject.Inject


/**
 * UI-controller of Content screen.
 *
 * Implementing [DaggerFragment] in order to use injections, which are defined at
 * [dev.sample.authentication.di.fragment.FragmentBindingModule]#bindContentFragment
 */
class ContentFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ContentViewModel
    private lateinit var binding: FragmentContentBinding
    private lateinit var adapter: NewsPagedListAdapter

    private var authStateObserver: Observer<User> = Observer {
        onAuthStateChange()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setupDataBinding(inflater, container)
        setupContentList()

        viewModel.userData.observe(this, authStateObserver)

        return binding.root
    }

    override fun onDestroyView() {
        viewModel.userData.removeObserver(authStateObserver)

        super.onDestroyView()
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false)
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel
    }

    private fun setupContentList() {
        binding.contentList.layoutManager = LinearLayoutManager(requireContext())

        adapter = NewsPagedListAdapter()
        binding.contentList.adapter = adapter

        // TODO setup initial preloader
        // TODO setup page loading indicator

        // TODO setup page refresher
        binding.refresher.setOnRefreshListener {
        }
    }

    // TODO consider to use it again
    private fun onNewsDataChange() {
        if (binding.refresher.isRefreshing) {
            binding.refresher.isRefreshing = false
        }

    }

    private fun onAuthStateChange() {
        // TODO retake all data on auth change
    }

}
