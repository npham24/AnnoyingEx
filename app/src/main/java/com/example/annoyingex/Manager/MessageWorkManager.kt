package com.example.annoyingex.Manager

import android.content.Context
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.annoyingex.MessageWorkContent
import java.util.concurrent.TimeUnit

class MessageWorkManager(private val context: Context) {
    private var workManager = WorkManager.getInstance(context)

    companion object {
        const val SPAM_TAG = "SPAM_TAG"
    }

    fun spam() {
        if (!workRunning()) {
            val constraints = Constraints.Builder()
                .setRequiresCharging(false)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<MessageWorkContent>(20, TimeUnit.MINUTES)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .addTag(SPAM_TAG)
                .build()


            workManager.enqueue(workRequest)
        }
    }

    private fun workRunning(): Boolean {
        return when (workManager.getWorkInfosByTag(SPAM_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }

    fun block() {
        workManager.cancelAllWorkByTag(SPAM_TAG)
    }
}