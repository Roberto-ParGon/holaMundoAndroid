package com.example.holamundo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, "SongsDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE canciones(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, cantante TEXT)")
    }
git a
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS canciones")
        onCreate(db)
    }

    fun retrieveSong(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT id AS _id, nombre, cantante FROM canciones", null)
    }

    fun saveSong(name: String, author: String): Long {
        val values = ContentValues().apply {
            put("nombre", name)
            put("cantante", author)
        }
        val db = writableDatabase
        val id = db.insert("canciones", null, values)
        db.close()
        return id
    }


}