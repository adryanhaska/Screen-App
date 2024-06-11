package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.myapplication.network.ApiService
import com.example.myapplication.network.User

class UserRepository(private val apiService: ApiService) {
    fun getUser(): LiveData<PagingData<User>> {
        println("UserRepository: Fetching users")
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                println("UserRepository: Creating PagingSource")
                UserPagingSource(apiService)
            }
        ).liveData
    }
}
