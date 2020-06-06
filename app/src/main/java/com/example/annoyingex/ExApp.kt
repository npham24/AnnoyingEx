package com.example.annoyingex

import android.app.Application
import com.example.annoyingex.Manager.ApiManager
import com.example.annoyingex.Manager.MessageWorkManager
import com.example.annoyingex.Manager.MessageNotiManager

class ExApp: Application() {
    lateinit var apiManager: ApiManager
        private set
    lateinit var messageWorkManager: MessageWorkManager
        private set
    lateinit var messageNotiManager: MessageNotiManager
        private set
    var listOfMsgs: List<String>? = null

    override fun onCreate() {
        super.onCreate()

        apiManager = ApiManager(this)
        messageWorkManager = MessageWorkManager(this)
        messageNotiManager = MessageNotiManager(this)
    }
}