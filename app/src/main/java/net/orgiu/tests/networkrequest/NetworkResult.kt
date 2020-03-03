package net.orgiu.tests.networkrequest

import net.orgiu.tests.R

enum class NetworkResult(val colorResId: Int, val messageResId: Int) {

    CONNECTED(R.color.colorConnected, R.string.messageConnected),
    DISCONNECTED(R.color.colorDisconnected, R.string.messageDisconnected),
    DISCONNECTING(R.color.colorDisconnecting, R.string.messageDisconnecting)

}