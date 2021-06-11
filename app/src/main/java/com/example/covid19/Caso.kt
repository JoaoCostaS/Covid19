package com.example.covid19

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Caso (var id: Long, var infetados: Int, var ativos: Int, var obitos: Int, /*var data: Int*/var id_cidades: Long) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaCasos.CAMPO_INFETADOS, infetados)
            put(TabelaCasos.CAMPO_ATIVOS, ativos)
            put(TabelaCasos.CAMPO_OBITOS, obitos)
           // put(TabelaCasos.CAMPO_DATA, data)
            put(TabelaCasos.CAMPO_ID_CIDADES, id_cidades)
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Caso {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colInfetados = cursor.getColumnIndex(TabelaCasos.CAMPO_INFETADOS)
            val colAtivos = cursor.getColumnIndex(TabelaCasos.CAMPO_ATIVOS)
            val colObitos = cursor.getColumnIndex(TabelaCasos.CAMPO_OBITOS)
            //val colData = cursor.getColumnIndex(TabelaCasos.CAMPO_DATA)
            val colIdCidade = cursor.getColumnIndex(TabelaCasos.CAMPO_ID_CIDADES)

            val id = cursor.getLong(colId)
            val infetados = cursor.getInt(colInfetados)
            val ativos = cursor.getInt(colAtivos)
            val obitos = cursor.getInt(colObitos)
           // val data = cursor.getColumnIndex(colData)
            val id_cidades = cursor.getLong(colIdCidade)

            return Caso(id, infetados, ativos, obitos, id_cidades)
        }
    }
}