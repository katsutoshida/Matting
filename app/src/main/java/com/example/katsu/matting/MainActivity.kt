package com.example.katsu.matting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyAsyncTask().execute()
    }
    inner class MyAsyncTask: AsyncTask<Void, Void, String>() {

        override fun doInBackground(vararg p0: Void?): String? {
            return getHtml()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val tview = findViewById<TextView>(R.id.name) //texiViewにデータを受け取りのコード
            tview.setText(result)
        }
    }

}

fun getHtml(): String {
    val client = OkHttpClient()
    val req = Request.Builder().url("http://163.44.175.121/api/v1/user").get().build()
    val resp = client.newCall(req).execute()
    return resp.body()!!.string()
}

