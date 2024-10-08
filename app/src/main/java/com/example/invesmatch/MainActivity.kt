package com.example.invesmatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Simulo el tiempo de Splashscreen antes del main
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val etUserFirstname= findViewById<EditText>(R.id.etUserFirstname)
        val etUserLastName= findViewById<EditText>(R.id.etUserLastName)
        val etUserEmail= findViewById<EditText>(R.id.etUserEmail)
        val btnStart =findViewById<Button>(R.id.btnNext)

        val userData= getSharedPreferences("initialPref", Context.MODE_PRIVATE)

        //Planteo este estado para saber si estaba logueado
        val wasPrevLogged = userData.getBoolean("isLogged",false)

        if(wasPrevLogged){
            val intent = Intent(this, InvestmentComparisonActivity::class.java)
            startActivity(intent)
            finish()

        }
        btnStart.setOnClickListener{
            val userFirstName=etUserFirstname.text.toString()
            val userLastName= etUserLastName.text.toString()
            val userEmail=etUserEmail.text.toString()

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()|| userLastName.isEmpty()||userFirstName.isEmpty()) {
                Toast.makeText(this, "Ingresa datos validos", Toast.LENGTH_SHORT).show()
            }else{
                //llamo a edit() que es una instancia para tener el objeto edit
                //de donde puedo llamar metodos para modificar
                val editor = userData.edit()
                editor.putBoolean("isLogged", true)
                editor.apply()


                val intent = Intent(this, InvestorProfileActivity::class.java)

                intent.putExtra("firstName", userFirstName)
                intent.putExtra("lastName", userLastName)
                intent.putExtra("email", userEmail)

                startActivity(intent)
            }


        }

    }
}