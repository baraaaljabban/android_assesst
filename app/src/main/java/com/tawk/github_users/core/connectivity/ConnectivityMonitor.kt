package com.tawk.github_users.core.connectivity

 import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class ConnectivityState(val isConnected: Boolean)


class ConnectivityMonitor @Inject constructor(
    private val connectivityManager: ConnectivityManager
)  {
    private val networkCallback: ConnectivityManager.NetworkCallback

    private val _connectivityState = MutableStateFlow(ConnectivityState(false))
    val connectivityState: StateFlow<ConnectivityState> = _connectivityState

    init {
        networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                _connectivityState.value = ConnectivityState(true)
            }

            override fun onLost(network: Network) {
                _connectivityState.value = ConnectivityState(false)
            }
        }
        registerNetworkCallback()
    }

    private fun registerNetworkCallback() {
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
