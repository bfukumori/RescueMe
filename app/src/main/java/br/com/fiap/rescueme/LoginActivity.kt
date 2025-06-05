package br.com.fiap.rescueme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail: EditText = findViewById(R.id.etEmail)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val tvRegisterLink: TextView = findViewById(R.id.tvRegisterLink)

        // Lógica do botão de Login (simulada)
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Simulação de login: para fins de teste, aceite qualquer coisa.
                // Em uma aplicação real, você faria a validação aqui (API, Firebase, etc.)
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show()

                // Navegar para a MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Opcional: Finalizar LoginActivity para que o usuário não volte para ela
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // Lógica do link para a tela de Cadastro
        tvRegisterLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}