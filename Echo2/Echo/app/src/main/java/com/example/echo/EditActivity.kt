package com.example.echo

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditActivity : AppCompatActivity() {

    private lateinit var et_nameEditor: EditText
    private lateinit var et_lastnameEditor: EditText
    private lateinit var et_emailEditor: EditText
    private lateinit var et_phoneEditor: EditText
    private lateinit var btt_completeEditor: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        Log.d("EditActivity", "onCreate: Activity Edit iniciada")

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        et_nameEditor = findViewById(R.id.et_nameEditor)
        et_lastnameEditor = findViewById(R.id.et_lastnameEditor)
        et_emailEditor = findViewById(R.id.et_emailEditor)
        et_phoneEditor = findViewById(R.id.et_phoneEditor)
        btt_completeEditor = findViewById(R.id.btt_completeEditor)

        if (intent.hasExtra("nombre")) {
            et_nameEditor.setText(intent.getStringExtra("nombre"))
            et_lastnameEditor.setText(intent.getStringExtra("apellido"))
            et_emailEditor.setText(intent.getStringExtra("email"))
            et_phoneEditor.setText(intent.getStringExtra("telefono"))
        }

        // Configurar botón para guardar cambios
        btt_completeEditor.setOnClickListener {
            if (validarCampos()) {
                guardarCambiosUsuario()
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        }
    private fun guardarCambiosUsuario() {
        val editor = sharedPreferences.edit()
        editor.putString("nombre", et_nameEditor.text.toString().trim())
        editor.putString("apellido", et_lastnameEditor.text.toString().trim())
        editor.putString("mail", et_emailEditor.text.toString().trim())
        editor.putString("telefono", et_phoneEditor.text.toString().trim())
        editor.apply()

        Log.d("EditActivity", "guardarDatosUsuario: Datos actualizados")
        Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show()
    }
    private fun validarCampos(): Boolean {
        val nombre = et_nameEditor.text.toString().trim()
        val apellido = et_lastnameEditor.text.toString().trim()
        val email = et_emailEditor.text.toString().trim()
        val telefono = et_phoneEditor.text.toString().trim()

        var validado = true

        if (nombre.isEmpty()) {
            et_nameEditor.error = "El nombre es obligatorio"
            validado = false
        }
        if (apellido.isEmpty()) {
            et_lastnameEditor.error = "El apellido es obligatorio"
            validado = false
        }
        if (email.isEmpty()) {
            et_emailEditor.error = "El correo es obligatorio"
            validado = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_emailEditor.error = "Correo inválido"
            validado = false
        }
        if (telefono.isEmpty()) {
            et_phoneEditor.error = "El teléfono es obligatorio"
            validado = false
        }

        return validado
    }

    override fun onStart() {
        super.onStart()
        Log.d("EditActivity", "onStart: Activity Edit esta en primer plano")
    }

    override fun onResume() {
        super.onResume()
        Log.d("EditActivity", "onResume: Activity Edit esta ejecutandose")
    }

    override fun onPause() {
        super.onPause()
        Log.d("EditActivity", "onPause: Activity Edit ha sido pausada")
    }
    override fun onStop() {
        super.onStop()
        Log.d("EditActivity", "onStop: Activity Edit ha sido detenida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("EditActivity", "onDestroy: Activity Edit ha sido destruida")
    }

    }
