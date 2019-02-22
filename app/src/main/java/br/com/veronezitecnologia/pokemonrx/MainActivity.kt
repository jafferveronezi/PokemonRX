package br.com.veronezitecnologia.pokemonrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.veronezitecnologia.pokemonrx.adapter.PokemonAdapter
import br.com.veronezitecnologia.pokemonrx.api.PokemonApi
import br.com.veronezitecnologia.pokemonrx.api.PokemonService
import br.com.veronezitecnologia.pokemonrx.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PokemonAdapter
    val list = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = PokemonAdapter(this, list)
        list_pokemon.adapter = adapter
        list_pokemon.layoutManager = LinearLayoutManager(this)

        val api = PokemonService()

        api.loadPokemons()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                            list.add(it)
                        },
                        {e -> e.printStackTrace()},
                        {
                            adapter.notifyDataSetChanged()
                        }
                )
    }
}
