package br.com.fiap.rescueme

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etRegisterEmail: EditText = findViewById(R.id.etRegisterEmail)
        val etRegisterPassword: EditText = findViewById(R.id.etRegisterPassword)
        val etConfirmPassword: EditText = findViewById(R.id.etConfirmPassword)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email = etRegisterEmail.text.toString()
            val password = etRegisterPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    // Simulação de cadastro: para fins de teste, aceite qualquer coisa.
                    // Em uma aplicação real, você faria o registro de usuário aqui (API, Firebase, etc.)
                    Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    finish() // Voltar para a tela de login após o cadastro
                } else {
                    Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}