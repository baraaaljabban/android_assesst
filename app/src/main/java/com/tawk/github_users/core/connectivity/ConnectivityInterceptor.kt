package com.tawk.github_users.core.connectivity

import com.tawk.github_users.core.excepition.NetworkConnectivityException
import okhttp3.Interceptor

/**
 * [ConnectivityInterceptor] will throw [NetworkConnectivityException] if there was
 * no connectivity
 *
 * if the device was online then it will proceed with the request
 *
 * this class will be used as Interceptor with retrofit
 */
interface ConnectivityInterceptor : Interceptor {
}