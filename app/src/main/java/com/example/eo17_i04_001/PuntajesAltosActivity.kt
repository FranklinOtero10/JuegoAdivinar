package com.example.eo17_i04_001

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eo17_i04_001.Adapters.PuntajesAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PuntajesAltosActivity : AppCompatActivity() {

    private lateinit var rcPuntajes: RecyclerView
    private lateinit var puntajesAdapter: PuntajesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntajes_altos)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        title = "Puntajes más Altos"

        configRecyclerView()
    }

    private fun getPlayers(){
        doAsync {
            val player = PlayerApplication.database.playerDao().getAltos()
            uiThread {
                puntajesAdapter.setPlayers(player)
            }
        }
    }

    //Metodo que configura el recyclerview
    private fun configRecyclerView(){
        rcPuntajes = findViewById(R.id.rvPuntajes)
        puntajesAdapter = PuntajesAdapter(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(this)

        getPlayers()

        rcPuntajes.apply {
            rcPuntajes.setHasFixedSize(true)
            rcPuntajes.layoutManager = linearLayoutManager
            rcPuntajes.adapter = puntajesAdapter
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