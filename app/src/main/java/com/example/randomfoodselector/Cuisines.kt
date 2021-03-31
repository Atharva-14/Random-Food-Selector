package com.example.randomfoodselector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.randomfoodselector.databinding.ActivityCuisinesBinding

class Cuisines : AppCompatActivity() {

    private lateinit var binding: ActivityCuisinesBinding

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCuisinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("url").toString()

        val list = listOf("Indian", "Italian", "Mexican", "Chinese", "French"
                , "Thai", "Spanish", "American", "British", "European")

        val adapter = CountryAdapter(list,object : ItemClicked{
            override fun onItemClick(string: String) {
                val urlMain = url + string.lowercase()
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("url",urlMain)
                //Log.i("urlcus", urlMain)
                startActivity(intent)
            }
        })

        binding.recyclerView.adapter = adapter

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}