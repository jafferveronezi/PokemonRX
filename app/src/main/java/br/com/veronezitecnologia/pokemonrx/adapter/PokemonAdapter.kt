package br.com.veronezitecnologia.pokemonrx.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.veronezitecnologia.pokemonrx.R
import br.com.veronezitecnologia.pokemonrx.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(val context: Context,
                    val pokemonList: List<Pokemon>)
    : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder?.name.text = pokemon.name
        Picasso.with(context).load(pokemon.sprites.frontDefault).into(holder?.image);
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.tv_pokemon
        val image = itemView.iv_pokemon
    }
}