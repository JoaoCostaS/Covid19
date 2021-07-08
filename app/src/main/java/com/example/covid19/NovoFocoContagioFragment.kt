package com.example.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.loader.app.LoaderManager
import androidx.navigation.fragment.findNavController


class NovoFocoContagioFragment : Fragment() {
    private lateinit var editTextCidadeF: EditText
    private lateinit var editTextLocal: EditText
    private lateinit var spinnerCidadesF: Spinner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        (activity as MainActivity).menuAtual = R.menu.menu_novo_foco_contagio
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novo_foco_contagio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextCidadeF = view.findViewById(R.id.editTextCidade)
        editTextLocal = view.findViewById(R.id.editTextLocal)
        spinnerCidadesF = view.findViewById(R.id.spinnerCidadesF)

      //  LoaderManager.getInstance(this)
        //    .initLoader(NovoCasoFragment.ID_LOADER_MANAGER_CIDADES, null, this)

    }

    fun navegaListaFocoContagio(){
        findNavController().navigate(R.id.action_novoFocoContagioFragment_to_fragment_lista_foco_contagio)
    }
    fun guardar(){

    }
    fun processedOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_foco -> guardar()
            R.id.action_cancelar_novo_foco  -> navegaListaFocoContagio()
            else -> return false
        }
        return true
    }
}