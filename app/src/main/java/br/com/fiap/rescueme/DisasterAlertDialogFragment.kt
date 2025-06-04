package br.com.fiap.rescueme

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DisasterAlertDialogFragment : DialogFragment() {

    // Constantes para as chaves dos argumentos
    companion object {
        const val TAG = "DisasterAlertDialogFragment" // Tag para identificar o fragmento
        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"
        private const val ARG_DISASTER_TYPE = "disaster_type" // Exemplo: tipo de desastre
    }

    // Método de fábrica para criar uma nova instância do DialogFragment com argumentos
    fun newInstance(
        title: String,
        message: String,
        disasterType: String
    ): DisasterAlertDialogFragment {
        val fragment = DisasterAlertDialogFragment()
        val args = Bundle().apply {
            putString(ARG_TITLE, title)
            putString(ARG_MESSAGE, message)
            putString(ARG_DISASTER_TYPE, disasterType)
        }
        fragment.arguments = args
        return fragment
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Recuperar os argumentos passados para o fragmento
        val title = arguments?.getString(ARG_TITLE) ?: "Alerta de Desastre"
        val message = arguments?.getString(ARG_MESSAGE) ?: "Houve um desastre natural."
        val disasterType = arguments?.getString(ARG_DISASTER_TYPE) ?: "Desconhecido"

        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setIcon(android.R.drawable.ic_dialog_alert) // Ícone de alerta padrão do Android
            .setNegativeButton("Preciso de ajuda") { dialog, _ ->
                // Notificar o listener sobre o clique negativo, passando o tipo de desastre
                listener?.onDisasterDialogNegativeClick(this, disasterType) // Passa o disasterType
                dialog.dismiss()
            }
            .setPositiveButton("Não preciso de ajuda") { dialog, _ ->
                // Notificar o listener sobre o clique positivo
                listener?.onDisasterDialogPositiveClick(this)
                dialog.dismiss() // Fecha o diálogo
            }
            .create()
    }

    // Opcional: Implementar callbacks para a Activity/Fragment que chamou o diálogo
    // Isso é útil se você precisa que a tela que chamou o diálogo saiba qual botão foi clicado.
    interface DisasterDialogListener {
        fun onDisasterDialogPositiveClick(dialog: DialogFragment)
        fun onDisasterDialogNegativeClick(
            dialog: DialogFragment,
            disasterType: String
        ) // Adicionamos disasterType aqui
    }

    // Usar um listener para comunicar de volta à Activity/Fragment
    var listener: DisasterDialogListener? = null

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        // Verifica se a Activity ou o Fragment host implementa a interface
        try {
            listener = context as DisasterDialogListener
        } catch (e: ClassCastException) {
            // Se não implementar, verifica se é um Fragment pai
            val parentFragment = parentFragment
            if (parentFragment is DisasterDialogListener) {
                listener = parentFragment
            } else {
                // Se nenhum host implementar, um erro é lançado
                // throw ClassCastException("$context must implement DisasterDialogListener")
                // Ou você pode apenas não usar o listener se não for obrigatório
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null // Libera a referência para evitar vazamentos de memória
    }
}