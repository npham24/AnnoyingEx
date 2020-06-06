package com.example.annoyingex.Manager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.annoyingex.ExApp
import com.example.annoyingex.MainActivity
import com.example.annoyingex.R
import kotlin.random.Random

class MessageNotiManager(private val context: Context) {
    private val notificationManager: NotificationManager =
        getSystemService(context, NotificationManager::class.java) as NotificationManager

    init {
        createNotificationChannel()
    }

    companion object {
        const val MSG_CHANNEL = "MSG_CHANNEL"
    }

    fun beginNotify() {
        val listOfMsgs = (context as ExApp).listOfMsgs
        val mainActivity = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingActivityIntent = PendingIntent.getActivity(context, 0,
            mainActivity, PendingIntent.FLAG_UPDATE_CURRENT)

        listOfMsgs?.let {
            val pos = Random.nextInt(it.size)
            val notification = createNoti(it[pos], pendingActivityIntent)
            notificationManager.notify(pos, notification)
        } ?: run{
            val notification = createNoti("unable to retrieve message", pendingActivityIntent)
            notificationManager.notify(-1, notification)
        }
    }

    private fun createNoti(msgContent: String, pendingIntent: PendingIntent): Notification {
        return NotificationCompat.Builder(context, MSG_CHANNEL)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .setCategory("message")
            .setContentTitle("Karen")
            .setContentText(msgContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Karen Calling"
            val descriptionText = "I want to speak to the manager"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(MSG_CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }
}