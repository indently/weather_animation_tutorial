package com.codepalace.weather_animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.codepalace.weather_animation.databinding.ActivityMainBinding
import com.github.matteobattilana.weather.PrecipType
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var weather: PrecipType
    lateinit var weatherText: String
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make sure current activity stays on light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Tap button to change weather randomly
        binding.btnChangeWeather.setOnClickListener {
            changeWeather()
        }

    }

    private fun changeWeather() {

        var weatherSpeed = 0
        var weatherParticles = 0f

        // Cycle through the weather cycles
        if (number < 2) {
            ++number
        } else {
            number = 0
        }

        //Randomly select a weather
        when (number) {
            0 -> {
                weather = PrecipType.CLEAR
                weatherText = "Clear"
            }
            1 -> {
                weather = PrecipType.SNOW
                weatherText = "Snow"
                weatherParticles = 10f
                weatherSpeed = 200
            }
            2 -> {
                weather = PrecipType.RAIN
                weatherText = "Rain"
                weatherParticles = 60f
                weatherSpeed = 600
            }
        }

        // Set the weather text to the current weather
        binding.tvWeather.text = weatherText

        //Update animation UI for weather
        binding.wvWeatherView.apply {
            setWeatherData(weather)
            speed = weatherSpeed // How fast
            emissionRate = weatherParticles // How many particles
            angle = 0 // The angle of the fall
            fadeOutPercent = .75f // When to fade out (0.0f-1.0f)
        }
    }
}