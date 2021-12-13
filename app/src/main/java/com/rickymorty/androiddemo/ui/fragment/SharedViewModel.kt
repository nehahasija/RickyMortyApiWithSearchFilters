package com.rickymorty.androiddemo.ui.fragment

import androidx.lifecycle.*
import androidx.paging.*
import com.rickymorty.androiddemo.api.PostDataSource
import com.rickymorty.androiddemo.api.Repository
import com.rickymorty.androiddemo.model.Character
import com.rickymorty.androiddemo.model.EpisodeData
import com.rickymorty.androiddemo.model.LocationData
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: Repository) : ViewModel() {
    var isFilter = MutableLiveData<Boolean>()
    var locations = MutableLiveData<LocationData>()
    var episodes = MutableLiveData<EpisodeData>()
    private val currentQuery = MutableLiveData(DEFAULT_QUERY)


    val characters = currentQuery.switchMap { queryString ->
        getSearchResults(queryString).cachedIn(viewModelScope)
    }


    fun searchCharacterNames(query: String) {
        currentQuery.value = query
    }


    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PostDataSource(repository, query) }
        ).liveData


    fun getcharacterLocation(url: String) {
        var result: Int = url.filter { it.isDigit() }.toInt()
        viewModelScope.launch {
            locations.value = repository.getCharacterLocation(result)
            isFilter.value = true
        }

    }

    fun getcharacterepisode(url: String) {
        var result: Int = url.filter { it.isDigit() }.toInt()
        viewModelScope.launch {
            episodes.value = repository.getCharacterEpisode(result)
            isFilter.value = true
        }

    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }


}