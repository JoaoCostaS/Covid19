package com.example.covid19

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var menu: Menu

    var menuAtual = R.menu.menu_menu
        set(value) {
            field = value
            invalidateOptionsMenu()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        DadosApp.activity = this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(menuAtual, menu)
        this.menu = menu
        if (menuAtual == R.menu.menu_lista_cidades) {
            atualizaMenuListaCidades(false)
        }
        if (menuAtual == R.menu.menu_lista_casos) {
            atualizaMenuListaCasos(false)
        }
        if (menuAtual == R.menu.menu_lista_foco_contagio) {
            atualizaMenuListaFocoContagio(false)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a pare nt activity in AndroidManifest.xml.
        val opcaoProcessada = when (item.itemId) {
            //return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, R.string.versao, Toast.LENGTH_LONG).show()
                true
            }
            else -> when(menuAtual) {
                R.menu.menu_lista_cidades -> (DadosApp.fragment as ListaCidadesFragment).processedOpcaoMenu(item)
                R.menu.menu_nova_cidade -> (DadosApp.fragment as NovaCidadeFragment).processedOpcaoMenu(item)
                R.menu.menu_edita_cidade -> (DadosApp.fragment as EditaCidadeFragment).processedOpcaoMenu(item)
                R.menu.menu_elimina_cidade -> (DadosApp.fragment as EliminaCidadeFragment).processedOpcaoMenu(item)
                R.menu.menu_lista_casos -> (DadosApp.fragment as Fragment_lista_casos).processedOpcaoMenu(item)
                R.menu.menu_novo_caso -> (DadosApp.fragment as NovoCasoFragment).processedOpcaoMenu(item)
                R.menu.menu_edita_caso-> (DadosApp.fragment as EditaCasoFragment).processedOpcaoMenu(item)
                R.menu.menu_elimina_caso -> (DadosApp.fragment as EliminaCasoFragment).processedOpcaoMenu(item)
                R.menu.menu_lista_foco_contagio -> (DadosApp.fragment as Fragment_lista_foco_contagio).processedOpcaoMenu(item)
                R.menu.menu_novo_foco_contagio-> (DadosApp.fragment as NovoFocoContagioFragment).processedOpcaoMenu(item)
                R.menu.menu_edita_foco_contagio-> (DadosApp.fragment as EditaFocoContagioFragment).processedOpcaoMenu(item)
                R.menu.menu_elimina_foco_contagio-> (DadosApp.fragment as EliminaFocoContagioFragment).processedOpcaoMenu(item)
                else -> false
            }
        }
        return if(opcaoProcessada) true else super.onOptionsItemSelected(item)

    }
    fun atualizaMenuListaCidades(mostraBotoesAlterarEliminar : Boolean){
        menu.findItem(R.id.action_alterar_cidade).setVisible(mostraBotoesAlterarEliminar)
        menu.findItem(R.id.action_eliminar_cidade).setVisible(mostraBotoesAlterarEliminar)
    }
    fun atualizaMenuListaCasos(mostraBotoesAlterarEliminar : Boolean){
        menu.findItem(R.id.action_alterar_caso).setVisible(mostraBotoesAlterarEliminar)
        menu.findItem(R.id.action_eliminar_caso).setVisible(mostraBotoesAlterarEliminar)
    }
    fun atualizaMenuListaFocoContagio(mostraBotoesAlterarEliminar : Boolean){
        menu.findItem(R.id.action_alterar_foco_contagio).setVisible(mostraBotoesAlterarEliminar)
        menu.findItem(R.id.action_eliminar_foco_contagio).setVisible(mostraBotoesAlterarEliminar)
    }
}