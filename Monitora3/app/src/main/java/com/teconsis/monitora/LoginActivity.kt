package com.teconsis.monitora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (isValidCredentials(email, password)) {
                // Credenciais válidas, fazer o login
                login()
            } else {
                // Credenciais inválidas, exibir mensagem de erro
                Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidCredentials(email: String, password: String): Boolean {
        // Verificar se as credenciais são válidas (lógica personalizada)
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun login() {
        // Lógica de login bem-sucedido
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}