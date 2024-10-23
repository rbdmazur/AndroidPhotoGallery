package com.example.photogallery

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

const val NOTIFICATION_CHANEL_ID = "pexels_poll"

class PhotoGalleryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferencesRepository.initialize(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_chanel)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val chanel = NotificationChannel(NOTIFICATION_CHANEL_ID, name, importance)
            val notificationManager: NotificationManager =
                getSystemService(NotificationManager :: class.java)
            notificationManager.createNotificationChannel(chanel)
        }
    }
}