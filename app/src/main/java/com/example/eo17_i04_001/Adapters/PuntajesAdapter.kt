package com.example.eo17_i04_001.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.eo17_i04_001.Models.PlayerEntity
import com.example.eo17_i04_001.R
import com.example.eo17_i04_001.databinding.ItemPuntajesBinding

class PuntajesAdapter(private var lstPuntajes: MutableList<PlayerEntity>, var context: Context)
    :RecyclerView.Adapter<PuntajesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layout = LayoutInflater.from(parent.context)
        return ViewHolder(layout.inflate(R.layout.item_puntajes, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lstPuntajes[position]
        val playerEntity: PlayerEntity = item
        with(holder){
            binding.txvPuntajeJugador.text = playerEntity.getNombre()
            binding.txvPuntajeIntentos.text = playerEntity.getInten().toString()
            configGlide(binding.imgUser)
        }
    }

    //Cargamos la imagen desde un servidor con su url
    private fun configGlide(img: ImageView){
        val url =
            "https://static.wikia.nocookie.net/mario/images/5/5a/Champi%C3%B1%C3%B3n_1-UP.jpg/revision/latest/scale-to-width-down/1024?cb=20101117230650&path-prefix=es"
        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .circleCrop()
            .into(img)
    }

    fun setPlayers(player: MutableList<PlayerEntity>){
        this.lstPuntajes = player.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = lstPuntajes.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemPuntajesBinding.bind(view)
    }

}