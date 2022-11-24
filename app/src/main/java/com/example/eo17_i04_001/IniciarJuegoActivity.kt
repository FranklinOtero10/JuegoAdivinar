package com.example.eo17_i04_001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.eo17_i04_001.Models.PlayerEntity
import com.google.android.material.textfield.TextInputEditText
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class IniciarJuegoActivity : AppCompatActivity() {

    private lateinit var txvJugador: TextView
    private lateinit var txvNivel: TextView
    private lateinit var txvContIntentos: TextView
    private lateinit var edtNumeroAdivinar: TextInputEditText
    private lateinit var btnAdivinar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_juego)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = "Adivigana!!"

        edtNumeroAdivinar = findViewById(R.id.edtNumeroAdivinar)
        txvContIntentos = findViewById(R.id.txvContIntentos)
        txvJugador = findViewById(R.id.txvJugador)
        txvNivel = findViewById(R.id.txvNivel)
        btnAdivinar = findViewById(R.id.btnAdivinar)

        val preferences = getSharedPreferences("Players", MODE_PRIVATE)
        val nickPlayer = preferences.getString("nickname", "")
        val nivelPlayer = preferences.getString("nivel", "")
        var contadorIntentos = 0

        txvContIntentos.text = contadorIntentos.toString()
        cargarUsuario()

        btnAdivinar.setOnClickListener {
            contadorIntentos += 1
            txvContIntentos.text = contadorIntentos.toString()
            if (edtNumeroAdivinar.text.toString().isNotEmpty()){
                //Comprobando el nivel seleccionado por el jugador
                if (nivelPlayer == "Facil"){
                    //Comprobamos si el numero generado es igual al digitado por el jugador
                    if (adivinarFacil() == edtNumeroAdivinar.text.toString().toInt()){
                        //Comprobamos mediante una consulta si el jugador existe
                        if (PlayerApplication.database.playerDao().comprobar(nickPlayer.toString())){
                            //Si el jugador ya existe en la bd solo actualizamos sus intentos
                            AlertDialog.Builder(this)
                                .setTitle("¡Felicidades has ganado!!")
                                .setMessage("Se actualizará tu información")
                                .setPositiveButton("Ok"){_,_ ->
                                    modificarBD(contadorIntentos, nickPlayer.toString())
                                }.show()
                        }else{
                            //Si el jugador no existe en la bd guardamos su info
                            AlertDialog.Builder(this)
                                .setTitle("¡Felicidades has ganado!!")
                                .setMessage("A continuación se guardara tu información")
                                .setPositiveButton("Ok") { _, _ ->
                                    guardarBD(nickPlayer.toString(), contadorIntentos)
                                }.show()
                        }
                    }else{
                        edtNumeroAdivinar.setText("")
                        Toast.makeText(this, "Número incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }else if(nivelPlayer == "Medio"){
                    if (adivinarMedio() == edtNumeroAdivinar.text.toString().toInt()){
                        if (PlayerApplication.database.playerDao().comprobar(nickPlayer.toString())){
                            AlertDialog.Builder(this)
                                .setTitle("¡Felicidades has ganado!!")
                                .setMessage("Se actualizará tu información")
                                .setPositiveButton("Ok"){_,_ ->
                                    modificarBD(contadorIntentos, nickPlayer.toString())
                                }.show()
                        }else{
                            AlertDialog.Builder(this)
                                .setTitle("¡Felicidades has ganado!!")
                                .setMessage("A continuación se guardara tu información")
                                .setPositiveButton("Ok") { _, _ ->
                                    guardarBD(nickPlayer.toString(), contadorIntentos)
                                }.show()
                        }
                    }else{
                        edtNumeroAdivinar.setText("")
                        Toast.makeText(this, "Número incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }else if (nivelPlayer == "Dificil"){
                    if (adivinarDificil() == edtNumeroAdivinar.text.toString().toInt()){
                        if (PlayerApplication.database.playerDao().comprobar(nickPlayer.toString())){
                            AlertDialog.Builder(this)
                                .setTitle("¡Felicidades has ganado!!")
                                .setMessage("Se actualizará tu información")
                                .setPositiveButton("Ok"){_,_ ->
                                    modificarBD(contadorIntentos, nickPlayer.toString())
                                }.show()
                        }else{
                            AlertDialog.Builder(this)
                                .setTitle("¡Felicidades has ganado!!")
                                .setMessage("A continuación se guardara tu información")
                                .setPositiveButton("Ok") { _, _ ->
                                    guardarBD(nickPlayer.toString(), contadorIntentos)
                                }.show()
                        }
                    }else{
                        edtNumeroAdivinar.setText("")
                        Toast.makeText(this, "Número incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    AlertDialog.Builder(this)
                        .setTitle("¡Aviso!")
                        .setMessage("Registrese en configuración")
                        .setPositiveButton("Ok") { _, _ ->
                            finish()
                        }.show()
                }
            }else{
                contadorIntentos = 0
                txvContIntentos.text = contadorIntentos.toString()
                AlertDialog.Builder(this).setTitle("¡Aviso!")
                    .setMessage("Ingrese un número")
                    .setPositiveButton("Ok", null).show()
            }
        }
    }

    //Guardar al jugador ganador en la base de datos
    fun guardarBD(nombre: String, intentos: Int){
        doAsync {
            PlayerApplication.database.playerDao().addPlayer(
                PlayerEntity(
                    nickname = nombre,
                    intentos = intentos
                ))
            uiThread {
                finish()
            }
        }
    }

    //Si el jugador ya existe solo modificamos sus intentos en la bd
    fun modificarBD(pIntentos: Int, pNick: String){
        doAsync {
            PlayerApplication.database.playerDao().updateScore(pIntentos, pNick)
            uiThread {
                finish()
            }
        }
    }

    private fun adivinarFacil(): Int{
        //return Random.nextInt(5) + 1
        return (1..50).random()
    }

    private fun adivinarMedio(): Int{
        return (1..100).random()
    }

    private fun adivinarDificil(): Int{
        return (1..150).random()
    }

    //Cargando el usuario registrado previamente usando sharedPreferences
    private fun cargarUsuario(){
        val preferences = getSharedPreferences("Players", MODE_PRIVATE)
        val nickPlayer = preferences.getString("nickname", null)
        val nivelPlayer = preferences.getString("nivel", "")

        if (nickPlayer == null){
            AlertDialog.Builder(this)
                .setTitle("¡Aviso!")
                .setMessage("Registrese en configuración")
                .setPositiveButton("Ok") { _, _ ->
                    finish()
                }.show()
        }else{
            txvJugador.text = nickPlayer.toString()
            txvNivel.text = nivelPlayer.toString()
        }

    }

    // Configuracion del action bar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}