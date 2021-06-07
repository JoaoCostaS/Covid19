package com.example.covid19

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Cidade (var id: Long = -1, var nome: String) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(TabelaCidades.CAMPO_NOME, nome)

        return valores
    }
    companion object{
        fun fromCursor(cursor: Cursor):Cidade{
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colNome = cursor.getColumnIndex(TabelaCidades.CAMPO_NOME)

            val id = cursor.getLong(colId)
            val nome = cursor.getString(colNome)

            return Cidade(id, nome )
        }
    }
}