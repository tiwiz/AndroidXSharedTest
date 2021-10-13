package net.orgiu.tests.networkrequest

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.MutableLiveData
import net.orgiu.tests.networkrequest.NetworkResult.*

class Callback : ConnectivityManager.NetworkCallback() {

    val result = MutableLiveData<NetworkResult>()

    override fun onLost(network: Network) {
        result.postValue(DISCONNECTED)
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        result.postValue(DISCONNECTING)
    }

    override fun onAvailable(network: Network) {
        result.postValue(CONNECTED)
    }
}