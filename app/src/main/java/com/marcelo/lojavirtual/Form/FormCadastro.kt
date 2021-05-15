package com.marcelo.lojavirtual.Form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcelo.lojavirtual.R

class FormCadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)

        supportActionBar!!.hide()
    }
}