package net.orgiu.tests.networkrequest

import android.content.Context
import android.content.Context.*
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData

class Manager(context: Context,
              private val factory: Factory = Factory(),
              private val callback: Callback = Callback()) {

    private val connectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    val result : LiveData<NetworkResult>
        get() = callback.result

    fun registerCallback() {
        val request = factory.wifiRequest()
        connectivityManager.registerNetworkCallback(request, callback)
    }

    fun unregisterCallback() {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}