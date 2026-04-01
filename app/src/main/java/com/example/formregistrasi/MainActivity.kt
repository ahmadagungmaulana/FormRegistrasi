package com.example.formregistrasi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    // Deklarasi semua komponen UI
    private lateinit var tilNama: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tilConfirmPassword: TextInputLayout
    private lateinit var etNama: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var rgJenisKelamin: RadioGroup
    private lateinit var tvErrorJenisKelamin: TextView
    private lateinit var cbMembaca: CheckBox
    private lateinit var cbOlahraga: CheckBox
    private lateinit var cbMusik: CheckBox
    private lateinit var cbGaming: CheckBox
    private lateinit var cbMasak: CheckBox
    private lateinit var tvErrorHobi: TextView
    private lateinit var spinnerKota: Spinner
    private lateinit var btnSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hubungkan variabel dengan komponen di XML
        tilNama = findViewById(R.id.tilNama)
        tilEmail = findViewById(R.id.tilEmail)
        tilPassword = findViewById(R.id.tilPassword)
        tilConfirmPassword = findViewById(R.id.tilConfirmPassword)
        etNama = findViewById(R.id.etNama)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin)
        tvErrorJenisKelamin = findViewById(R.id.tvErrorJenisKelamin)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbOlahraga = findViewById(R.id.cbOlahraga)
        cbMusik = findViewById(R.id.cbMusik)
        cbGaming = findViewById(R.id.cbGaming)
        cbMasak = findViewById(R.id.cbMasak)
        tvErrorHobi = findViewById(R.id.tvErrorHobi)
        spinnerKota = findViewById(R.id.spinnerKota)
        btnSubmit = findViewById(R.id.btnSubmit)

        setupSpinner()
        setupRealtimeValidation()
        setupSubmitButton()
        setupLongPress()
    }

    // =====================
    // SETUP SPINNER
    // =====================
    private fun setupSpinner() {
        val kotaList = listOf(
            "-- Pilih Kota --",
            "Jakarta",
            "Bandung",
            "Surabaya",
            "Yogyakarta",
            "Medan",
            "Makassar"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            kotaList
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerKota.adapter = adapter
    }

    // =====================
    // REAL-TIME VALIDATION
    // =====================
    private fun setupRealtimeValidation() {

        // Validasi Nama
        etNama.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    tilNama.error = null
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validasi Email
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = s.toString()
                if (email.isNotEmpty()) {
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        tilEmail.error = "Format email tidak valid"
                    } else {
                        tilEmail.error = null
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validasi Password
        etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val pass = s.toString()
                if (pass.isNotEmpty()) {
                    if (pass.length < 6) {
                        tilPassword.error = "Password minimal 6 karakter"
                    } else {
                        tilPassword.error = null
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Validasi Confirm Password
        etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val confirmPass = s.toString()
                val pass = etPassword.text.toString()
                if (confirmPass.isNotEmpty()) {
                    if (confirmPass != pass) {
                        tilConfirmPassword.error = "Password tidak cocok"
                    } else {
                        tilConfirmPassword.error = null
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // =====================
    // VALIDASI SAAT SUBMIT
    // =====================
    private fun validateForm(): Boolean {
        var isValid = true

        // Cek Nama
        if (etNama.text.toString().isEmpty()) {
            tilNama.error = "Nama tidak boleh kosong"
            isValid = false
        }

        // Cek Email
        val email = etEmail.text.toString()
        if (email.isEmpty()) {
            tilEmail.error = "Email tidak boleh kosong"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Format email tidak valid"
            isValid = false
        }

        // Cek Password
        val password = etPassword.text.toString()
        if (password.isEmpty()) {
            tilPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            tilPassword.error = "Password minimal 6 karakter"
            isValid = false
        }

        // Cek Confirm Password
        val confirmPassword = etConfirmPassword.text.toString()
        if (confirmPassword.isEmpty()) {
            tilConfirmPassword.error = "Konfirmasi password tidak boleh kosong"
            isValid = false
        } else if (confirmPassword != password) {
            tilConfirmPassword.error = "Password tidak cocok"
            isValid = false
        }

        // Cek Jenis Kelamin
        if (rgJenisKelamin.checkedRadioButtonId == -1) {
            tvErrorJenisKelamin.visibility = View.VISIBLE
            isValid = false
        } else {
            tvErrorJenisKelamin.visibility = View.GONE
        }

        // Cek Hobi minimal 3
        val totalHobiDipilih = listOf(
            cbMembaca, cbOlahraga, cbMusik, cbGaming, cbMasak
        ).count { it.isChecked }

        if (totalHobiDipilih < 3) {
            tvErrorHobi.visibility = View.VISIBLE
            isValid = false
        } else {
            tvErrorHobi.visibility = View.GONE
        }

        // Cek Spinner
        if (spinnerKota.selectedItemPosition == 0) {
            Toast.makeText(this, "Pilih asal kota!", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }

    // =====================
    // TOMBOL SUBMIT
    // =====================
    private fun setupSubmitButton() {
        btnSubmit.setOnClickListener {
            if (validateForm()) {
                showConfirmationDialog()
            }
        }
    }

    // =====================
    // ALERT DIALOG
    // =====================
    private fun showConfirmationDialog() {
        val nama = etNama.text.toString()
        val email = etEmail.text.toString()
        val kota = spinnerKota.selectedItem.toString()

        val radioButton = findViewById<RadioButton>(rgJenisKelamin.checkedRadioButtonId)
        val jenisKelamin = radioButton.text.toString()

        val hobiList = listOf(cbMembaca, cbOlahraga, cbMusik, cbGaming, cbMasak)
            .filter { it.isChecked }
            .joinToString(", ") { it.text.toString() }

        val message = """
            Nama     : $nama
            Email    : $email
            Kelamin  : $jenisKelamin
            Hobi     : $hobiList
            Kota     : $kota
            
            Apakah data sudah benar?
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Registrasi")
            .setMessage(message)
            .setPositiveButton("Ya, Daftar!") { _, _ ->
                Toast.makeText(
                    this,
                    "Registrasi Berhasil! Selamat datang, $nama!",
                    Toast.LENGTH_LONG
                ).show()
            }
            .setNegativeButton("Cek Lagi") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    // =====================
    // LONG PRESS
    // =====================
    private fun setupLongPress() {
        btnSubmit.setOnLongClickListener {
            Toast.makeText(
                this,
                "💡 Tips: Pastikan semua data sudah benar sebelum mendaftar!",
                Toast.LENGTH_LONG
            ).show()
            true
        }
    }
}