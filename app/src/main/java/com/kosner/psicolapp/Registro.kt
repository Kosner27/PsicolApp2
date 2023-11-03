package com.kosner.psicolapp
import org.json.JSONObject
import android.annotation.SuppressLint
import android.content.ContextParams
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.privacysandbox.tools.core.model.Method
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import android.util.Log
import java.nio.charset.Charset

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

        // Agregar un Listener para el botón de registro
        btnRegistro.setOnClickListener {
            // Llamamos a una función para realizar la validación
            if (validarCampos()) {
                // Si los campos están llenos, realizar la solicitud POST
                realizarRegistro()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Función para realizar la validación de campos
    private fun validarCampos(): Boolean {
        return !Cedula.text.isNullOrEmpty() &&
                !Name.text.isNullOrEmpty() &&
                !Apellido.text.isNullOrEmpty() &&
                !Correo.text.isNullOrEmpty() &&
                !Contrasena.text.isNullOrEmpty() &&
                !Compania.text.isNullOrEmpty() &&
                !Nit.text.isNullOrEmpty()
    }

    // Función para realizar la solicitud POST
    private fun realizarRegistro() {
        val url = "http://192.168.1.9/PsicolApp/save.php"
        val queue = Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            { response ->
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show()
                val intent = Intent(this, Index::class.java)
                startActivity(intent)
            },
            { error ->
                val errorMessage = "Error $error"
                Log.e("ErrorRegistro", errorMessage)
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
            override fun getBody(): ByteArray {
                val jsonParams = JSONObject()
                jsonParams.put("Cedula", Cedula.text.toString())
                jsonParams.put("Nombre", Name.text.toString())
                jsonParams.put("Apellido", Apellido.text.toString())
                jsonParams.put("email", Correo.text.toString())
                jsonParams.put("contrasena", Contrasena.text.toString())
                jsonParams.put("nombreCompania", Compania.text.toString())
                jsonParams.put("NIT", Nit.text.toString())

                return jsonParams.toString().toByteArray(Charset.defaultCharset())
            }
        }
        queue.add(resultadoPost)
    }
}


