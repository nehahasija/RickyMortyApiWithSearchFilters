package com.rickymorty.androiddemo.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rickymorty.androiddemo.model.Character
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX=1
class PostDataSource(private val repository: Repository, private val query:String) : PagingSource<Int, com.rickymorty.androiddemo.model.Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.rickymorty.androiddemo.model.Character> {
        val pageNumber = params.key ?: STARTING_PAGE_INDEX
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = repository.getSearchCharacters(query, pageNumber)
            val data = response.results

            val prevKey =
                if (currentLoadingPageKey == STARTING_PAGE_INDEX) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = if (data.isEmpty()) null else currentLoadingPageKey + 1
            )
        } catch (e: IOException) {
             LoadResult.Error(e)
        } catch (exception: HttpException) {
             LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}