package com.example.covid19

import android.content.ContentValues
import android.database.Cursor
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
    fun insert(values: ContentValues): Long {
        return db.insert(TabelaCidades.NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(TabelaCidades.NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(TabelaCidades.NOME_TABELA, whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>,
        selection: String,
        selectionArgs: Array<String>,
        groupBy: String,
        having: String,
        orderBy: String
    ): Cursor? {
        return db.query(TabelaCidades.NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
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