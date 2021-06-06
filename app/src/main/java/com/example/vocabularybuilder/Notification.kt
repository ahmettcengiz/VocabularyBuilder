package com.example.vocabularybuilder


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import java.util.*

class Notification : Service() {


    var timer: Timer? = null
    var timerTask: TimerTask? = null
    var TAG = "Timers"
    var Your_X_SECS = 25
    override fun onBind(arg0: Intent): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        var word: String? = intent.getStringExtra("word")

        Log.e(TAG, "onStartCommand")
        super.onStartCommand(intent, flags, startId)
        if (word != null) {
            startTimer(word)
        }
        return START_STICKY
    }

    override fun onCreate() {
        Log.e(TAG, "onCreate")

    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy")
        stopTimerTask()
        super.onDestroy()
    }

    //we are going to use a handler to be able to run in our TimerTask
    val handler = Handler()
    fun startTimer(word :String) {
        timer = Timer()
        initializeTimerTask(word)
        timer!!.schedule(timerTask, 60000, (Your_X_SECS * 1000).toLong()) //
    }

    fun stopTimerTask() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    fun initializeTimerTask(word:String ) {
        timerTask = object : TimerTask() {
            override fun run() {
                handler.post { createNotification(word) }
            }
        }
    }

    private fun createNotification(word: String) {
        val intentMemorized = Intent(applicationContext, Notification::class.java)
        val intentTransition = PendingIntent.getActivity(this, 0, intentMemorized, PendingIntent.FLAG_UPDATE_CURRENT)

        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(applicationContext, default_notification_channel_id)
        mBuilder.setContentTitle("Notification")
        mBuilder.setContentText("Learn word '"+word +"' Now")
        mBuilder.setTicker("Learn Word Now")
        mBuilder.setSmallIcon(R.drawable.ic_launcher_foreground)
        mBuilder.setAutoCancel(true)
        mBuilder.addAction(
            R.drawable.ic_launcher_foreground,
            "Done",
            intentTransition
        )
        mBuilder.addAction(
            R.drawable.ic_launcher_foreground,
            "Remind Later",
            intentTransition
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            assert(mNotificationManager != null)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }
        assert(mNotificationManager != null)
        mNotificationManager.notify(System.currentTimeMillis().toInt(), mBuilder.build())
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "10001"
        private const val default_notification_channel_id = "default"

    }
}