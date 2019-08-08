package offline.open.detail

import androidx.lifecycle.MutableLiveData

class ShareProvider {

    val sharedLink = MutableLiveData<String>()

    fun onShare(url: String) {
        sharedLink.postValue(url)
    }
}