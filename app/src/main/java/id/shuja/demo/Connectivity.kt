package id.shuja.demo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Connectivity {

	fun getConnectivityManager(context: Context): ConnectivityManager {
		return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	}

	fun isOnline(context: Context): Boolean {
		val connect = Connectivity.getConnectivityManager(context)
		val networkInfo: NetworkInfo? = connect.activeNetworkInfo
		return networkInfo?.isConnected == true
	}

}