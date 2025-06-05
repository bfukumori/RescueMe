package br.com.fiap.rescueme

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), DisasterAlertDialogFragment.DisasterDialogListener {

    private lateinit var sheltersRecyclerView: RecyclerView
    private lateinit var shelterAdapter: ShelterAdapter
    private val shelterList = ArrayList<Shelter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showDisasterAlertButton: Button = findViewById(R.id.showDisasterAlertButton)
        showDisasterAlertButton.setOnClickListener {
            showDisasterAlert(
                "Alerta de Enchente",
                "Previsão de chuvas fortes e risco de alagamentos. Mantenha-se seguro!",
                "Enchente"
            )
        }

        sheltersRecyclerView = findViewById(R.id.sheltersRecyclerView)
        sheltersRecyclerView.layoutManager = LinearLayoutManager(this)
        shelterAdapter = ShelterAdapter(shelterList)
        sheltersRecyclerView.adapter = shelterAdapter

        loadShelterData()
    }

    private fun showDisasterAlert(title: String, message: String, disasterType: String) {
        val dialogFragment = DisasterAlertDialogFragment().newInstance(title, message, disasterType)
        dialogFragment.show(supportFragmentManager, DisasterAlertDialogFragment.TAG)
    }

    override fun onDisasterDialogPositiveClick(dialog: DialogFragment) {
        Toast.makeText(
            this,
            "Ação Positiva (Entendido) do Alerta de Desastre na MainActivity!",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDisasterDialogNegativeClick(dialog: DialogFragment, disasterType: String) {
        Toast.makeText(
            this,
            "Ação Negativa (Mais Detalhes) clicada. Mostrando detalhes do resgatista...",
            Toast.LENGTH_LONG
        ).show()

        val rescuerDetailsDialog = RescuerDetailsFragment.newInstance(disasterType)
        rescuerDetailsDialog.show(supportFragmentManager, RescuerDetailsFragment.TAG)
    }

    private fun loadShelterData() {
        shelterList.add(
            Shelter(
                "Abrigo Reviver",
                "Rua Alvilândia, 490 - Vila Ida, São Paulo - SP",
                "29,2 km",
                "https://maps.google.com/?cid=2116142962029940548"
            )
        )
        shelterList.add(
            Shelter(
                "Instituição Casa Lar Unidos pelo Amor",
                "Rua Belmira Alves Coelho - Estr. Quatro Encruzilhadas, 39 - Vila Belmira, Itapevi - SP",
                "16,5 km",
                "https://maps.google.com/?cid=14461671638165138296"
            )
        )
        shelterList.add(
            Shelter(
                "Amamos Casa de Acolhimento para Crianças e Adolescentes",
                "Av. Pres. Médici, 1303 - Aliança, Osasco - SP",
                "20,7 km",
                "https://maps.google.com/?cid=8197849094199512596"
            )
        )
        shelterList.add(
            Shelter(
                "Casa de Apoio Marta e Maria",
                "R. Catumbi, 427 - Belém, São Paulo - SP",
                "38,7 km",
                "https://maps.google.com/?cid=4552482540048614690"
            )
        )
        shelterList.add(
            Shelter(
                "Lar Vinicius",
                "R. Min. Eduardo de Campos Maia, 25 - Jardim Bonfiglioli, São Paulo - SP",
                "28,9 km",
                "https://maps.google.com/?cid=16727795881649186694"
            )
        )

        shelterAdapter.notifyDataSetChanged()
    }
}