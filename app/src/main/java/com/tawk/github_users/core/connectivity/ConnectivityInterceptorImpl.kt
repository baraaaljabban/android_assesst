package com.tawk.github_users.core.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.tawk.github_users.core.excepition.NetworkConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * [ConnectivityInterceptorImpl] will throw [NetworkConnectivityException] if the device
 * was offline
 *
 * if the device was online then it will proceed with the request
 *
 * this class will be used as Interceptor with retrofit
 */
class ConnectivityInterceptorImpl (context:Context): ConnectivityInterceptor {
    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        return if(!isOnline()){
            throw NetworkConnectivityException()
        }else{
            chain.proceed(chain.request())
        }
     }
    private fun isOnline(): Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw      = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }

    }
}