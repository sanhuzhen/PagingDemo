package com.sanhuzhen.pagingdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sanhuzhen.pagingdemo.bean.Repo
import com.sanhuzhen.pagingdemo.util.Repository
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {
    fun getPagingData(): Flow<PagingData<Repo>>{
        return Repository.getPagingData().cachedIn(viewModelScope)
    }
}