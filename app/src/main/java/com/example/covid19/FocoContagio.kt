package com.example.covid19

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class FocoContagio (var id: Long = -1, var local: String, var id_cidades: Long){
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaFocoContagio.CAMPO_LOCAL, local.toString())
            put(TabelaFocoContagio.CAMPO_ID_CIDADES, id_cidades.toLong())
        }
        return valores
    }
    companion object {
        fun fromCursor(cursor: Cursor): FocoContagio {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colLocal = cursor.getColumnIndex(TabelaFocoContagio.CAMPO_LOCAL)
            val colIdCidade = cursor.getColumnIndex(TabelaFocoContagio.CAMPO_ID_CIDADES)

            val id = cursor.getLong(colId)
            val local = cursor.getString(colLocal)
            val id_cidades = cursor.getLong(colIdCidade)

            return FocoContagio(id, local, id_cidades)
        }
    }
}