package dev.sample.authentication.features.content.ui

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
import dev.sample.authentication.entities.News
import javax.inject.Inject


/**
 * UI-controller of Content screen.
 */
class ContentFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ContentViewModel
    private lateinit var binding: FragmentContentBinding
    private lateinit var adapter: ContentAdapter

    private var newsObserver: Observer<List<News>> = Observer { result -> adapter.loadNews(result) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_content, container, false)
        binding.setLifecycleOwner(this@ContentFragment)

        adapter = ContentAdapter()

        binding.contentList.layoutManager = LinearLayoutManager(requireContext())
        binding.contentList.adapter = adapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ContentViewModel::class.java)

        // TODO put it back after implement data binding for recycler view
        //binding.viewModel = viewModel

        viewModel.newsData.observe(this, newsObserver)

        return binding.root
    }

}
