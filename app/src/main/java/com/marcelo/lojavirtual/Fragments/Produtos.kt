package com.marcelo.lojavirtual.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.marcelo.lojavirtual.Model.Dados
import com.marcelo.lojavirtual.R
import com.marcelo.lojavirtual.databinding.FragmentProdutosBinding
import com.squareup.picasso.Picasso
import com.xwray.groupie.*

class Produtos : Fragment() {


    private lateinit var Adapter: GroupAdapter<ViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produtos, container, false)
    }

    private var fragmentProdutos: FragmentProdutosBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bindingProdutos = FragmentProdutosBinding.bind(view)
        fragmentProdutos = bindingProdutos

        val recycler_produtos = bindingProdutos.recyclerProdutos

        Adapter = GroupAdapter()
        recycler_produtos.adapter = Adapter
        Adapter.setOnItemClickListener { item, view ->
            Toast.makeText(context, "Item Clicado", Toast.LENGTH_LONG).show()
        }

        BuscarProdutos()
    }

    private inner class ProdutosItem(internal val adProducts: Dados) : Item<ViewHolder>() {
        override fun getLayout(): Int {
            return R.layout.lista_produtos
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {

            viewHolder.itemView.findViewById<TextView>(R.id.nomeProduto).text = adProducts.nome
            viewHolder.itemView.findViewById<TextView>(R.id.precoProduto).text = adProducts.preco
            var fotoProduto = viewHolder.itemView.findViewById<ImageView>(R.id.imagemProduto)
            Picasso.get().load(adProducts.uid).into(fotoProduto)
        }
    }

    private fun BuscarProdutos() {
        FirebaseFirestore.getInstance().collection("Produtos")
            .addSnapshotListener { snapshot, exception ->
                exception?.let {
                    return@addSnapshotListener
                }
                snapshot?.let {
                    for (doc in snapshot) {
                        val produtos = doc.toObject(Dados::class.java)
                        Adapter.add(ProdutosItem(produtos))
                    }
                }
            }

    }
}

