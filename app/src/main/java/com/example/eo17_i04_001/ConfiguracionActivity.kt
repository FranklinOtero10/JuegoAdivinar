package com.example.eo17_i04_001

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class ConfiguracionActivity : AppCompatActivity() {

    private lateinit var btnGuardar: Button
    private lateinit var edtNick: TextInputEditText
    private lateinit var rbFacil: RadioButton
    private lateinit var rbMedio: RadioButton
    private lateinit var rbDificil: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        title = "Configuración"

        btnGuardar = findViewById(R.id.btnGuardar)
        edtNick = findViewById(R.id.edtNick)
        rbFacil = findViewById(R.id.rbFacil)
        rbMedio = findViewById(R.id.rbMedio)
        rbDificil = findViewById(R.id.rbDificil)

        btnGuardar.setOnClickListener {
            if (edtNick.text.toString().isEmpty()) {
                edtNick.error = "Debe completar este campo"
            } else if (!rbFacil.isChecked && !rbMedio.isChecked && !rbDificil.isChecked) {
                AlertDialog.Builder(this)
                    .setTitle("¡Aviso!")
                    .setMessage("Debe seleccionar una opción")
                    .setPositiveButton("Ok", null).show()
            }else{
                guardarUsuario()
                finish()
            }

        }
    }

    fun guardarUsuario() {
        //Guardando en SharedPreferences
        val preferences = getSharedPreferences("Players", MODE_PRIVATE)
        if (rbFacil.isChecked) {
            with(preferences.edit()) {
                putString("nickname", edtNick.text.toString())
                putString("nivel", rbFacil.text.toString()).apply()
            }
        } else if (rbMedio.isChecked) {
            with(preferences.edit()) {
                putString("nickname", edtNick.text.toString())
                putString("nivel", rbMedio.text.toString()).apply()
            }
        } else if (rbDificil.isChecked) {
            with(preferences.edit()) {
                putString("nickname", edtNick.text.toString())
                putString("nivel", rbDificil.text.toString()).apply()
            }
        }
        Toast.makeText(this, "Usuario guardado", Toast.LENGTH_SHORT).show()
    }

    // Configuracion del action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}