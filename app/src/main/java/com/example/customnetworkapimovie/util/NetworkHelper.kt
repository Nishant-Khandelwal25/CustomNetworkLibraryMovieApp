package com.example.customnetworkapimovie.util

import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@Suppress("DEPRECATION")
class NetworkHelperImpl @Inject constructor(@ApplicationContext private val context: Context) :
    NetworkHelper {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isOnline(): Boolean {
        return connectivityManager.activeNetworkInfo?.isConnected ?: false
    }

    override fun getInternetMessage(): String {
        return "Please check your internet connection"
    }
}

interface NetworkHelper {
    fun isOnline(): Boolean
    fun getInternetMessage(): String
}