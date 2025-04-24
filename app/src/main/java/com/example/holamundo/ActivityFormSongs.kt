package com.example.holamundo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityFormSongs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_songs)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val songName = findViewById<EditText>(R.id.tfSongName)
        val songAuthor = findViewById<EditText>(R.id.tfAuthorSong)
        val btnSaveSong = findViewById<Button>(R.id.btnSaveSong)
        val db = SQLiteHelper(this)

        btnSaveSong.setOnClickListener {

            val name = songName.text.toString()
            val author = songAuthor.text.toString()

            db.saveSong(name, author)

            val intentSongList = Intent(this, ActivityListSongs::class.java)
            startActivity(intentSongList)
        }
    }
}