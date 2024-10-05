package com.example.invesmatch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class InvestorProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investor_profile)


        //para permitir volver a la main activity

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //para recibir la data de main

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")

        val textView = findViewById<TextView>(R.id.welcomeUser)
        textView.text = "Hola $firstName"

        val btnOk = findViewById<Button>(R.id.btnOk)

        val rgExperience = findViewById<RadioGroup>(R.id.rgExperience)
        val rgMonthSaving = findViewById<RadioGroup>(R.id.rgMonthSaving)
        val rgSavingInvestment = findViewById<RadioGroup>(R.id.rgSavingInvestment)


        btnOk.setOnClickListener{

            //Tener en cuenta que devuelve -1 si no hay seleccionado pero pusimos una validacion
            val selectedExperienceId = rgExperience.checkedRadioButtonId
            val selectedMonthSavingId = rgMonthSaving.checkedRadioButtonId
            val selectedSavingInvestmentId = rgSavingInvestment.checkedRadioButtonId

            if (selectedExperienceId == -1 || selectedMonthSavingId == -1 || selectedSavingInvestmentId == -1) {
                Toast.makeText(this, "Por favor, completa todas las opciones.", Toast.LENGTH_SHORT).show()

            }else {
                // Calcular puntaje total
                //Lo inicializamos asi vamos sumando las respuestas
                var puntajeTotal = 0

                    puntajeTotal += obtenerPuntaje(selectedExperienceId)
                    puntajeTotal += obtenerPuntaje(selectedMonthSavingId)
                    puntajeTotal += obtenerPuntaje(selectedSavingInvestmentId)


                val investorType = when {
                    puntajeTotal <= 2 -> "Conservador"
                    puntajeTotal <= 4 -> "Moderado"
                    else -> "Agresivo"
                }

                val intent= Intent(this,UserProfileActivity::class.java).apply{
                    putExtra("firstName", firstName)
                    putExtra("lastName", lastName)
                    putExtra("email", email)
                    putExtra("investorType", investorType)

                }
                startActivity(intent)


                val mensaje = "Puntaje Total: $puntajeTotal Tipo de Inversor: $investorType"
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            }


        }



    }
    private fun obtenerPuntaje(radioButtonId: Int): Int {
        return when (radioButtonId) {
            R.id.rbNone, R.id.rbLess20, R.id.rbLess25 -> 0
            R.id.rbLow, R.id.rb20to50, R.id.rb25to65 -> 1
            R.id.rbMidHigh, R.id.rbMore50, R.id.rbMore65 -> 2
            else -> 0
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}