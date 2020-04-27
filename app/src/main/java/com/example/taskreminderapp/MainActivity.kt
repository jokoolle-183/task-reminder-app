package com.example.taskreminderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taskreminderapp.ui.LogFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, LogFragment())
            .addToBackStack(null)
            .commit()
    }
}
