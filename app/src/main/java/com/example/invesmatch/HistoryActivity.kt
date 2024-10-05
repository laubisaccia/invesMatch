package com.example.invesmatch

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val historyTextView = findViewById<TextView>(R.id.tvHistory)
        val listViewHistory = findViewById<ListView>(R.id.listViewHistory)


        // Cargar las comparaciones guardadas
        val sharedPreferences = getSharedPreferences("recommendations", Context.MODE_PRIVATE)
        val comparisons = sharedPreferences.getStringSet("lastRecommendations", mutableSetOf()) ?: mutableSetOf()


        val comparisonsList = comparisons.toList()

        Log.d("HistoryActivity", "Comparaciones: $comparisonsList")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, comparisonsList)
        listViewHistory.adapter = adapter
        Log.d("HistoryActivity", "Comparaciones: $comparisonsList")



    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}