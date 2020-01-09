package net.orgiu.tests.networkrequest

import android.net.ConnectivityManager
import android.net.Network
import android.util.Log

class Callback : ConnectivityManager.NetworkCallback() {

    override fun onLost(network: Network?) {
        Log.d(TAG, "Network lost")
    }

    override fun onLosing(network: Network?, maxMsToLive: Int) {
        Log.d(TAG, "Losing network in the next $maxMsToLive MS")
    }

    override fun onAvailable(network: Network?) {
        Log.d(TAG, "Network $network is available")
    }

    companion object {
        private const val TAG = "NetworkCallback"
    }
}