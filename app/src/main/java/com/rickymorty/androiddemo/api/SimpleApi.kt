package com.rickymorty.androiddemo.api


import com.rickymorty.androiddemo.model.CharacterList
import com.rickymorty.androiddemo.model.EpisodeData
import com.rickymorty.androiddemo.model.LocationData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleApi {

    @GET("api/character")
    suspend fun getSearchCharacters(@Query("name") name : String,
                              @Query("page") page:Int): CharacterList
    @GET("api/character")
    suspend fun getCharacters(@Query("page") page:Int): CharacterList

    @GET("api/location/{ID}")
    suspend fun getCharacterLocation(@Path("ID") id : Int): LocationData
    @GET("api/episode/{ID}")
    suspend fun getCharacterEpisode(@Path("ID") id : Int): EpisodeData

    @GET("api/character")
    suspend fun getCharactersByName(@Query("name") name : String): CharacterList


}