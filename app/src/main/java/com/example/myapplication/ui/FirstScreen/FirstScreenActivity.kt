package com.example.myapplication.ui.FirstScreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.ui.SecondScreen.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {

    private val viewModel: FirstScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_screen)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val palindromeEditText: EditText = findViewById(R.id.palindromeEditText)
        val checkButton: Button = findViewById(R.id.checkButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        checkButton.setOnClickListener {
            val text = palindromeEditText.text.toString()
            if (viewModel.isPalindrome(text)) {
                Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()
            }
        }

        nextButton.setOnClickListener {
            val name = nameEditText.text.toString()
            viewModel.setName(name)
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
        }
    }
}
