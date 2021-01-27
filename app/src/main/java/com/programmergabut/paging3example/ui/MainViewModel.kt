package com.programmergabut.paging3example.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.programmergabut.paging3example.data.remote.UserPagingSource
import com.programmergabut.paging3example.data.remote.UsersRepository
import com.programmergabut.paging3example.data.remote.json.Item
import com.programmergabut.paging3example.util.AbsentLiveData
import com.programmergabut.paging3example.util.Constant

class MainViewModel @ViewModelInject constructor(private val repository: UsersRepository): ViewModel() {

    private val currentQuery = MutableLiveData<String>()
    val users = Transformations.switchMap(currentQuery){ queryString ->
        if(queryString.isNullOrEmpty()){
            AbsentLiveData.create()
        } else {
            getSearchResult(queryString)
        }
    }
    fun searchPhoto(query: String){
        currentQuery.value = query
    }

    private fun getSearchResult(query: String): LiveData<PagingData<Item>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constant.GITHUB_API_PER_PAGE,
                maxSize = Constant.PAGER_MAX_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(repository, query) }
        ).liveData
    }

}