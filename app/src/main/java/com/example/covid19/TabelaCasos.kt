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
        val ultimaColuna = columns.size -1

        var posCampoNomeCidade = -1 //-1 indica que não foi pedido
        for (i in 0..ultimaColuna){
            if (columns[i] == CAMPO_EXTERNO_NOME_CIDADE){
                posCampoNomeCidade = i
                break
            }
        }

        if (posCampoNomeCidade == -1) { // não existem campos externos de outra tabela
            return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
        }

        var colunas = ""
        for(i in 0..ultimaColuna){
            var nomeColuna = if(i == posCampoNomeCidade){
                "${TabelaCidades.NOME_TABELA}.${TabelaCidades.CAMPO_NOME} AS $CAMPO_EXTERNO_NOME_CIDADE"
            }else {
                "$NOME_TABELA.${columns[i]}"
            }

            if (i > 0) colunas += ","
            colunas += nomeColuna
        }
        val tabelas = "$NOME_TABELA INNER JOIN ${TabelaCidades.NOME_TABELA} ON ${TabelaCidades.NOME_TABELA}.${BaseColumns._ID} = $NOME_TABELA.$CAMPO_ID_CIDADES"

        var sqlAdicional = ""

        if (selection != null) sqlAdicional += " WHERE $selection"

        if (groupBy != null) {
            sqlAdicional += " GROUP BY $groupBy"
            if (having != null) sqlAdicional += " HAVING $having"
        }
        if (orderBy != null){
            sqlAdicional += " ORDER BY $orderBy"
        }

        val sql = "SELECT $colunas FROM $tabelas$sqlAdicional"
        return db.rawQuery(sql, selectionArgs)
    }

    companion object{
        const val NOME_TABELA = "casos"
        const val CAMPO_INFETADOS = "infetados"
        const val CAMPO_ATIVOS = "ativos"
        const val CAMPO_OBITOS = "obitos"
        const val CAMPO_DATA = "data"
        const val CAMPO_ID_CIDADES = "id_cidades"
        const val CAMPO_EXTERNO_NOME_CIDADE = "nome_cidade" //${TabelaCidades.NOME_TABELA}. ${TabelaCidades.CAMPO_NOME} AS


        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_INFETADOS, CAMPO_ATIVOS, CAMPO_OBITOS, CAMPO_DATA, CAMPO_ID_CIDADES, CAMPO_EXTERNO_NOME_CIDADE)
    }
}