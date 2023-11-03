package com.kosner.psicolapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Cerrar()
    }


    fun Cerrar() {
        val btnRegistro = findViewById<Button>(R.id.CerrarSesion)
        btnRegistro.setOnClickListener {
            val intent = Intent(this, Index::class.java)
            startActivity(intent)
        }
    }
}