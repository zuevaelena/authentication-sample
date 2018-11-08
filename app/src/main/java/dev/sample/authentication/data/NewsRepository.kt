package dev.sample.authentication.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sample.authentication.entities.News
import dev.sample.authentication.entities.NewsSource
import java.util.Date

interface NewsRepository {
    fun getPage() : LiveData<List<News>>
}

class NewsApiRepository : NewsRepository {
    private val observableData: MutableLiveData<List<News>> = MutableLiveData()

    private val mockList: List<News> = ArrayList<News>().apply {
        add(News( NewsSource("cnn", "CNN")
                , "White House pulls CNN's Jim Acosta's pass after contentious news conference"
                , ""
                , Date() ))
        add(News( NewsSource("", "Startribune.com")
                , "In Wisconsin, Walker falls to lingering resentments and fresh impatience"
                , "Follow the StarTribune for the news, photos and videos from the Twin Cities and beyond."
                , Date()
                , "Rochelle Olson"
                , Uri.parse("http://stmedia.stimg.co/Walker14.JPG?h=630&w=1200&fit=crop&bg=999&crop=faces") ))
        add(News( NewsSource("", "Nj.com")
                , "Kim leads MacArthur as counting continues in key congressional race"
                , "Challenger Andy Kim is looking to unseat incumbent GOP Rep. Tom MacArthur."
                , Date()
                , "Bill Duhart | For NJ.com"
                , Uri.parse("https://image.nj.com/home/njo-media/width620/img/burlington_impact/photo/25011678-standard.jpg") ))
    }

    override fun getPage(): LiveData<List<News>> {
        observableData.apply { postValue(mockList) }

        return observableData
    }
}
