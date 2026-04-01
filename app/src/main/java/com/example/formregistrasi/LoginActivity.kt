package com.example.formregistrasi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var tilLoginEmail: TextInputLayout
    private lateinit var tilLoginPassword: TextInputLayout
    private lateinit var etLoginEmail: TextInputEditText
    private lateinit var etLoginPassword: TextInputEditText
    private lateinit var btnDoLogin: Button
    private lateinit var tvDaftarDisini: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Hubungkan variabel dengan komponen XML
        tilLoginEmail = findViewById(R.id.tilLoginEmail)
        tilLoginPassword = findViewById(R.id.tilLoginPassword)
        etLoginEmail = findViewById(R.id.etLoginEmail)
        etLoginPassword = findViewById(R.id.etLoginPassword)
        btnDoLogin = findViewById(R.id.btnDoLogin)
        tvDaftarDisini = findViewById(R.id.tvDaftarDisini)

        setupRealtimeValidation()
        setupLoginButton()
        setupDaftarLink()
    }

    // =====================
    // REAL-TIME VALIDATION
    // =====================
    private fun setupRealtimeValidation() {

        // Validasi Email
        etLoginEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                if (email.isNotEmpty()) {
                    if (!android.util.Patterns.EMAIL_ADDRESS
                            .matcher(email).matches()) {
                        tilLoginEmail.error = "Format email tidak valid"
                    } else {
                        tilLoginEmail.error = null
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validasi Password
        etLoginPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val pass = s.toString()
                if (pass.isNotEmpty()) {
                    if (pass.length < 6) {
                        tilLoginPassword.error = "Password minimal 6 karakter"
                    } else {
                        tilLoginPassword.error = null
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // =====================
    // TOMBOL LOGIN
    // =====================
    private fun setupLoginButton() {
        btnDoLogin.setOnClickListener {
            if (validateLogin()) {
                val email = etLoginEmail.text.toString()
                Toast.makeText(
                    this,
                    "✅ Login Berhasil! Selamat datang, $email!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    // =====================
    // VALIDASI LOGIN
    // =====================
    private fun validateLogin(): Boolean {
        var isValid = true

        // Cek Email
        val email = etLoginEmail.text.toString()
        if (email.isEmpty()) {
            tilLoginEmail.error = "Email tidak boleh kosong"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS
                .matcher(email).matches()) {
            tilLoginEmail.error = "Format email tidak valid"
            isValid = false
        }

        // Cek Password
        val password = etLoginPassword.text.toString()
        if (password.isEmpty()) {
            tilLoginPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            tilLoginPassword.error = "Password minimal 6 karakter"
            isValid = false
        }

        return isValid
    }

    // =====================
    // LINK DAFTAR DI SINI
    // =====================
    private fun setupDaftarLink() {
        tvDaftarDisini.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}