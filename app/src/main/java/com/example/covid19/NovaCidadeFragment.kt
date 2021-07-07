package com.example.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController


class NovaCidadeFragment : Fragment() {

    private lateinit var editTextCidade: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        DadosApp.novaCidadeFragment = this
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

    }

    fun processedOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_nova_cidade -> guardar()
            R.id.action_cancelar_nova_cidade-> navegaListaCidades()
            else -> return false
        }
        return true
    }

    companion object {

    }
}