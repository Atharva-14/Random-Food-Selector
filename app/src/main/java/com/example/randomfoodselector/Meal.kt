package com.example.randomfoodselector


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomfoodselector.databinding.ActivityMealBinding

class Meal : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private var url = "https://api.spoonacular.com/recipes/random?apiKey=f79b45ccd141461595f2ed717adc54a2&number=1&tags="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.breakfast.setOnClickListener {
            val url = url +"snack,"
            val intent = Intent(applicationContext, Cuisines::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }

        binding.lunch.setOnClickListener {
            val url = url +"maincourse,"
            val intent = Intent(applicationContext, Cuisines::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }

        binding.eveningBreakfast.setOnClickListener {
            val url = url +"fingerfood,"
            val intent = Intent(applicationContext, Cuisines::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }

        binding.dinner.setOnClickListener {
            val url = url +"maincourse,"
            val intent = Intent(applicationContext, Cuisines::class.java)
            intent.putExtra("url",url)
            startActivity(intent)
        }
    }
}