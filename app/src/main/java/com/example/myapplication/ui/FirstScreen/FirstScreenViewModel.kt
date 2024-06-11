package com.example.myapplication.ui.FirstScreen

import androidx.lifecycle.ViewModel

class FirstScreenViewModel : ViewModel() {

    private var userName: String = ""

    fun setName(name: String) {
        userName = name
    }

    fun getName(): String {
        return userName
    }

    fun isPalindrome(text: String): Boolean {
        val cleanText = text.replace("\\s".toRegex(), "").lowercase()
        return cleanText == cleanText.reversed()
    }
}
