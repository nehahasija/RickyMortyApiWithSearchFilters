package com.rickymorty.androiddemo.api

import com.rickymorty.androiddemo.model.CharacterList
import com.rickymorty.androiddemo.model.EpisodeData
import com.rickymorty.androiddemo.model.LocationData


class Repository {

    suspend fun getCharacters(page: Int): CharacterList {
        return RetrofitInstance.api.getCharacters(page)
    }

    suspend fun getSearchCharacters(query: String, page: Int): CharacterList {
        return RetrofitInstance.api.getSearchCharacters(query, page)
    }

    suspend fun getCharactersByName(name: String): CharacterList {
        return RetrofitInstance.api.getCharactersByName(name)
    }


    suspend fun getCharacterLocation(id: Int): LocationData {
        return RetrofitInstance.api.getCharacterLocation(id)
    }

    suspend fun getCharacterEpisode(id: Int): EpisodeData {
        return RetrofitInstance.api.getCharacterEpisode(id)
    }

}