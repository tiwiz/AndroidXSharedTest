package offline.open.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import offline.open.models.ArticleDetails
import offline.open.repository.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {

    val article = MutableLiveData<ArticleDetails>()

    fun loadArticle(articleId: String) {

        viewModelScope.launch {
            val articleDetails = repository.getArticleDetails(articleId)
            article.postValue(articleDetails)
        }
    }
}