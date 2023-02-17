package fr.technocube.fideli.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.technocube.fideli.ClientModel
import fr.technocube.fideli.MainActivity
import fr.technocube.fideli.R

class ClientAdapter (
    private val context: MainActivity,
    private val clientList: List<ClientModel>,
    private val layoutId: Int
): RecyclerView.Adapter<ClientAdapter.ViewHolder>(){

    // boite pour ranger tout les composant a controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val clientImage: ImageView = view.findViewById(R.id.image_item)
        val clientName: TextView? = view.findViewById(R.id.name_item)
        val clientFirstname: TextView? = view.findViewById(R.id.firstname_item)
        val clientCategory: TextView? = view.findViewById(R.id.category_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recuperer les infos du client
        val currentClient = clientList[position]

        // utiliser glide pour recuperer une image sur internet
        Glide.with(context).load(Uri.parse(currentClient.imageUrl)).into(holder.clientImage)

        // mettre à jour le nom du client
        holder.clientName?.text = currentClient.name

        // mettre à jour le prenom du client
        holder.clientFirstname?.text = currentClient.firstname

        // mettre à jour la categorie du client
        holder.clientCategory?.text = currentClient.category
    }

    override fun getItemCount(): Int = clientList.size
}
