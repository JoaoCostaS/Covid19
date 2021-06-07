package com.example.covid19

import android.content.ContentValues

data class Cidade (var id: Long = -1, var nome: String) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(TabelaCidades.CAMPO_NOME, nome)

        return valores
    }
}