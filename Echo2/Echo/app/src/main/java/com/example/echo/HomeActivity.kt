package com.example.echo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var btt_start: Button
    private lateinit var btt_register: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("HomeActivity", "onCreate: Activity Home inicio")

        btt_start = findViewById(R.id.btt_start)
        btt_register = findViewById(R.id.btt_register)

        btt_start.setOnClickListener{
                //redireccionamiento
                val intent = Intent(this,LogginActivity::class.java)
                startActivity(intent)
                finish()

        }
        btt_register.setOnClickListener{
            //redireccionamiento
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()

        }


    }
    override fun onStart() {
        super.onStart()
        Log.d("HomeActivity", "onStart: Activity Home esta en primer plano")
    }

    override fun onResume() {
        super.onResume()
        Log.d("HomeActivity", "onResume: Activity Home esta ejecutandose")
    }

    override fun onPause() {
        super.onPause()
        Log.d("HomeActivity", "onPause: Activity Home ha sido pausada")
    }
    override fun onStop() {
        super.onStop()
        Log.d("HomeActivity", "onStop: Activity Home ha sido detenida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("HomeActivity", "onDestroy: Activity Home ha sido destruida")
    }
}