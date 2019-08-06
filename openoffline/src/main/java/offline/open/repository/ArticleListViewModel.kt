package offline.open.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import offline.open.models.LceError
import offline.open.models.LceLoading
import offline.open.models.LceSuccess
import offline.open.models.MutableOverview


class ArticleListViewModel(private val repository: Repository) : ViewModel() {

    val overview = MutableOverview()

    fun fetch() {
        overview.postValue(LceLoading())
        viewModelScope.launch {
            try {
                val data = repository.getOverview()
                overview.postValue(LceSuccess(data))
            } catch (e: Exception) {
                overview.postValue(LceError(e))
            }
        }
    }
}