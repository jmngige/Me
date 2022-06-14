package com.starsolns.me.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.starsolns.me.R
import com.starsolns.me.views.host.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun createNotification(name: String, email: String){
        createNotificationChannel()

        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.profileFragment)
            .createPendingIntent()



        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(name)
            .setContentText(email)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_profile)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        val manager = NotificationManagerCompat.from(context)
        manager.notify(NOTIFICATION_ID, notification)

    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                enableVibration(true)
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }


    companion object {
        const val CHANNEL_ID = "user_profile_id"
        const val NOTIFICATION_ID = 101
        const val CHANNEL_NAME = "user_profile"

    }

}