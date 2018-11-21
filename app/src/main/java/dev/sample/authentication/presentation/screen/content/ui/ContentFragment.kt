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
import dev.sample.authentication.domain.model.News
import dev.sample.authentication.presentation.customview.PaginationOnScrollListener
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
    private lateinit var adapter: ContentAdapter
    private lateinit var scrollListener: PaginationOnScrollListener

    private var newsDataObserver: Observer<List<News>> = Observer { newData ->
        onNewsDataChange(newData.size)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setupDataBinding(inflater, container)
        setupContentList()

        viewModel.newsData.observe(this, newsDataObserver)

        return binding.root
    }

    override fun onDestroyView() {
        viewModel.newsData.removeObserver(newsDataObserver)

        super.onDestroyView()
    }

    private fun setupDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false)
        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel
    }

    private fun setupContentList() {
        binding.contentList.layoutManager = LinearLayoutManager(requireContext())

        adapter = ContentAdapter()
        binding.contentList.adapter = adapter

        scrollListener = PaginationOnScrollListener {
            adapter.pagingMode()

            viewModel.newsNextPage()
        }
        binding.contentList.addOnScrollListener(scrollListener)

        binding.refresher.setOnRefreshListener {
            adapter.refreshMode()

            scrollListener.setLoadingNecessity(true)

            binding.viewModel?.refreshNewsData()
        }
    }

    private fun onNewsDataChange(newDataSize: Int) {
        if(binding.refresher.isRefreshing) {
            binding.refresher.isRefreshing = false
        }

        if(newDataSize == 0) {
            scrollListener.setLoadingNecessity(false)
        }

        if(scrollListener.isInProcess) {
            scrollListener.finishPageLoading()
        }
    }

}
