package com.marcelo.lojavirtual.Fragments

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.marcelo.lojavirtual.R
import com.marcelo.lojavirtual.databinding.ActivityCadastroProdutosBinding
import com.marcelo.lojavirtual.databinding.ActivityFormLoginBinding

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
}