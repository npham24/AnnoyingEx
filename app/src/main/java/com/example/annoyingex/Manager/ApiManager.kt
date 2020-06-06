package com.example.annoyingex.Manager

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.annoyingex.Model.Messages
import com.google.gson.Gson

class ApiManager(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun fetchMsgs(url: String, success: (Messages) -> Unit) {
        val request = StringRequest(
            Request.Method.GET, url, { response ->
                val gson = Gson()
                val listOfMsgs = gson.fromJson(response, Messages::class.java)
                success(listOfMsgs)
            },
            {
                Log.i("npham", "fail")
            }
        )
        queue.add(request)
    }
}