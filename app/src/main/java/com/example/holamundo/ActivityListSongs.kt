package com.example.holamundo

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityListSongs : AppCompatActivity() {
    private lateinit var dbHelper: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_songs)
        dbHelper = SQLiteHelper(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listView)
        dbHelper = SQLiteHelper(this)
        val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            dbHelper.retrieveSong(),
            arrayOf("nombre", "cantante"),
            intArrayOf(android.R.id.text1,android.R.id.text2),
            0
        )

        listView.adapter = adapter
        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, _, id ->
            dbHelper.deleteSong(id)

            val newCursor = dbHelper.retrieveSong()
            (listView.adapter as SimpleCursorAdapter).changeCursor(newCursor)

            true
        }



    }



}

