package dev.sample.authentication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class DataLoadViewModel : ViewModel(), CoroutineScope {

    private val loadingJob: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = loadingJob

    override fun onCleared() {
        super.onCleared()

        if (loadingJob.isActive) loadingJob.cancel()
    }
}
