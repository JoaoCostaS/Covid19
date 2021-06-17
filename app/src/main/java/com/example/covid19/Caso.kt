package com.example.covid19

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class Caso (var id: Long = -1, var infetados: Int, var ativos: Int, var obitos: Int, var data: Date, var id_cidades: Long) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaCasos.CAMPO_INFETADOS, infetados.toInt())
            put(TabelaCasos.CAMPO_ATIVOS, ativos.toInt())
            put(TabelaCasos.CAMPO_OBITOS, obitos.toInt())
            put(TabelaCasos.CAMPO_DATA, data.time)
            put(TabelaCasos.CAMPO_ID_CIDADES, id_cidades.toLong())
        }
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Caso {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colInfetados = cursor.getColumnIndex(TabelaCasos.CAMPO_INFETADOS)
            val colAtivos = cursor.getColumnIndex(TabelaCasos.CAMPO_ATIVOS)
            val colObitos = cursor.getColumnIndex(TabelaCasos.CAMPO_OBITOS)
            val colData = cursor.getColumnIndex(TabelaCasos.CAMPO_DATA)
            val colIdCidade = cursor.getColumnIndex(TabelaCasos.CAMPO_ID_CIDADES)

            val id = cursor.getLong(colId)
            val infetados = cursor.getInt(colInfetados)
            val ativos = cursor.getInt(colAtivos)
            val obitos = cursor.getInt(colObitos)
            val data_vacina = Date(cursor.getLong(colData))
            val id_cidades = cursor.getLong(colIdCidade)

            return Caso(id, infetados, ativos, obitos, data_vacina, id_cidades)
        }
    }
}