package dev.sample.authentication.presentation.customview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PaginationOnScrollListener(private val loadMore: () -> Unit) : RecyclerView.OnScrollListener() {
    private var isLoadingNecessary = true

    var isInProcess = false
        private set

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (!isLoadingNecessary || isInProcess) return

        val itemsCount = recyclerView.adapter!!.itemCount
        val currentFocusedPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if (currentFocusedPosition > 0 && currentFocusedPosition > itemsCount - 2) {
            isInProcess = true
            loadMore()
        }
    }

    fun finishPageLoading() {
        isInProcess = false
    }

    fun setLoadingNecessity(isNecessary: Boolean) {
        isLoadingNecessary = isNecessary
    }

}
