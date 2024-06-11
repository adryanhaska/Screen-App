package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.UserRepository
import com.example.myapplication.network.ApiConfig

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}