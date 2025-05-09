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

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS canciones")
        onCreate(db)
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

    fun retrieveSong(): Cursor {
        return readableDatabase.rawQuery("SELECT id AS _id, nombre, cantante FROM canciones", null)
    }

    fun deleteSong(id: Long): Int {
        return writableDatabase.delete("canciones", "id=?", arrayOf(id.toString()))
    }
}