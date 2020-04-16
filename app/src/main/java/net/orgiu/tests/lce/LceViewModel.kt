package net.orgiu.tests.lce

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LceViewModel : ViewModel() {

    private val mutableLiveData = MutableLiveData<LCE<Boolean>>()

    val result: LiveData<LCE<Boolean>>
        get() = mutableLiveData

    fun runFunction(shouldSucceed: Boolean, delayInSeconds: Int) {
        mutableLiveData.postValue(LCE.Loading)

        viewModelScope.launch {
            delay(delayInSeconds * 1000L)
            if (shouldSucceed) {
                mutableLiveData.postValue(LCE.Complete(true))
            } else {
                mutableLiveData.postValue(LCE.Error(Exception()))
            }
        }

    }
}