package com.marcelo.lojavirtual.Form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.marcelo.lojavirtual.R
import com.marcelo.lojavirtual.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.textTelaDeCadastro.setOnClickListener{
            var intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }
    }
}