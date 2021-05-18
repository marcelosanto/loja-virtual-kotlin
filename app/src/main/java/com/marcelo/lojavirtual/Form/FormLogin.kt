package com.marcelo.lojavirtual.Form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.ActionBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.marcelo.lojavirtual.R
import com.marcelo.lojavirtual.TelaPrincipal
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

        binding.btnEntrar.setOnClickListener {
            AutenticarUsuario()
        }
    }

    private fun AutenticarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if(email.isEmpty() || senha.isEmpty()){
            var snackbar = Snackbar.make(binding.layoutLogin, "Coloque um email ou senha", Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)
                .setAction("OK", { })
            snackbar.show()
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener{
                if(it.isSuccessful){
                    binding.frameL.visibility = View.VISIBLE
                    Handler(Looper.getMainLooper()).postDelayed({AbrirTelaPrincipal()}, 3000)

                }
            }.addOnFailureListener{
                var snackbar = Snackbar.make(binding.layoutLogin, "Error ao logar usuário", Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(Color.WHITE).setTextColor(Color.BLACK)
                    .setAction("OK", { })
                snackbar.show()
            }
        }
    }

    private  fun AbrirTelaPrincipal() {
        var intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }
}