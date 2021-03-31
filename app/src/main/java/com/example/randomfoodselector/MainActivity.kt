package com.example.randomfoodselector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.randomfoodselector.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var foodName: String
    private lateinit var readyInMin: String
    private lateinit var urlMain: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchData()

        binding.randomRecipe.setOnClickListener {
            fetchData()
        }

        binding.foodImg.setOnClickListener {
            val intent = Intent(applicationContext, WebView::class.java)
            intent.putExtra("url",urlMain)
            Log.i("urlcus", urlMain)
            startActivity(intent)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
    private fun fetchData() {
        val queue = Volley.newRequestQueue(this)
        val url = intent.getStringExtra("url").toString()
        Log.i("urlM", url)

        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url,null,
                { response ->
                    val recipejsonArray = response.getJSONArray("recipes")

                    val jsonObject = recipejsonArray.getJSONObject(0)

                    foodName = jsonObject.getString("title")
                    Log.i("fdname", foodName)
                    readyInMin = jsonObject.getString("readyInMinutes")
                    Log.i("rim",readyInMin)

                    val recipe = Recipe(jsonObject.getString("title"),
                            jsonObject.getString("readyInMinutes"),
                            jsonObject.getString("sourceUrl"),
                            jsonObject.getString("image"))

                    setData(recipe)


                    Log.i("Recipe", recipe.toString())
                },
                {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                })

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    private fun setData(recipe: Recipe) {
        binding.foodName.text = recipe.name
        binding.readyInMin.text = recipe.readyInMin

        Glide.with(this).load(recipe.imgUrl).into(binding.foodImg)
        urlMain = recipe.sourceUrl
    }
}