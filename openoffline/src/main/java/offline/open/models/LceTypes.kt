package offline.open.models

import androidx.lifecycle.Observer

interface LceView<TYPE> {

    fun onLoading()

    fun onSuccess(data: TYPE)

    fun onError(throwable: Throwable)
}

sealed class Lce<TYPE>(
    val loading: Boolean = false,
    open val data: TYPE? = null,
    open val error: Throwable? = null)

class LceLoading<TYPE> : Lce<TYPE>(true, null, null)
data class LceSuccess<TYPE>(override val data: TYPE) : Lce<TYPE>(false, data, null)
data class LceError<TYPE>(override val error: Throwable) : Lce<TYPE>(false, null, error)

open class LceDispatcher<TYPE>(private val view: LceView<TYPE>) : Observer<Lce<TYPE>> {
    override fun onChanged(lce: Lce<TYPE>?) {
        when (lce) {
            is LceLoading -> view.onLoading()
            is LceSuccess -> view.onSuccess(lce.data)
            is LceError -> view.onError(lce.error)
        }
    }
}