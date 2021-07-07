package com.example.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


class NovaCidadeFragment : Fragment() {

    private lateinit var editTextCidade: EditText


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        DadosApp.fragment= this
        (activity as MainActivity).menuAtual = R.menu.menu_nova_cidade

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nova_cidade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextCidade = view.findViewById(R.id.editTextCidade)
    }

    fun navegaListaCidades(){
        findNavController().navigate(R.id.action_novaCidadeFragment_to_ListaCidadesFragment)
    }

    fun guardar(){
        val cidade = editTextCidade.text.toString()
        if (cidade.isEmpty()){
            editTextCidade.setError(getString(R.string.cidade_obrigatoria))
            editTextCidade.requestFocus()
            return
        }
        val cidades = Cidade(nome = cidade)

        val uri = activity?.contentResolver?.insert(
                ContentProviderCovid.ENDERECO_CIDADES,
                cidades.toContentValues()
        )
        if(uri == null) {
            Snackbar.make(
                    editTextCidade,
                    getString(R.string.erro_inserir_cidade),
                    Snackbar.LENGTH_LONG
            ).show()
            return
        }
        navegaListaCidades()

    }

    fun processedOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_nova_cidade -> guardar()
            R.id.action_cancelar_nova_cidade-> navegaListaCidades()
            else -> return false
        }
        return true
    }

}