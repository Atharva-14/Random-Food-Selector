package com.example.randomfoodselector

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.example.randomfoodselector.databinding.ActivityWebViewBinding

class WebView : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webViewSetup()
    }

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun webViewSetup() {
        val url = intent.getStringExtra("url").toString()
        Log.i("urlll", url)

        binding.webView.webViewClient = WebViewClient()

        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.loadsImagesAutomatically = true
            loadUrl(url)
            Log.i("uri", url.toString())
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) binding.webView.goBack() else super.onBackPressed()
    }
}