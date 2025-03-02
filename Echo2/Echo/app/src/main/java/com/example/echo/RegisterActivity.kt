package com.example.echo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity: AppCompatActivity() {

    private lateinit var et_nameRegister:EditText
    private lateinit var et_lastnameRegister:EditText
    private lateinit var et_emailRegister:EditText
    private lateinit var et_phoneRegister:EditText
    private lateinit var et_passwordRegister:EditText
    private lateinit var et_confirmPasswordRegister:EditText
    private lateinit var btt_completeRegister:Button
    private lateinit var btt_backMenuRegister:Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Log.d("RegisterActivity", "onCreate: Activity Register iniciada")

        //Inicializar las variables}

        //Aqui se guardaran los datos de las personas que ingresen a la aplicacion
        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        //Inicializar variables de vistas
        et_nameRegister = findViewById(R.id.et_nameRegister)
        et_lastnameRegister = findViewById(R.id.et_lastnameRegister)
        et_emailRegister = findViewById(R.id.et_emailRegister)
        et_phoneRegister = findViewById(R.id.et_phoneRegister)
        et_passwordRegister = findViewById(R.id.et_passwordRegister)
        et_confirmPasswordRegister = findViewById(R.id.et_confirmPasswordRegister)
        btt_completeRegister = findViewById(R.id.btt_completeRegister)
        btt_backMenuRegister = findViewById(R.id.btt_backMenuRegister)

        //Configuracion listener boton de registro
        btt_completeRegister.setOnClickListener{
            if(validarCampos()){
                //metodo de guardar datos de usuario
                guardarDatosUsuario()
                //redireccionamiento
                val intent = Intent(this,LogginActivity::class.java)
                startActivity(intent)
                finish()

            }

        }
        btt_backMenuRegister.setOnClickListener{
            //redireccionamiento
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
    private fun validarCampos(): Boolean {
        val nombre = et_nameRegister.text.toString().trim()
        val apellido = et_lastnameRegister.text.toString().trim()
        val mail = et_emailRegister.text.toString().trim()
        val telefono = et_phoneRegister.text.toString().trim()
        val clave = et_passwordRegister.text.toString().trim()
        val confirmar = et_confirmPasswordRegister.text.toString().trim()

        var validado = true

        if (nombre.isEmpty()) {
            et_nameRegister.error = "El nombre es obligatorio"
            validado = false
        }
        if (apellido.isEmpty()) {
            et_lastnameRegister.error = "El apellido es obligatorio"
            validado = false
        }
        if (mail.isEmpty()) {
            et_emailRegister.error = "El correo es obligatorio"
            validado = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            et_emailRegister.error = "Correo inválido"
            validado = false
        }
        if (telefono.isEmpty()) {
            et_phoneRegister.error = "El teléfono es obligatorio"
            validado = false
        }
        if (clave.isEmpty()) {
            et_passwordRegister.error = "La clave es obligatoria"
            validado = false
        }
        if (confirmar.isEmpty()) {
            et_confirmPasswordRegister.error = "Debes confirmar tu clave"
            validado = false
        } else if (clave != confirmar) {
            et_confirmPasswordRegister.error = "Las claves no coinciden"
            validado = false
        }

        return validado
    }
    private fun guardarDatosUsuario(){
        val editor = sharedPreferences.edit()
        editor.putString("nombre", et_nameRegister.text.toString().trim())
        editor.putString("apellido", et_lastnameRegister.text.toString().trim())
        editor.putString("mail", et_emailRegister.text.toString().trim())
        editor.putString("telefono", et_phoneRegister.text.toString().trim())
        editor.putString("clave", et_passwordRegister.text.toString().trim())

        editor.apply()
        Log.d("RegisterActivity", "guardarDatosUsuario: Datos del usuario guardados")
        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }



    override fun onStart() {
        super.onStart()
        Log.d("RegisterActivity", "onStart: Activity Register esta en primer plano")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RegisterActivity", "onResume: Activity Register esta ejecutandose")
    }

    override fun onPause() {
        super.onPause()
        Log.d("RegisterActivity", "onPause: Activity Register ha sido pausada")
    }
    override fun onStop() {
        super.onStop()
        Log.d("RegisterActivity", "onStop: Activity Register ha sido detenida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RegisterActivity", "onDestroy: Activity Register ha sido destruida")
    }

}