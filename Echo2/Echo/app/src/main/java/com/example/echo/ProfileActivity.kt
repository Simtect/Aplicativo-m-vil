package com.example.echo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btt_editProfile: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        btt_editProfile = findViewById(com.example.echo.R.id.btt_editProfile)

        // Recuperar los datos guardados
        val nombre = sharedPreferences.getString("nombre", "Nombre")
        val apellido = sharedPreferences.getString("apellido", "Apellido")
        val email = sharedPreferences.getString("mail", "correo@example.com")
        val telefono = sharedPreferences.getString("telefono", "0000000000")

        // Asignar los datos a los TextView correspondientes
        findViewById<TextView>(R.id.tv_name_profile).text = nombre
        findViewById<TextView>(R.id.tv_lastname_profile).text = apellido
        findViewById<TextView>(R.id.tv_email_profile).text = email
        findViewById<TextView>(R.id.tv_number_profile).text = telefono


        btt_editProfile.setOnClickListener{
            //redireccionamiento
            val intent = Intent(this,EditActivity::class.java).apply {
                putExtra("nombre", nombre)
                putExtra("apellido", apellido)
                putExtra("email", email)
                putExtra("telefono", telefono)
            }
            startActivity(intent)

        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("ProfileActivity", "onStart: Activity Profile esta en primer plano")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ProfileActivity", "onResume: Activity Profile esta ejecutandose")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ProfileActivity", "onPause: Activity Profile ha sido pausada")
    }
    override fun onStop() {
        super.onStop()
        Log.d("ProfileActivity", "onStop: Activity Profile ha sido detenida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ProfileActivity", "onDestroy: Activity Profile ha sido destruida")
    }
}