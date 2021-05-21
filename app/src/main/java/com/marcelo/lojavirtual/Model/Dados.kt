package com.marcelo.lojavirtual.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dados(
    val uid: String = " ",
    val nome: String = " ",
    val preco: String = " ",
    val url: String = " "):Parcelable