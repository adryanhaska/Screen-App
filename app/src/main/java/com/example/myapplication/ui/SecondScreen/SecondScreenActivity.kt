package com.example.myapplication.ui.SecondScreen

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.ui.ThirdScreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private val viewModel: SecondScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_screen)

        supportActionBar?.apply {
            title = getString(R.string.second_screen)
            setDisplayHomeAsUpEnabled(true)
        }

        val welcomeTextView: TextView = findViewById(R.id.tv_welcome)
        val selectedUserNameTextView: TextView = findViewById(R.id.tv_selected_user_name)
        val chooseUserButton: Button = findViewById(R.id.btn_choose_user)
        val username: TextView = findViewById(R.id.tv_user_name)

        val userName = intent.getStringExtra("USER_NAME")
        viewModel.setUserName(userName)

        viewModel.userName.observe(this) { userName ->
            username.text = userName
        }

        var selectedName = intent.getStringExtra("USER_NAME_2")
        println(selectedName)
        selectedName = if (selectedName.isNullOrEmpty()){
            "Selected User Name"
        } else {
            selectedName
        }
        viewModel.setSelectedUserName(selectedName)

        viewModel.selectedUserName.observe(this) { selectedName ->
            selectedUserNameTextView.text = selectedName
        }

        chooseUserButton.setOnClickListener {
            val intent = Intent(this, ThirdScreenActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val selectedUserName = data?.getStringExtra("USER_NAME_2")
            viewModel.setSelectedUserName(selectedUserName)
        }
    }
}
