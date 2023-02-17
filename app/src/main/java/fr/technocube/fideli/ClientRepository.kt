package fr.technocube.fideli

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.technocube.fideli.ClientRepository.Singleton.clientList
import fr.technocube.fideli.ClientRepository.Singleton.databaseRef
import javax.security.auth.callback.Callback

class ClientRepository {

    object Singleton {

        //Se connecter a la reference "Clients" sur firebase
        val databaseRef = FirebaseDatabase.getInstance().getReference("clients")

        //Créer uen liste qui va contenir les clients
        val clientList = arrayListOf<ClientModel>()

    }

    fun updateData(callback: () -> Unit) {

        // recuperer les donnés depuis la databaseRef pour les envoyer dans la liste des clients
        databaseRef .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciens client pour recharger une liste toute neuve
                clientList.clear()

                //recuperer la liste

                for (ds in snapshot.children) {
                    //construire un objet client
                    val client = ds.getValue(ClientModel::class.java)

                    //verifier que le client n'est pas null
                    if(client != null){
                        //ajouter le client a la liste
                        clientList.add(client)
                    }
                }
                //actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {}


        })


    }

}