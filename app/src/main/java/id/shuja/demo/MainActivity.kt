package id.shuja.demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

	private lateinit var receiver: NetworkReceiver

	companion object {
		private lateinit var tvNoInternet:TextView

		var refreshBrowser = true
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		tvNoInternet = findViewById(R.id.tv_no_internet)

	}

	override fun onStart() {
		super.onStart()
		receiver = NetworkReceiver()
		registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

	}

	override fun onDestroy() {
		super.onDestroy()
		this.unregisterReceiver(receiver)
	}

	class NetworkReceiver : BroadcastReceiver() {

		override fun onReceive(context: Context, intent: Intent) {

			val conn = Connectivity.getConnectivityManager(context)
			val networkInfo: NetworkInfo? = conn.activeNetworkInfo

			if (networkInfo?.type == ConnectivityManager.TYPE_WIFI) {
				refreshBrowser = true
				Toast.makeText(context, "Wifi is Success", Toast.LENGTH_LONG).show()
				tvNoInternet.setText("wifi internet")
			} else if (networkInfo?.type == ConnectivityManager.TYPE_MOBILE) {
				refreshBrowser = true
				Toast.makeText(context, "Mobile Internet is Success", Toast.LENGTH_LONG).show()
				tvNoInternet.setText("mobile internet")
			} else {
				refreshBrowser = false
				Toast.makeText(context, "no connection", Toast.LENGTH_LONG).show()
				tvNoInternet.setText("no internet")


			}
		}

	}
}
