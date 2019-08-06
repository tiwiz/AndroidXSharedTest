package offline.open.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias Overview = List<ArticleOverview>

typealias MutableOverview = MutableLiveData<Lce<List<ArticleOverview>>>

typealias OverviewDispatcher = LceDispatcher<Overview>

typealias Detail = LiveData<Lce<ArticleDetails>>

typealias MutableDetail = MutableLiveData<Lce<ArticleDetails>>