package br.com.fiap.rescueme

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar os campos de entrada e outros componentes
        val etRegisterName: EditText = findViewById(R.id.etRegisterName)
        val etRegisterCpf: EditText = findViewById(R.id.etRegisterCpf)
        val etRegisterAge: EditText = findViewById(R.id.etRegisterAge)
        val rgGender: RadioGroup = findViewById(R.id.rgGender)
        val etRegisterObservation: EditText = findViewById(R.id.etRegisterObservation)
        val etRegisterAddress: EditText = findViewById(R.id.etRegisterAddress)
        val etRegisterLiveTogether: EditText = findViewById(R.id.etRegisterLiveTogether)
        val spinnerSpecialNeeds: Spinner = findViewById(R.id.spinnerSpecialNeeds)
        val etRegisterEmail: EditText = findViewById(R.id.etRegisterEmail)
        val etRegisterPassword: EditText = findViewById(R.id.etRegisterPassword)
        val etConfirmPassword: EditText = findViewById(R.id.etConfirmPassword)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        // Configurar o Spinner de Necessidades Especiais
        val specialNeedsOptions = arrayOf(
            "Nenhuma",
            "Deficiência Física",
            "Deficiência Visual",
            "Deficiência Auditiva",
            "Deficiência Intelectual",
            "Mobilidade Reduzida",
            "Outra"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, specialNeedsOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSpecialNeeds.adapter = adapter

        btnRegister.setOnClickListener {
            val name = etRegisterName.text.toString().trim()
            val cpf = etRegisterCpf.text.toString().trim()
            val age = etRegisterAge.text.toString().trim()
            val observation = etRegisterObservation.text.toString().trim()
            val address = etRegisterAddress.text.toString().trim()
            val liveTogether = etRegisterLiveTogether.text.toString().trim()
            val email = etRegisterEmail.text.toString().trim()
            val password = etRegisterPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            // Obter o gênero selecionado
            val selectedGenderId = rgGender.checkedRadioButtonId
            val gender = when (selectedGenderId) {
                R.id.rbMale -> "Masculino"
                R.id.rbFemale -> "Feminino"
                R.id.rbOther -> "Outro"
                else -> "" // Nenhum gênero selecionado
            }

            // Obter a necessidade especial selecionada do Spinner
            val specialNeed = spinnerSpecialNeeds.selectedItem.toString()

            // Validação básica dos campos
            if (name.isEmpty() || cpf.isEmpty() || age.isEmpty() || gender.isEmpty() ||
                address.isEmpty() || liveTogether.isEmpty() || email.isEmpty() ||
                password.isEmpty() || confirmPassword.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Por favor, preencha todos os campos obrigatórios.",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener // Impede o restante da execução
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validação de CPF (básica, sem algoritmo de validação)
            if (cpf.length != 11) {
                Toast.makeText(this, "CPF deve conter 11 dígitos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validação de e-mail (básica)
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Por favor, insira um e-mail válido.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Validação de idade (exemplo)
            val ageInt = age.toIntOrNull()
            if (ageInt == null || ageInt < 0 || ageInt > 120) {
                Toast.makeText(this, "Por favor, insira uma idade válida.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Validação de número de pessoas na residência
            val liveTogetherInt = liveTogether.toIntOrNull()
            if (liveTogetherInt == null || liveTogetherInt <= 0) {
                Toast.makeText(
                    this,
                    "O número de pessoas na residência deve ser um número válido maior que zero.",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }


            // Se todas as validações passarem
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
            // Aqui você enviaria os dados para um backend (API, Firebase, etc.)
            // Exemplo de como você teria os dados:
            val userData = """
                Nome: $name
                CPF: $cpf
                Idade: $ageInt
                Gênero: $gender
                Necessidade Especial: $specialNeed
                Observação: $observation
                Endereço: $address
                Pessoas na Residência: $liveTogetherInt
                Email: $email
            """.trimIndent()
            // Log.d("RegisterActivity", userData) // Para ver no Logcat

            finish() // Voltar para a tela de login após o cadastro
        }
    }
}