package com.example.invesmatch

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoryActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val historyTextView = findViewById<TextView>(R.id.tvHistory)
        val listViewHistory = findViewById<ListView>(R.id.listViewHistory)

        val sharedPreferences = getSharedPreferences("recommendations", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("comparisonsList", "[]")
        val type = object : TypeToken<ArrayList<InvestmentComparisonActivity.ComparisonEntity>>() {}.type
        val comparisonsList: ArrayList<InvestmentComparisonActivity.ComparisonEntity> = gson.fromJson(json, type)
        Log.d("HistoryActivity", "Número de comparaciones cargadas: ${comparisonsList.size}")


        val comparisonsStringList = comparisonsList.map { comparison ->
            "Entidad 1: ${comparison.entity1}, Ganancia 1: ${comparison.ganancia1}\n" +
                    "Entidad 2: ${comparison.entity2}, Ganancia 2: ${comparison.ganancia2}"
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, comparisonsStringList)
        listViewHistory.adapter = adapter

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}