package dev.sample.authentication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sample.authentication.data.remote.RemoteNewsRepository
import dev.sample.authentication.entities.News
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.List

interface NewsRepository {
    fun getPage(): LiveData<List<News>>
}

class DefaultNewsRepository @Inject constructor(val remoteRepository: RemoteNewsRepository) : NewsRepository {
    private val observableData: MutableLiveData<List<News>> = MutableLiveData()

    private val mockList: List<News> = ArrayList<News>().apply {
        add(News("Prince Charles won't 'meddle' when he's king"
                , "I'm not that stupid - the Prince of Wales says he'll have to stop campaigning when he's monarch."
                , Date()
                , ""
                , "https://ichef.bbci.co.uk/news/1024/branded_news/A232/production/_104222514_mediaitem104222331.jpg"))
        add(News("In Wisconsin, Walker falls to lingering resentments and fresh impatience"
                , "Follow the StarTribune for the news, photos and videos from the Twin Cities and beyond. From the Twin Cities and beyond."
                , Date()
                , "Rochelle Olson"
                , "http://stmedia.stimg.co/Walker14.JPG?h=630&w=1200&fit=crop&bg=999&crop=faces"))
        add(News("Marijuana Stocks Spiked After Jeff Sessions Resigned"
                , "The former attorney general was a major political roadblock to further cannabis legalization."
                , Date()
                , "Amanda Mull"
                , "https://cdn.theatlantic.com/assets/media/img/mt/2018/11/RTX5U8L5-1/facebook.jpg?1541634926"))
        add(News("McCarthy launches bid for House minority leader"
                , "Conservative hard-liner Jim Jordan is challenging him from the right."
                , Date()
                , "https://www.facebook.com/rachael.bade.9"
                , "https://static.politico.com/e6/25/b99f7b214a6d8b68004c1189266a/181107-kevin-mccarthy-gty-773.jpg"))
        add(News("Early numbers suggest voter turnout soared in the 2018 midterms"
                , "We won’t know the exact figures for a while."
                , Date()
                , "Emily Stewart"
                , "https://cdn.vox-cdn.com/thumbor/8-RkmFvTO5u5iDOB1-POdijV-ZU=/0x350:4928x2930/fit-in/1200x630/cdn.vox-cdn.com/uploads/chorus_asset/file/13402277/1058207668.jpg.jpg"))

        add(News("Umbau in der Kraftwerksparte belastet Siemens-Geschäft"
                , ""
                , Date()
                , "WELT"
                , "https://www.welt.de/img/newsticker/dpa_nt/infoline_nt/wirtschaft_nt/mobile183475766/2931352957-ci16x9-w1200/urn-newsml-dpa-com-20090101-181108-99-720186-large-4-3-jpg.jpg"))

    }

    override fun getPage(): LiveData<List<News>> {
        GlobalScope.launch {
            observableData.postValue(remoteRepository.getPage())
        }

        return observableData
    }
}
