package com.example.invesmatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TermsAndConditionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}