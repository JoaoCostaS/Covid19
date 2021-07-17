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
import androidx.loader.app.LoaderManager
import androidx.navigation.fragment.findNavController


class EliminaFocoContagioFragment : Fragment() {
    private lateinit var textViewCidadeFoco: TextView
    private lateinit var textViewLocalF: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment= this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_foco_contagio
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elimina_foco_contagio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //editTextCidadeF = view.findViewById(R.id.editTextCidade)
        textViewCidadeFoco = view.findViewById(R.id.textViewCidadeFoco)
        textViewLocalF = view.findViewById(R.id.textViewLocalF)

        val focoContagio = DadosApp.focoContagioSelecionado!!

        textViewCidadeFoco.setText(focoContagio.nomeCidade)
        textViewLocalF.setText(focoContagio.local)
    }

    fun navegaListaFocoContagio(){
        findNavController().navigate(R.id.action_eliminaFocoContagioFragment_to_fragment_lista_foco_contagio)
    }

    fun elimina(){
        val uriFocoContagio = Uri.withAppendedPath(
            ContentProviderCovid.ENDERECO_FOCO_CONTAGIO,
            DadosApp.focoContagioSelecionado!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriFocoContagio,
            null,
            null
        )

        if (registos != 1){
            Toast.makeText(
                requireContext(),
                R.string.erro_eliminar_foco_contagio,
                Toast.LENGTH_LONG
            ).show()
            return
        }
        Toast.makeText(
            requireContext(),
            R.string.foco_contagio_eliminado_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaFocoContagio()
    }

    fun processedOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_foco -> elimina()
            R.id.action_cancelar_eliminar_foco  -> navegaListaFocoContagio()
            else -> return false
        }
        return true
    }

}