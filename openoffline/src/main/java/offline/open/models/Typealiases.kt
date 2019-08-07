package offline.open.models

import androidx.lifecycle.MutableLiveData

typealias Overview = List<ArticleOverview>

typealias MutableOverview = MutableLiveData<Lce<List<ArticleOverview>>>

typealias OverviewDispatcher = LceDispatcher<Overview>