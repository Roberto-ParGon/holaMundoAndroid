package com.example.holamundo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tfUser = findViewById<EditText>(R.id.tfUser)
        val tfPass = findViewById<EditText>(R.id.tfPass)
        val tfApodo = findViewById<EditText>(R.id.tfApodo)

        val btnActivity2 = findViewById<Button>(R.id.btnActivity2)
        btnActivity2.setOnClickListener {

            val user = tfUser.text.toString()
            val pass = tfPass.text.toString()
            val apodo = tfApodo.text.toString()

            if(user == "1" && pass == "1"){

                val mySharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE)
                with(mySharedPreferences.edit()){
                    putString("apodo", apodo)
                    apply()
                }

                val intent = Intent(this, Activity2::class.java)
                //intent.putExtra("apodo", apodo)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Usuario o contraseña incorrectas", Toast.LENGTH_SHORT).show()
            }
        }

    }
}