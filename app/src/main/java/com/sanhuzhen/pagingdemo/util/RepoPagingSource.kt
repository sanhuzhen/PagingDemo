package com.sanhuzhen.pagingdemo.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sanhuzhen.pagingdemo.api.ApiService
import com.sanhuzhen.pagingdemo.bean.Repo

class RepoPagingSource(private val apiService: ApiService): PagingSource<Int, Repo>() {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try{
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val response = apiService.searchRepos(page, pageSize)
            val reposItems = response.items
            val prevKey = if (page>1) page - 1 else null
            val nextKey = if (reposItems.isEmpty()) null else page + 1
            LoadResult.Page(reposItems, prevKey, nextKey)
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}