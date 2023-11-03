package com.kosner.psicolapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class Index : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

       Registro()
        Inicio()

        }
    fun Registro(){
        val btnRegistro= findViewById<Button>(R.id.Registro)
        btnRegistro.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
                }
}
    fun Inicio(){
        val btnInicio= findViewById<Button>(R.id.Inicio)
        btnInicio.setOnClickListener {
            val intent = Intent(this, IncioSesion::class.java)
            startActivity(intent)
        }
    }
}