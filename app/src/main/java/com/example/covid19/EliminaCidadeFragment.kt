package com.example.covid19

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController


class EliminaCidadeFragment : Fragment() {
    private lateinit var textViewCidade: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment= this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_cidade
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elimina_cidade, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewCidade = view.findViewById(R.id.textViewCidade)

        textViewCidade.setText(DadosApp.cidadeSelecionado!!.nome)
    }
    fun navegaListaCidades(){
        findNavController().navigate(R.id.action_eliminaCidadeFragment_to_listaCidadesFragment)
    }

    fun elimina(){
        val uriCidade = Uri.withAppendedPath(
            ContentProviderCovid.ENDERECO_CIDADES,
            DadosApp.cidadeSelecionado!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriCidade,
            null,
            null
        )

        if(registos != 1) {
            Toast.makeText(
                requireContext(),
                R.string.erro_eliminar_cidade,
                Toast.LENGTH_LONG
            ).show()
            return
        }
        Toast.makeText(
            requireContext(),
            R.string.cidade_eliminada_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaCidades()
    }
    fun processedOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_cidade-> elimina()
            R.id.action_cancelar_eliminar_cidade -> navegaListaCidades()
            else -> return false
        }
        return true
    }
}