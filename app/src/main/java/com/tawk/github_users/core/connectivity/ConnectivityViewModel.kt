package com.tawk.github_users.core.connectivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ConnectivityViewModel] will be used as an abstract view model so that
 * whenever a connection is restored will try again to do the request
 *
 * @param [connectivityMonitor]  to collect connectivity State
 *
 * once the connection is restored then [onConnectivityRestored] will be called
 */
@HiltViewModel
open class ConnectivityViewModel @Inject constructor(
    private val connectivityMonitor: ConnectivityMonitor
) : ViewModel() {
    private val _connectivityState = MutableStateFlow(ConnectivityState(false))
    val connectivityState: StateFlow<ConnectivityState> = _connectivityState

    init {
        _connectivityState.value = ConnectivityState(true)
        startConnectivityMonitoring()
    }

    private fun startConnectivityMonitoring() {
        viewModelScope.launch (Dispatchers.IO){
            connectivityMonitor.connectivityState.collect {
                _connectivityState.value = it
                if (it.isConnected) {
                    onConnectivityRestored()
                }
            }
        }
    }

    protected open fun onConnectivityRestored() {
    }

    override fun onCleared() {
        super.onCleared()
        connectivityMonitor.unregisterNetworkCallback()
    }
}
