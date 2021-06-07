package com.example.covid19

import android.provider.BaseColumns
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

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
        val TabelaCidades = TabelaCidades(db)

        val cidade = Cidade(nome = "Lisboa")
        cidade.id = insereCidade(TabelaCidades, cidade)

        db.close()
    }
    @Test
    fun consegueAlterarCidades() {
        val db = getBdCovidOpenHelper().writableDatabase
        val TabelaCidades = TabelaCidades(db)

        val cidade = Cidade(nome = "Guarda")
        cidade.id = insereCidade(TabelaCidades, cidade)

        cidade.nome = "Guarda"

        val registosAlterados = TabelaCidades.update(
            cidade.toContentValues(),
            "${BaseColumns._ID}=?",
            arrayOf(cidade.id.toString())
        )

        assertEquals(1, registosAlterados)

        db.close()
    }
    @Test
    fun consegueEliminarCidades(){
        val db = getBdCovidOpenHelper().writableDatabase
        val TabelaCidades = TabelaCidades(db)

        val cidade = Cidade(nome = "Aveiro")
        cidade.id = insereCidade(TabelaCidades, cidade)

        val registosEliminados = TabelaCidades.delete(
            "${BaseColumns._ID}=?",
            arrayOf(cidade.id.toString())
        )
        assertEquals(1, registosEliminados)

        db.close()
    }
}

