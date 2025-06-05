package br.com.fiap.rescueme

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShelterAdapter(private val shelters: List<Shelter>) :
    RecyclerView.Adapter<ShelterAdapter.ShelterViewHolder>() {

    class ShelterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvShelterName: TextView = itemView.findViewById(R.id.tvShelterName)
        val tvShelterAddress: TextView = itemView.findViewById(R.id.tvShelterAddress)
        val tvShelterDistance: TextView = itemView.findViewById(R.id.tvShelterDistance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShelterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shelter, parent, false)
        return ShelterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShelterViewHolder, position: Int) {
        val shelter = shelters[position]
        holder.tvShelterName.text = shelter.name
        holder.tvShelterAddress.text = shelter.address
        holder.tvShelterDistance.text = "Distância: ${shelter.distance}"

        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(shelter.mapUrl))
            if (intent.resolveActivity(holder.itemView.context.packageManager) != null) {
                holder.itemView.context.startActivity(intent)
            } else {
                // Mostrar um Toast se não houver app para abrir o link
                // Toast.makeText(holder.itemView.context, "Não foi possível abrir o mapa.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return shelters.size
    }
}