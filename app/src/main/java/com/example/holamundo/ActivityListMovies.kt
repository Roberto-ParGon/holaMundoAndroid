package com.example.holamundo

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ActivityListMovies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_movies)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val movieList = findViewById<ListView>(R.id.lvMovies)
        val movies = ArrayList<Map<String, String>>()
        val db = FirebaseDatabase.getInstance().getReference("movies")
        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (movie in snapshot.children) {
                    val movieData = movie.getValue(Movie::class.java)
                    if (movieData != null) {
                        val map = HashMap<String, String>()
                        map["tittle"] = movieData.tittle ?: ""
                        map["category"] = movieData.category ?: ""
                        movies.add(map)
                    }
                }
                val adapter = SimpleAdapter(
                    this@ActivityListMovies,
                    movies,
                    android.R.layout.simple_list_item_2,
                    arrayOf("tittle", "category"),
                    intArrayOf(android.R.id.text1, android.R.id.text2)
                )
                movieList.adapter = adapter
            }
            override fun onCancelled(error: DatabaseError) {
                println("Error recuperando información de las películas:${error.message}")
            }
        })
    }
}