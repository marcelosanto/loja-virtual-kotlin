package com.marcelo.lojavirtual.Form

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.marcelo.lojavirtual.R
import com.marcelo.lojavirtual.databinding.ActivityFormCadastroBinding
import com.marcelo.lojavirtual.databinding.ActivityFormLoginBinding

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btnCadastrar.setOnClickListener {
            CadastrarUsuario()
        }
    }

    private fun CadastrarUsuario() {
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if(email.isEmpty() || senha.isEmpty()){
            var snackbar = Snackbar.make(binding.layoutCadastro, "Digite seu email e senha!", Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {})
            snackbar.show()
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener{
                if (it.isSuccessful){
                    var snackbar = Snackbar.make(binding.layoutCadastro, "Cadastro realizado com sucesso!", Snackbar.LENGTH_INDEFINITE)
                        .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {})
                    snackbar.show()
                }
            }.addOnFailureListener{
                var snackbar = Snackbar.make(binding.layoutCadastro, "Erro ao cadastrar usuário", Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK).setAction("OK", View.OnClickListener {})
                snackbar.show()
            }
        }
    }
}