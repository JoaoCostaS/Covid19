package com.example.covid19

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaCasos(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria() {
        db.execSQL(
            " CREATE TABLE " + NOME_TABELA + " (" +
                    BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CAMPO_INFETADOS + " INTEGER, " +
                    CAMPO_ATIVOS + " INTEGER, " +
                    CAMPO_OBITOS + " INTEGER, " +
                    CAMPO_DATA + " INTEGER NOT NULL, " +
                    CAMPO_ID_CIDADES + " INTEGER NOT NULL," +
                    " FOREIGN KEY(" + CAMPO_ID_CIDADES + ") " +
                    " REFERENCES " + TabelaCidades.NOME_TABELA +
                    ")"
        )
    }
    companion object{
        const val NOME_TABELA = "casos"
        const val CAMPO_INFETADOS = "infetados"
        const val CAMPO_ATIVOS = "ativos"
        const val CAMPO_OBITOS = "obitos"
        const val CAMPO_DATA = "data"
        const val CAMPO_ID_CIDADES = "id_cidades"
    }
}