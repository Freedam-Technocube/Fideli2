package fr.technocube.fideli.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.technocube.fideli.ClientModel
import fr.technocube.fideli.ClientRepository.Singleton.clientList
import fr.technocube.fideli.MainActivity
import fr.technocube.fideli.R
import fr.technocube.fideli.adapter.ClientAdapter
import fr.technocube.fideli.adapter.ClientItemDecoration

class HomeFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false) ?: return null





        //recuperer le recylclerview horizontal
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = ClientAdapter(context, clientList, R.layout.item_horizontal_client)

        //recuperer le recylclerview vertical
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = ClientAdapter(context, clientList, R.layout.item_vertical_client)
        verticalRecyclerView.addItemDecoration(ClientItemDecoration())

        return view
    }
}