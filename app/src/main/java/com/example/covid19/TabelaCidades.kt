package com.example.covid19

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaCidades(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria() {
        db.execSQL("CREATE TABLE " + NOME_TABELA + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CAMPO_NOME + " TEXT NOT NULL " +
                ")")
    }
    companion object{
        const val NOME_TABELA = "cidades"
        const val  CAMPO_NOME = "nome"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME)
    }

}
