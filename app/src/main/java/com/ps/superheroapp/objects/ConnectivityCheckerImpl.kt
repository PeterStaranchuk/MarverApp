package com.ps.superheroapp.objects

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class ConnectivityCheckerImpl @Inject constructor(private val context: Context) : ConnectivityChecker {

    override fun isOffline(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val netInfo = cm!!.activeNetworkInfo
        return !(netInfo != null && netInfo.isConnected)
    }

}