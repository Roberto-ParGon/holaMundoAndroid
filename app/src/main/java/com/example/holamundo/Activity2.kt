package com.example.holamundo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val welcomeApodo = findViewById<TextView>(R.id.lbWelcome)
        //val apodo = intent.getStringExtra("apodo")
        val mySharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
        val apodo = mySharedPreferences.getString("apodo","Apodo no encontrado")
        //welcomeApodo.text = "Bienvenido, $apodo"
        welcomeApodo.text = getString(R.string.lbWelcome, apodo)


        val btnSongs = findViewById<Button>(R.id.btnSongs)
        val btnMovies = findViewById<Button>(R.id.btnMovies)

        btnSongs.setOnClickListener {
            val intentSongs = Intent(this, ActivityFormSongs::class.java)
            startActivity(intentSongs)
        }

        btnMovies.setOnClickListener {
            val intentMovies = Intent(this, ActivityFormMovies::class.java)
            startActivity(intentMovies)
        }
    }
}