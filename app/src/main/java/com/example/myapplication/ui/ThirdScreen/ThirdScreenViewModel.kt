package com.example.myapplication.ui.ThirdScreen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.UserRepository
import com.example.myapplication.di.Injection
import com.example.myapplication.network.User

class ThirdScreenViewModel(userRepository: UserRepository) : ViewModel() {

    init {
        println("ThirdScreenViewModel initialized")
    }

    val user: LiveData<PagingData<User>> =
        userRepository.getUser().cachedIn(viewModelScope)
}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThirdScreenViewModel(Injection.provideRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}