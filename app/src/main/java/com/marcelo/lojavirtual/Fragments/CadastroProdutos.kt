package com.marcelo.lojavirtual.Fragments

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.FirebaseFirestoreKtxRegistrar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.FirebaseStorage
import com.marcelo.lojavirtual.Model.Dados
import com.marcelo.lojavirtual.R
import com.marcelo.lojavirtual.databinding.ActivityCadastroProdutosBinding
import com.marcelo.lojavirtual.databinding.ActivityFormLoginBinding
import java.util.*

@Suppress("DEPRECATION")
class CadastroProdutos : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroProdutosBinding

    private var SelecionarUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSelecionarFoto.setOnClickListener {
            selecionarFotoDaGaleria()
        }

        binding.btCadastrarProduto.setOnClickListener {
            salvarDadosNoFirebase()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 0) {
            SelecionarUri = data?.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, SelecionarUri)
            binding.fotoProduto.setImageBitmap(bitmap)
            binding.btSelecionarFoto.alpha = 0f
        }
    }

    private fun selecionarFotoDaGaleria() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,0)
    }

    private fun salvarDadosNoFirebase() {
        val nomeArquivo = UUID.randomUUID().toString()
        val referencia = FirebaseStorage.getInstance().getReference(
            "/imagens/${nomeArquivo}")

        SelecionarUri?.let {
            referencia.putFile(it).addOnSuccessListener {
                referencia.downloadUrl.addOnSuccessListener {

                    val url = it.toString()
                    val nome = binding.editNome.text.toString()
                    val preco = binding.editPreco.text.toString()
                    val uid = FirebaseAuth.getInstance().uid

                    val Produtos = Dados(url, nome, preco)
                    FirebaseFirestore.getInstance().collection("Produtos")
                        .add(Produtos)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Produto cadastrado  com sucesso!", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Falha ao cadastrar  o produto!", Toast.LENGTH_SHORT).show()
                        }
                }
            }
        }
    }
}