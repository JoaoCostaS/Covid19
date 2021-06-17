package com.example.covid19

import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TestesBaseDados {
    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun getBdCovidOpenHelper() = BdCovidOpenHelper(getAppContext())
    private fun insereCidade(tabela: TabelaCidades, cidade: Cidade): Long {
        val id = tabela.insert(cidade.toContentValues())
        assertNotEquals(-1, id)

        return id
    }
    private fun insereCaso(tabela: TabelaCasos, caso: Caso): Long {
        val id = tabela.insert(caso.toContentValues())
        assertNotEquals(-1, id)

        return id
    }
    private fun getCidadeBaseDados(tabela: TabelaCidades,id: Long): Cidade {
        val cursor = tabela.query(
                TabelaCidades.TODAS_COLUNAS,
                "${BaseColumns._ID}=?",
                arrayOf(id.toString()),
                null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Cidade.fromCursor(cursor)
    }
    private fun getCasoBaseDados(tabela: TabelaCasos,id: Long): Caso {
        val cursor = tabela.query(
                TabelaCasos.TODAS_COLUNAS,
                "${BaseColumns._ID}=?",
                arrayOf(id.toString()),
                null, null, null
        )

        assertNotNull(cursor)
        assert(cursor!!.moveToNext())

        return Caso.fromCursor(cursor)
    }
    private fun Data(ano: Int, mes: Int, dia: Int) = Date(ano -1900, mes -1, dia)


    @Before
    fun apagaBaseDados() {
        getAppContext().deleteDatabase(BdCovidOpenHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val db = getBdCovidOpenHelper().readableDatabase
        assert(db.isOpen)
        db.close()
    }

    @Test
    fun consegueInserirCidades() {
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaCidades = TabelaCidades(db)

        val cidade = Cidade(nome = "Lisboa")
        cidade.id = insereCidade(tabelaCidades, cidade)

        assertEquals(cidade, getCidadeBaseDados(tabelaCidades, cidade.id))

        db.close()
    }
    @Test
    fun consegueAlterarCidades() {
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaCidades = TabelaCidades(db)

        val cidade = Cidade(nome = "Guarda")
        cidade.id = insereCidade(tabelaCidades, cidade)

        cidade.nome = "Guarda"

        val registosAlterados = tabelaCidades.update(
            cidade.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(cidade.id.toString())
        )

        assertEquals(1, registosAlterados)
        assertEquals(cidade, getCidadeBaseDados(tabelaCidades, cidade.id))

        db.close()
    }
    @Test
    fun consegueEliminarCidades(){
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaCidades = TabelaCidades(db)

        val cidade = Cidade(nome = "Aveiro")
        cidade.id = insereCidade(tabelaCidades, cidade)

        val registosEliminados = tabelaCidades.delete(
            "${BaseColumns._ID}=?",
            arrayOf(cidade.id.toString())
        )
        assertEquals(1, registosEliminados)
       

        db.close()
    }
    @Test
    fun consegueLerCidades(){
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaCidades = TabelaCidades(db)

        val cidade = Cidade(nome = "Braga")
        cidade.id = insereCidade(tabelaCidades, cidade)

        assertEquals(cidade, getCidadeBaseDados(tabelaCidades, cidade.id))

        db.close()
    }
    @Test
    fun consegueInserirCasos() {
        val db = getBdCovidOpenHelper().writableDatabase

        val tabelaCidades = TabelaCidades(db)
        val cidade = Cidade(nome = "Faro")
        cidade.id = insereCidade(tabelaCidades, cidade)

        val tabelaCasos = TabelaCasos(db)
        val caso = Caso(infetados = 3415, ativos = 60, obitos = 53, data = Data(2020, 5, 20), id_cidades = cidade.id)
        caso.id = insereCaso(tabelaCasos, caso)

        assertEquals(caso, getCasoBaseDados(tabelaCasos, caso.id))

        db.close()
    }
    @Test
    fun consegueAlterarCasos() {
        val db = getBdCovidOpenHelper().writableDatabase

        val tabelaCidades = TabelaCidades(db)

        val cidadeCovilha = Cidade(nome = "Covilha")
        cidadeCovilha.id = insereCidade(tabelaCidades, cidadeCovilha)

        val cidadePorto = Cidade(nome = "Porto")
        cidadePorto.id = insereCidade(tabelaCidades, cidadePorto)

        val tabelaCasos = TabelaCasos(db)
        val caso = Caso(infetados = 5000, ativos = 100, obitos = 10, data = Data(2020, 5, 20), id_cidades = cidadeCovilha.id)
        caso.id = insereCaso(tabelaCasos, caso)


        caso.infetados = 5500
        caso.ativos = 150
        caso.obitos =15
        caso.id_cidades = cidadePorto.id

        val registosAlterados = tabelaCasos.update(
                caso.toContentValues(),
                "${BaseColumns._ID}=?",
                arrayOf(caso.id.toString())
        )

        assertEquals(1, registosAlterados)
        assertEquals(caso, getCasoBaseDados(tabelaCasos, caso.id))

        db.close()
    }
    @Test
    fun consegueEliminarCasos(){
        val db = getBdCovidOpenHelper().writableDatabase
        val tabelaCidades = TabelaCidades(db)

        val cidade = Cidade(nome = "Peniche")
        cidade.id = insereCidade(tabelaCidades, cidade)

        val tabelaCasos = TabelaCasos(db)
        val caso = Caso(infetados = 10, ativos = 1500, obitos = 0, data = Data(1950, 10, 30), id_cidades = cidade.id)
        caso.id = insereCaso(tabelaCasos, caso)

        val registosEliminados = tabelaCasos.delete(
                "${BaseColumns._ID}=?",
                arrayOf(caso.id.toString())
        )
        assertEquals(1, registosEliminados)


        db.close()
    }

}

