package com.example.covid19

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class Vacina(var id: Long = -1, var vacinados: Int, var naovacinados: Int, var data_vacina: Date, var id_cidades: Long) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues().apply {
            put(TabelaVacinacao.CAMPO_VACINADOS, vacinados.toInt())
            put(TabelaVacinacao.CAMPO_NAOVACINADOS, naovacinados.toInt())
            put(TabelaVacinacao.CAMPO_DATA_VACINA, data_vacina.time)
            put(TabelaVacinacao.CAMPO_ID_CIDADES, id_cidades.toLong())
        }
        return valores
    }
    companion object {
        fun fromCursor(cursor: Cursor): Vacina {
            val colId = cursor.getColumnIndex(BaseColumns._ID)
            val colVacinados = cursor.getColumnIndex(TabelaVacinacao.CAMPO_VACINADOS)
            val colNaoVacinados = cursor.getColumnIndex(TabelaVacinacao.CAMPO_NAOVACINADOS)
            val colData_Vacina = cursor.getColumnIndex(TabelaVacinacao.CAMPO_DATA_VACINA)
            val colIdCidade = cursor.getColumnIndex(TabelaVacinacao.CAMPO_ID_CIDADES)

            val id = cursor.getLong(colId)
            val vacinados = cursor.getInt(colVacinados)
            val naovacinados = cursor.getInt(colNaoVacinados)
            val data_vacina = Date(cursor.getLong(colData_Vacina))
            val id_cidades = cursor.getLong(colIdCidade)

            return Vacina(id, vacinados, naovacinados, data_vacina, id_cidades)
        }
    }
}