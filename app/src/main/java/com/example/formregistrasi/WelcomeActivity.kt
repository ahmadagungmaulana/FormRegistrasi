package com.example.formregistrasi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var btnDaftar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btnLogin = findViewById(R.id.btnLogin)
        btnDaftar = findViewById(R.id.btnDaftar)

        // Tombol LOGIN → pindah ke LoginActivity
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Tombol DAFTAR → pindah ke MainActivity
        btnDaftar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}