package com.example.echo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LogginActivity: AppCompatActivity() {

    private lateinit var et_userLoggin: EditText
    private lateinit var et_passwordLoggin: EditText
    private lateinit var btt_recoverpass: Button
    private lateinit var btt_registerLoggin: Button
    private lateinit var btt_loggin: Button
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggin)


        Log.d("RegisterActivity", "onCreate: Activity Loggin iniciada")

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        et_userLoggin = findViewById(R.id.et_userLoggin)
        et_passwordLoggin = findViewById(R.id.et_passwordLoggin)
        btt_recoverpass = findViewById(R.id.btt_recoverpass)
        btt_registerLoggin = findViewById(R.id.btt_registerLoggin)
        btt_loggin = findViewById(R.id.btt_loggin)

        btt_recoverpass.setOnClickListener {
            //redireccionamiento
            val intent = Intent(this, RecoverActivity::class.java)
            startActivity(intent)
            finish()

        }


        btt_registerLoggin.setOnClickListener {
            //redireccionamiento
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()

        }
        btt_loggin.setOnClickListener {
            if (validarCampos()) {
                if(verificarDatos()) {

                    //redireccionamiento
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }


        }
    }

    private fun validarCampos(): Boolean {
        val mail = et_userLoggin.text.toString().trim()
        val clave = et_passwordLoggin.text.toString().trim()

        var validado = true

        if (mail.isEmpty()) {
            et_userLoggin.error = "El correo es obligatorio"
            validado = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            et_userLoggin.error = "Correo inválido"
            validado = false}
        if (clave.isEmpty()) {
            et_passwordLoggin.error = "Verifique la contraseña"
            validado = false
        }


        return validado
    }
    private fun verificarDatos(): Boolean {
        val mail = et_userLoggin.text.toString().trim()
        val clave = et_passwordLoggin.text.toString().trim()

        val correoRegistrado = sharedPreferences.getString("mail", null)
        val claveRegistrada = sharedPreferences.getString("clave", null)

        if (mail == correoRegistrado && clave == claveRegistrada) {
            Log.d("LogginActivity", "verificarDatos: Usuario autenticado correctamente")
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
            return true
        } else {
            if (mail != correoRegistrado) {
                Log.d("LogginActivity", "verificarDatos: Correo no encontrado")
                et_userLoggin.error = "Correo incorrecto"
            }
            if (clave != claveRegistrada) {
                Log.d("LogginActivity", "verificarDatos: Clave incorrecta")
                et_passwordLoggin.error = "Clave incorrecta"
            }
            Toast.makeText(this, "Credenciales incorrectas, intenta de nuevo", Toast.LENGTH_SHORT).show()
            return false
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("LogginActivity", "onStart: Activity Loggin esta en primer plano")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LogginActivity", "onResume: Activity Loggin esta ejecutandose")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LogginActivity", "onPause: Activity Loggin ha sido pausada")
    }
    override fun onStop() {
        super.onStop()
        Log.d("LogginActivity", "onStop: Activity Loggin ha sido detenida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LogginActivity", "onDestroy: Activity Loggin ha sido destruida")
    }
}