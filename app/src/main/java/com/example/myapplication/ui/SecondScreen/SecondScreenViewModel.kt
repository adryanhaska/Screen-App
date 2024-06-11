package com.example.myapplication.ui.SecondScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondScreenViewModel : ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _selectedUserName = MutableLiveData<String>()
    val selectedUserName: LiveData<String> get() = _selectedUserName

    fun setUserName(name: String?) {
        _userName.value = name!!
    }

    fun setSelectedUserName(name: String?) {
        _selectedUserName.value = name!!
    }
}