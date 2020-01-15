package id.shuja.demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

	private lateinit var receiver: NetworkReceiver

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onStart() {
		super.onStart()

		val conn = Connectivity.getConnectivityManager(this)
		val activateInfo: NetworkInfo = conn.activeNetworkInfo

		if (activateInfo.isConnected && activateInfo != null) {
			wifiConnected = activateInfo.type == ConnectivityManager.TYPE_WIFI
			mobileConnected = activateInfo.type == ConnectivityManager.TYPE_MOBILE
		} else {
			wifiConnected = false
			mobileConnected = false
		}


		if (refreshBrowser) {
			if (wifiConnected || mobileConnected) {
				Toast.makeText(this, "Refresh Internet", Toast.LENGTH_LONG).show()
			} else {
				Toast.makeText(this, "Error Page", Toast.LENGTH_LONG).show()
			}
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		this.unregisterReceiver(receiver)
	}

	companion object {
		const val WIFI ="Wi-Fi"
		const val MOBILE ="Mobile"

		private var wifiConnected = false
		private var mobileConnected = false

		var refreshBrowser = true
	}

	class NetworkReceiver : BroadcastReceiver() {

		override fun onReceive(context: Context, intent: Intent) {

			val conn = Connectivity.getConnectivityManager(context)
			val networkInfo: NetworkInfo? = conn.activeNetworkInfo

			if (networkInfo?.type == ConnectivityManager.TYPE_WIFI) {
				refreshBrowser = true
				Toast.makeText(context, "Wifi is Success", Toast.LENGTH_LONG).show()
			} else if (networkInfo?.type == ConnectivityManager.TYPE_MOBILE) {
				refreshBrowser = true
				Toast.makeText(context, "Mobile Internet is Success", Toast.LENGTH_LONG).show()
			} else {
				refreshBrowser = false
				Toast.makeText(context, "no connection", Toast.LENGTH_LONG).show()
			}
		}

	}
}
