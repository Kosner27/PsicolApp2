package com.kosner.psicolapp

import android.annotation.SuppressLint
import android.content.ContextParams
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request


class Registro : AppCompatActivity() {
    // Declaración de variables para las vistas de entrada y botón
    private lateinit var Name: EditText
    private lateinit var Apellido: EditText
    private lateinit var Compania: EditText
    private lateinit var Correo: EditText
    private lateinit var Contrasena: EditText
    private lateinit var Cedula: EditText
    private lateinit var Nit: EditText
    private lateinit var btnRegistro: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
// Inicialización de las vistas de entrada y el botón
        Name = findViewById(R.id.Name)
        Apellido = findViewById(R.id.Apellido)
        Compania = findViewById(R.id.Compania)
        Correo = findViewById(R.id.Correo)
        Contrasena = findViewById(R.id.Contrsena)
        Cedula = findViewById(R.id.Cedula)
        Nit=findViewById(R.id.Nit)
        btnRegistro = findViewById(R.id.BtnRegistrar)
    }

    // Función llamada cuando se hace clic en el botón de registro
    fun clickBtnInsertar(view: View) {
        // URL de la solicitud POST para guardar datos en el servidor
        val url = "http://192.168.1.11/PsicolApp/save.php"
        // Crear una cola de solicitudes Volley
        val queue = Volley.newRequestQueue(this)
        // Crear una solicitud de tipo StringRequest para una solicitud POST
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            // Manejar la respuesta exitosa del servidor
            { response ->
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                val intent = Intent(this, Index::class.java)
                startActivity(intent)
                // Manejar errores de la solicitud
            }, { error -> Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show() }
        ) {
            override fun getParams(): MutableMap<String, String> {
                // Definir los parámetros que se enviarán en la solicitud POST
                val parametros = HashMap<String, String>()
                parametros.put("Cedula", Cedula?.text.toString())
                parametros.put("Nombre", Name?.text.toString())
                parametros.put("Apellido", Apellido?.text.toString())
                parametros.put("email", Correo?.text.toString())
                parametros.put("contrasena", Contrasena?.text.toString())
                parametros.put("nombreCompania", Compania?.text.toString())
                parametros.put("NIT",Nit?.text.toString())

                return parametros
            }
        }
// Agregar la solicitud a la cola de solicitudes
    queue.add(resultadoPost)
    }

}


