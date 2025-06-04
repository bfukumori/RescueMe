package br.com.fiap.rescueme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirefighterDetailsFragment : Fragment() {

    companion object {
        const val TAG = "FirefighterDetailsFragment"
        private const val ARG_DISASTER_TYPE = "disaster_type"

        fun newInstance(disasterType: String): FirefighterDetailsFragment {
            val fragment = FirefighterDetailsFragment()
            val args = Bundle().apply {
                putString(ARG_DISASTER_TYPE, disasterType)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout para este fragmento
        val view = inflater.inflate(R.layout.fragment_firefighter_details, container, false)

        val disasterType = arguments?.getString(ARG_DISASTER_TYPE) ?: "Desconhecido"
        val tvFirefighterName: TextView = view.findViewById(R.id.tvFirefighterName)
        val tvFirefighterPhone: TextView = view.findViewById(R.id.tvFirefighterPhone)
        val tvExpectedArrivalTime: TextView = view.findViewById(R.id.tvExpectedArrivalTime)
        val tvDisasterType: TextView = view.findViewById(R.id.tvDisasterType)

        // Simula dados do bombeiro com base no tipo de desastre
        val firefighterName: String
        val firefighterPhone: String
        val arrivalTime: String

        when (disasterType) {
            "Enchente" -> {
                firefighterName = "Capitão Silva"
                firefighterPhone = "(11) 98765-4321"
                arrivalTime = "30 minutos"
            }

            "Terremoto" -> {
                firefighterName = "Sargento Mendes"
                firefighterPhone = "(11) 99876-1234"
                arrivalTime = "45 minutos"
            }
            // Adicione mais casos para outros tipos de desastre, se necessário
            else -> {
                firefighterName = "Equipe de Resgate"
                firefighterPhone = "(11) 91234-5678"
                arrivalTime = "Em breve"
            }
        }

        tvFirefighterName.text = "Nome do Bombeiro: $firefighterName"
        tvFirefighterPhone.text = "Telefone: $firefighterPhone"
        tvExpectedArrivalTime.text = "Tempo Estimado: $arrivalTime"
        tvDisasterType.text = "Tipo de Desastre: $disasterType"

        return view
    }
}