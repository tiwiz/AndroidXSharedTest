package net.orgiu.tests.networkrequest

import android.net.NetworkCapabilities
import android.net.NetworkRequest

class Factory {

    fun wifiRequest() : NetworkRequest =
        NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
}