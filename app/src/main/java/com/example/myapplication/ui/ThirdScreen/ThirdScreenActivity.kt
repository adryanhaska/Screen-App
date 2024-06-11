package com.example.myapplication.ui.ThirdScreen

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.adapter.UserListAdapter
import com.example.myapplication.databinding.ThirdScreenBinding
import com.example.myapplication.ui.SecondScreen.SecondScreenActivity

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ThirdScreenBinding
    private val mainViewModel: ThirdScreenViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUser.layoutManager = LinearLayoutManager(this)

        supportActionBar?.apply {
            title = getString(R.string.third_screen)
            setDisplayHomeAsUpEnabled(true)
        }

        getData()
    }

    private fun getData() {
        val adapter = UserListAdapter()
        binding.rvUser.adapter = adapter
        binding.tvNotFound.isVisible = false
        mainViewModel.user.observe(this) { pagingData ->
            adapter.submitData(lifecycle, pagingData)
        }
        adapter.setOnItemClickCallback { firstName, lastName ->
            navigateToSecondActivity(firstName, lastName)
        }
    }

    private fun navigateToSecondActivity(firstName: TextView, lastName: TextView) {
        val intent = Intent(this, SecondScreenActivity::class.java).apply {
            val value = "${firstName.text} " + "${lastName.text}"
            putExtra("USER_NAME_2", value)
        }

        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
