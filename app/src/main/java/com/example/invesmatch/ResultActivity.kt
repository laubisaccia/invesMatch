package com.example.invesmatch

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recommendation = intent.getStringExtra("recommendation")
        val entity1 = intent.getStringExtra("entity1")
        val entity2 = intent.getStringExtra("entity2")
        val ganancia1 = intent.getFloatExtra("ganancia1",0F)
        val ganancia2 = intent.getFloatExtra("ganancia2",0F)
        val roi1 = intent.getFloatExtra("roi1",0F)
        val roi2 = intent.getFloatExtra("roi2",0F)

        val textViewEntity1 = findViewById<TextView>(R.id.entityResult1)
        val textViewGanancia1 = findViewById<TextView>(R.id.earn1)
        val textViewRoi1 = findViewById<TextView>(R.id.roi1)

        val textViewEntity2 = findViewById<TextView>(R.id.entityResult2)
        val textViewGanancia2 = findViewById<TextView>(R.id.earn2)
        val textViewRoi2 = findViewById<TextView>(R.id.roi2)

        textViewEntity1.text = entity1
        textViewGanancia1.text = "Ganancia: $ganancia1"
        textViewRoi1.text = "ROI: $roi1"

        textViewEntity2.text = entity2
        textViewGanancia2.text = "Ganancia: $ganancia2"
        textViewRoi2.text = "ROI: $roi2"

        val edResult = findViewById<TextView>(R.id.recommendation)
        edResult.text=recommendation


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}