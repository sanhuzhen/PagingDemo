package com.sanhuzhen.pagingdemo.util

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sanhuzhen.pagingdemo.api.ApiService
import com.sanhuzhen.pagingdemo.bean.Repo
import kotlinx.coroutines.flow.Flow

object Repository {
    private const val PAGE_SIZE = 50

    private val apiService = ApiService.create()

    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { RepoPagingSource(apiService) }
        ).flow
    }
}