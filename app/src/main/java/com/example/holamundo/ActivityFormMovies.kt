package com.example.holamundo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class ActivityFormMovies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieTittle = findViewById<EditText>(R.id.etMovieTittle)
        val movieCategory = findViewById<Spinner>(R.id.spMovieGenre)
        val saveMovie = findViewById<Button>(R.id.btnSaveMovie)
        val categories =
            listOf("Acción", "Drama", "Comedia", "Terror", "Ciencia Ficción", "Antigua")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        movieCategory.adapter = adapter

        saveMovie.setOnClickListener {
            val selectedTittle = movieTittle.text.toString()
            val selectedCategory = movieCategory.selectedItem.toString()
            val db = FirebaseDatabase.getInstance().getReference("movies")
            val id = db.push().key
            val movie = Movie(id, selectedTittle, selectedCategory)
            if (id != null) {
                db.child(id).setValue(movie).addOnSuccessListener {
                    Toast.makeText(this, "Película guardada exitosamente", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this, ActivityListMovies::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}