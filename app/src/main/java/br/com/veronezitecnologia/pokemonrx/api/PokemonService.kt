package br.com.veronezitecnologia.pokemonrx.api

import br.com.veronezitecnologia.pokemonrx.model.Pokemon
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable

class PokemonService {

    val api: PokemonApi

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

        api = retrofit.create(PokemonApi::class.java)
    }

    fun loadPokemons(): Observable<Pokemon> {
        return api.listaPokemon()
                .flatMap { Observable.from(it.results) }
                .flatMap { api.buscarPor(it.name) }

    }
}