package com.jorfald.friender.utils
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


object NetworkChecker {

        fun isOnline(context: Context): Boolean {

            val manager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


            val network = manager.activeNetwork ?: return false

            val activeNetwork = manager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }

        }
    }