package com.example.covid19

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar


class EditaCidadeFragment : Fragment() {

    private lateinit var editTextCidade: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        DadosApp.fragment= this
        (activity as MainActivity).menuAtual = R.menu.menu_edita_cidade
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edita_cidade, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextCidade = view.findViewById(R.id.editTextCidade)
    }

    fun navegaListaCidades(){
        findNavController().navigate(R.id.action_editaCidadeFragment_to_listaCidadesFragment)
    }

    fun guardar(){
        val cidade = editTextCidade.text.toString()
        if (cidade.isEmpty()){
            editTextCidade.setError(getString(R.string.cidade_obrigatoria))
            editTextCidade.requestFocus()
            return
        }
        val cidades = DadosApp.cidadeSelecionado!!

        cidades.nome = cidade

        val uriCidade = Uri.withAppendedPath(
            ContentProviderCovid.ENDERECO_CIDADES,
            cidades.id.toString()
        )

        val registos = activity?.contentResolver?.update(
            uriCidade,
            cidades.toContentValues(),
            null,
            null
        )

        if(registos != 1) {
            Toast.makeText(
                requireContext(),
                R.string.erro_alterar_cidade,
                Toast.LENGTH_LONG
            ).show()
            return
        }
            Toast.makeText(
                requireContext(),
                R.string.erro_guardado_sucesso,
                Toast.LENGTH_LONG
            ).show()
            return
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