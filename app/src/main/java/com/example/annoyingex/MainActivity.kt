package com.example.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val messageWorkManager = (application as ExApp).messageWorkManager
        val apiManager = (application as ExApp).apiManager
        apiManager.fetchMsgs("https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json") {response ->
            (application as ExApp).listOfMsgs = response.messages
            Log.i("npham", "List: ${response.messages.toString()}")
        }

        btnMessage.setOnClickListener {
            Log.i("npham", "button clicked")
            messageWorkManager.spam()
        }

        btnBlock.setOnClickListener {
            messageWorkManager.block()
        }
    }
}
