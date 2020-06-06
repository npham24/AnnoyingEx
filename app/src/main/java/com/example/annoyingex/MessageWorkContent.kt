package com.example.annoyingex

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MessageWorkContent (private val context: Context, workParams: WorkerParameters): Worker(context , workParams) {
    override fun doWork(): Result {
        Log.i("npham", "doing work")
        (context as ExApp).messageNotiManager.beginNotify()
        return Result.success()
    }
}