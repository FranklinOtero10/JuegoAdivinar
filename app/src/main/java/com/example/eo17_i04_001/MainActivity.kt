package com.example.eo17_i04_001


import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var btnPuntajeAlto: Button
    lateinit var btnIniciar: Button
    lateinit var btnConfig: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        btnIniciar = findViewById(R.id.btnIniciar)
        btnPuntajeAlto = findViewById(R.id.btnPuntajes)
        btnConfig = findViewById(R.id.btnConfiguraciones)

        btnIniciar.setOnClickListener {
            startActivity(Intent(this, IniciarJuegoActivity::class.java))
        }

        btnPuntajeAlto.setOnClickListener {
            startActivity(Intent(this, PuntajesAltosActivity::class.java))
        }

        btnConfig.setOnClickListener {
            startActivity(Intent(this, ConfiguracionActivity::class.java))
        }
    }

    //Para que se muestre el menu superior
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Segun la opcion seleccionada en el menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.AcercaDe -> {
                startActivity(Intent(this, AcercaDeActivity::class.java))
            }
            R.id.Salir -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("¡Advertencia!").setMessage("¿Desea salir?")
                builder.setPositiveButton(
                    "Ok"
                ) { _, _ -> finish() }
                builder.setNegativeButton("Cancelar", null)
                val dialog = builder.create()
                dialog.show()
            }
            R.id.InfoJuego -> {
                startActivity(Intent(this, InfoJuegoActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}