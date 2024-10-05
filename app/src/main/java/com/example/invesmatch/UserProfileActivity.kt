package com.example.invesmatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.invesmatch.utils.capitalizeString


class UserProfileActivity : AppCompatActivity (){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_complete_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val investorType = intent.getStringExtra("investorType")

        val welcomeMessage = findViewById<TextView>(R.id.tvWelcomeMessage)
        welcomeMessage.text = capitalizeString("Bienvenido, $firstName $lastName")

        val textView = findViewById<TextView>(R.id.tvProfileSummary)
        textView.text = "PERFIL INVERSOR"

        val investorTypeView = findViewById<TextView>(R.id.tvInvestorType)
        investorTypeView.text = investorType

        val investmentComparison=findViewById<Button>(R.id.btnStart)
        investmentComparison.setOnClickListener{
            val intent= Intent(this, InvestmentComparisonActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}