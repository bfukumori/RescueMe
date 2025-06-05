package br.com.fiap.rescueme

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class RescuerDetailsFragment : DialogFragment() {

    companion object {
        const val TAG = "RescuerDetailsFragment"
        private const val ARG_DISASTER_TYPE = "disaster_type"

        fun newInstance(disasterType: String): RescuerDetailsFragment {
            val fragment = RescuerDetailsFragment()
            val args = Bundle().apply {
                putString(ARG_DISASTER_TYPE, disasterType)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext())
            .inflate(R.layout.fragment_rescuer_details, null)

        val disasterType = arguments?.getString(ARG_DISASTER_TYPE) ?: "Desconhecido"
        val tvRescuerName: TextView = view.findViewById(R.id.tvRescuerName)
        val tvRescuerPhone: TextView = view.findViewById(R.id.tvRescuerPhone)
        val tvExpectedArrivalTime: TextView = view.findViewById(R.id.tvExpectedArrivalTime)
        val tvDisasterType: TextView = view.findViewById(R.id.tvDisasterType)

        val rescuerName: String
        val rescuerPhone: String
        val arrivalTime: String

        when (disasterType) {
            "Enchente" -> {
                rescuerName = "Capitão Silva"
                rescuerPhone = "(11) 98765-4321"
                arrivalTime = "30 minutos"
            }

            "Terremoto" -> {
                rescuerName = "Sargento Mendes"
                rescuerPhone = "(11) 99876-1234"
                arrivalTime = "45 minutos"
            }

            else -> {
                rescuerName = "Equipe de Resgate"
                rescuerPhone = "(11) 91234-5678"
                arrivalTime = "Em breve"
            }
        }

        tvRescuerName.text = "Nome do Resgatista: $rescuerName"
        tvRescuerPhone.text = "Telefone: $rescuerPhone"
        tvExpectedArrivalTime.text = "Tempo Estimado: $arrivalTime"
        tvDisasterType.text = "Tipo de Desastre: $disasterType"

        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton("Fechar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}