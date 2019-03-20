package hanami.com.testdemo

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import com.breeze.xqqrcode.R
import com.unity3d.services.UnityServices
import com.unity3d.services.banners.IUnityBannerListener
import com.unity3d.services.banners.UnityBanners
import com.unity3d.services.monetization.IUnityMonetizationListener
import com.unity3d.services.monetization.UnityMonetization
import com.unity3d.services.monetization.placementcontent.core.PlacementContent
import hanami.com.testdemo.receiver.AntiReceiver
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), IUnityMonetizationListener, IUnityBannerListener {

    private val myHandler = MyHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_click.setOnClickListener {
            Log.i(TAG,UnityMonetization.isReady("enterAd").toString())
            UnityBanners.setBannerListener(this)
            UnityBanners.loadBanner(this,"enterAd")
        }
        btn_next.setOnClickListener {
            val intent = Intent(this,NextActivity::class.java)
            startActivity(intent)
        }
        myHandler.sendEmptyMessageAtTime(12, 10000)
        myHandler.removeCallbacksAndMessages(null)
        UnityMonetization.initialize (this, "3050455", this, true)
    }

    class MyHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            Log.i("TestDemo", "do something")
        }
    }

    @SuppressLint("ShortAlarm")
    private fun sendAlarm() {
        val intent = Intent(this, AntiReceiver::class.java)
        intent.action = "com.hanami.alarm"
        intent.putExtra("name", "hamami")
        intent.putExtra("age", 27)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.add(Calendar.SECOND, 1)
        val sender = PendingIntent.getBroadcast(this, 123, intent, 0)
        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 10 * 1000, sender)
    }

    override fun onUnityBannerLoaded(p0: String?, p1: View?) {
        Log.i(TAG,"onUnityBannerLoaded")
    }

    override fun onUnityBannerShow(p0: String?) {
        Log.i(TAG,"onUnityBannerShow")
    }

    override fun onUnityBannerClick(p0: String?) {
        Log.i(TAG,"onUnityBannerClick")
    }

    override fun onUnityBannerHide(p0: String?) {
        Log.i(TAG,"onUnityBannerHide")
    }

    override fun onUnityBannerError(p0: String?) {
        Log.i(TAG, "onUnityBannerError : $p0")
    }

    override fun onUnityBannerUnloaded(p0: String?) {
        Log.i(TAG,"onUnityBannerUnloaded")
    }


    override fun onPlacementContentStateChange(p0: String?, p1: PlacementContent?, p2: UnityMonetization.PlacementContentState?, p3: UnityMonetization.PlacementContentState?) {
        Log.i(TAG,"onPlacementContentStateChange : $p0")
    }

    override fun onPlacementContentReady(p0: String?, p1: PlacementContent?) {
        Log.i(TAG,"onPlacementContentReady : $p0")
    }

    override fun onUnityServicesError(p0: UnityServices.UnityServicesError?, p1: String?) {
        Log.i(TAG,"onUnityServicesError : $p0")
    }

    companion object {
        const val TAG = "UnityBanner"
    }

}
