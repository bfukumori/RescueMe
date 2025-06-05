package br.com.fiap.rescueme

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DisasterAlertDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "DisasterAlertDialogFragment"
        private const val ARG_TITLE = "title"
        private const val ARG_MESSAGE = "message"
        private const val ARG_DISASTER_TYPE = "disaster_type"
    }

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
        val title = arguments?.getString(ARG_TITLE) ?: "Alerta de Desastre"
        val message = arguments?.getString(ARG_MESSAGE) ?: "Houve um desastre natural."
        val disasterType = arguments?.getString(ARG_DISASTER_TYPE) ?: "Desconhecido"

        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setNegativeButton("Preciso de ajuda") { dialog, _ ->
                listener?.onDisasterDialogNegativeClick(this, disasterType)
                dialog.dismiss()
            }
            .setPositiveButton("Não preciso de ajuda") { dialog, _ ->
                listener?.onDisasterDialogPositiveClick(this)
                dialog.dismiss()
            }
            .create()
    }

    interface DisasterDialogListener {
        fun onDisasterDialogPositiveClick(dialog: DialogFragment)
        fun onDisasterDialogNegativeClick(
            dialog: DialogFragment,
            disasterType: String
        )
    }

    var listener: DisasterDialogListener? = null

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)

        try {
            listener = context as DisasterDialogListener
        } catch (e: ClassCastException) {

            val parentFragment = parentFragment
            if (parentFragment is DisasterDialogListener) {
                listener = parentFragment
            } else {
                // throw ClassCastException("$context must implement DisasterDialogListener")
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}