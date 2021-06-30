package com.example.covid19

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaFocoContagio(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria() {
        db.execSQL(" CREATE TABLE " + NOME_TABELA + "(" +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CAMPO_LOCAL + " TEXT, " +
                CAMPO_ID_CIDADES + " LONG NOT NULL, " +
                " FOREIGN KEY( " + CAMPO_ID_CIDADES + ") " +
                " REFERENCES " + TabelaCidades.NOME_TABELA +
                ")"
        )
    }
    fun insert(values: ContentValues): Long {
        return db.insert(NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(NOME_TABELA, whereClause, whereArgs)
    }

    fun query(
            columns: Array<String>,
            selection: String?,
            selectionArgs: Array<String>?,
            groupBy: String?,
            having: String?,
            orderBy: String?
    ): Cursor? {

        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
    }
    companion object{
        const val NOME_TABELA = "focoContagio"
        const val CAMPO_LOCAL = "local"
        const val CAMPO_ID_CIDADES = "id_cidades"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_LOCAL, CAMPO_ID_CIDADES)
    }



}