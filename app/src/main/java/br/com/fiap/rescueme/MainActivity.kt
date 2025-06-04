package br.com.fiap.rescueme

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

class MainActivity : AppCompatActivity(), DisasterAlertDialogFragment.DisasterDialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showDisasterAlertButton: Button = findViewById(R.id.showDisasterAlertButton)
        showDisasterAlertButton.setOnClickListener {
            showDisasterAlert("Alerta de Enchente", "Previsão de chuvas fortes e risco de alagamentos. Mantenha-se seguro!", "Enchente")
        }
    }

    private fun showDisasterAlert(title: String, message: String, disasterType: String) {
        val dialogFragment = DisasterAlertDialogFragment().newInstance(title, message, disasterType)
        dialogFragment.show(supportFragmentManager, DisasterAlertDialogFragment.TAG)
    }

    // Implementação da interface DisasterDialogListener
    override fun onDisasterDialogPositiveClick(dialog: DialogFragment) {
        Toast.makeText(this, "Ação Positiva (Entendido) do Alerta de Desastre na MainActivity!", Toast.LENGTH_SHORT).show()
    }

    override fun onDisasterDialogNegativeClick(dialog: DialogFragment, disasterType: String) {
        Toast.makeText(this, "Ação Negativa (Mais Detalhes) clicada. Mostrando detalhes do resgatista...", Toast.LENGTH_LONG).show()

        // *** AQUI É A MUDANÇA: AGORA ELE É CHAMADO COMO UM DIALOGFRAGMENT ***
        val rescuerDetailsDialog = RescuerDetailsFragment.newInstance(disasterType)
        rescuerDetailsDialog.show(supportFragmentManager, RescuerDetailsFragment.TAG)
    }
}