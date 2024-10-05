package com.example.invesmatch

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.OnCreateContextMenuListener
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class InvestmentComparisonActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investment_comparison)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val etMoney = findViewById<EditText>(R.id.etMoney)
        val etDays = findViewById<EditText>(R.id.etDays)
        val etEntity1 = findViewById<EditText>(R.id.etEntity1)
        val etTna1 = findViewById<EditText>(R.id.etTna1)
        val etEntity2 = findViewById<EditText>(R.id.etEntity2)
        val etTna2 = findViewById<EditText>(R.id.etTna2)

        val btnTerms = findViewById<Button>(R.id.btnTermsAndConditions)
        btnTerms.setOnClickListener{
            val intent = Intent(this, TermsAndConditionsActivity::class.java)
            startActivity(intent)
        }
        val btnHistory = findViewById<Button>(R.id.btnHistory)
        btnHistory.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        val btnCompare = findViewById<Button>(R.id.btnCompare)
        btnCompare.setOnClickListener {
            val money = etMoney.text.toString()
            val days = etDays.text.toString()
            val entity1 = etEntity1.text.toString()
            val tna1 = etTna1.text.toString()
            val entity2 = etEntity2.text.toString()
            val tna2 = etTna2.text.toString()

            val typeInvestment1 = findViewById<RadioGroup>(R.id.typeInvestment1)
            val typeInvestment2 = findViewById<RadioGroup>(R.id.typeInvestment2)

            if (money.isEmpty() || days.isEmpty() || entity1.isEmpty() || tna1.isEmpty()
                || entity2.isEmpty() || tna2.isEmpty()|| typeInvestment1.checkedRadioButtonId == -1 || typeInvestment2.checkedRadioButtonId == -1){
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {

                val moneyInt = money.toInt()
                val daysInt=days.toInt()
                val tna1Int=tna1.toFloat()
                val tna2Int=tna2.toFloat()


               val ganancia1 = tnaCalculate(tna1Int, daysInt, moneyInt)
                val capitalFinal1=moneyInt+ganancia1
                val roi1=((capitalFinal1-moneyInt)/moneyInt)*100

                val ganancia2 = tnaCalculate(tna2Int, daysInt, moneyInt)
                val capitalFinal2=moneyInt+ganancia2
                val roi2=((capitalFinal2-moneyInt)/moneyInt)*100

                val recommendation= if(roi1>roi2){
                    "La opción recomendada es\n$entity1 con roi $roi1"
                }else if(roi2>roi1){
                    "La opción recomendada es\n$entity2 con roi $roi2"
                }else{
                    "Ambas ofrecen el mismo roi de $roi1"
                }

                saveCompare(this, entity1, entity2, ganancia1, ganancia2, roi1, roi2, recommendation)

                etMoney.text.clear()
                etDays.text.clear()
                etEntity1.text.clear()
                etTna1.text.clear()
                etEntity2.text.clear()
                etTna2.text.clear()

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("entity1", entity1)
                intent.putExtra("entity2", entity2)
                intent.putExtra("ganancia1", ganancia1)
                intent.putExtra("ganancia2", ganancia2)
                intent.putExtra("roi1", roi1)
                intent.putExtra("roi2", roi2)
                intent.putExtra("recommendation", recommendation)

                startActivity(intent)
            }
        }
    }


    data class ComparisonEntity(
        val entity1: String,
        val entity2: String,
        val ganancia1: Float,
        val ganancia2: Float,
        val roi1: Float,
        val roi2: Float,
        val recommendation: String
    )


fun saveCompare(
    context: Context,
    entity1: String,
    entity2: String,
    ganancia1: Float,
    ganancia2: Float,
    roi1: Float,
    roi2: Float,
    recommendation: String
) {
    val sharedPreferences = context.getSharedPreferences("recommendations", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val json = sharedPreferences.getString("comparisonsList", "[]")
    val type = object : TypeToken<ArrayList<ComparisonEntity>>() {}.type
    val comparisonsList: ArrayList<ComparisonEntity> = gson.fromJson(json, type)
    val newComparison = ComparisonEntity(entity1, entity2, ganancia1, ganancia2, roi1, roi2, recommendation)
    comparisonsList.add(newComparison)
    if (comparisonsList.size > 5) {
        comparisonsList.removeAt(0)
    }
    val updatedJson = gson.toJson(comparisonsList)
    editor.putString("comparisonsList", updatedJson)
    editor.apply()}


    fun tnaCalculate (tna:Float,days:Int,money:Int):Float{
        val tnaDaily:Float= (tna/360)/100
        val dayEarn =tnaDaily * days
        val roi=  dayEarn*money
        return roi
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}