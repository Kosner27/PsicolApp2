package com.kosner.psicolapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.mindrot.jbcrypt.BCrypt


class IncioSesion : AppCompatActivity() {
    // Declaración de variables para las vistas de entrada y botón
    private lateinit var Correo: EditText
    private lateinit var Contrasena: EditText
    private lateinit var Iniciar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inicialización de las vistas de entrada y el botón
        setContentView(R.layout.activity_incio_sesion)
        Correo = findViewById(R.id.correo)
        Contrasena = findViewById(R.id.contrsena)
        Iniciar = findViewById(R.id.Iniciar)
    }
    // Función llamada cuando se hace clic en el botón de inicio de sesión
    fun clickBtnIniciar(view: View) {
        // Obtener el correo y la contraseña ingresados por el usuario
        val correo = Correo?.text.toString()
        val contrasena = Contrasena?.text.toString()

        // Verificar si los campos están vacíos
        if (correo.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_LONG).show()
            return // Salir de la función si hay campos vacíos
        }


// Definir la URL del servidor al que se enviarán los datos para iniciar sesión
        val url = "http://192.168.1.9/PsicolApp/LogIn.php"
        // Crear una cola de solicitudes Volley
        val queue = Volley.newRequestQueue(this)
        // Crear una solicitud de tipo StringRequest para una solicitud POST
        val resultadoPost = object : StringRequest(
            Method.POST, url,
            { response ->
                val respuesta = response.trim()
                if (response.trim() == "sesion iniciada") {
                    // La sesión se ha iniciado con éxito
                    val intent = Intent(this, Main::class.java)
                    startActivity(intent)
                } else {
                    // La sesión no se inició con éxito (correo o contraseña incorrectos)
                    Toast.makeText(this, "Respuesta del servidor: $respuesta", Toast.LENGTH_LONG).show()
                }
            }, { error -> Toast.makeText(this, "Error $error", Toast.LENGTH_LONG).show() }
        ) {
            override fun getParams(): MutableMap<String, String> {
                // Definir los parámetros que se enviarán en la solicitud POST
                val parametros = HashMap<String, String>()
                parametros["email"] = correo
                parametros["contrasena"] = contrasena
                return parametros
            }
        }
// Agregar la solicitud a la cola de solicitudes
        queue.add(resultadoPost)
    }

}