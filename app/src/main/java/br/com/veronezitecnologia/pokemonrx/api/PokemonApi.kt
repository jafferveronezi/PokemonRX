package br.com.veronezitecnologia.pokemonrx.api

import br.com.veronezitecnologia.pokemonrx.model.Pokemon
import br.com.veronezitecnologia.pokemonrx.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface PokemonApi {

    @GET("/api/v2/pokemon")
    fun listaPokemon(): Observable<PokemonResponse>

    @GET("/api/v2/pokemon/{nomePokemon}")
    fun buscarPor(@Path("nomePokemon") nomePokemon: String): Observable<Pokemon>
}