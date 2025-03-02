package com.example.echo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecoverActivity : AppCompatActivity() {

    private lateinit var btt_recover:Button
    private lateinit var btt_backMenuRecover:Button
    private  lateinit var et_emailRecover:EditText
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        Log.d("RegisterActivity", "onCreate: Activity Recover iniciada")

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        et_emailRecover = findViewById(R.id.et_emailRecover)
        btt_backMenuRecover = findViewById(R.id.btt_backMenuRecover)
        btt_recover = findViewById(R.id.btt_recover)


        btt_recover.setOnClickListener{
            if(validarCampos()){
                if(verificarCorreo()) {
                    //redireccionamiento
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

        }


        btt_backMenuRecover.setOnClickListener{
            //redireccionamiento
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
    private fun validarCampos(): Boolean {
        val mail = et_emailRecover.text.toString().trim()
        var validado = true

        if (mail.isEmpty()) {
            et_emailRecover.error = "El correo es obligatorio"
            validado = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            et_emailRecover.error = "Correo inválido"
            validado = false
        }
        return validado
    }
    private fun verificarCorreo():Boolean {
        val mail = et_emailRecover.text.toString().trim()
        val correoRegistrado = sharedPreferences.getString("mail", "")

        if (mail == correoRegistrado) {
            // Mostrar mensaje de éxito
            Toast.makeText(this, "Se ha enviado correo de recuperación", Toast.LENGTH_SHORT).show()
            Log.d("RecoverActivity", "verificarCorreo: Correo encontrado")
            // Mantenerse en la misma pantalla y cerrar después de 3 segundos
            return true
        } else {
            // Mostrar mensaje de error y quedarse en la misma pantalla
            Log.d("RecoverActivity", "verificarCorreo: Correo no encontrado")
            Toast.makeText(this, "No se ha encontrado el correo ingresado, intentelo nuevamente", Toast.LENGTH_SHORT).show()

            // Opcional: resaltar el campo de correo para indicar error
            et_emailRecover.error = "Correo no registrado"
            return false
        }
    }





    override fun onStart() {
        super.onStart()
        Log.d("RecoverActivity", "onStart: Activity Recover esta en primer plano")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RecoverActivity", "onResume: Activity Recover esta ejecutandose")
    }

    override fun onPause() {
        super.onPause()
        Log.d("RecoverActivity", "onPause: Activity Recover ha sido pausada")
    }
    override fun onStop() {
        super.onStop()
        Log.d("RecoverActivity", "onStop: Activity Recover ha sido detenida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RecoverActivity", "onDestroy: Activity Recover ha sido destruida")
    }


}