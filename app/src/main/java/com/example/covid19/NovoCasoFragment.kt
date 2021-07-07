package com.example.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.findNavController


class NovoCasoFragment : Fragment() {

    private lateinit var editTextInfetados: EditText
    private lateinit var editTextAtivos: EditText
    private lateinit var editTextObitos: EditText
    private lateinit var editTextData: EditText
    private lateinit var spinnerCidade: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment= this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_caso
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novo_caso, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextInfetados = view.findViewById(R.id.editTextInfetados)
        editTextAtivos = view.findViewById(R.id.editTextAtivos)
        editTextObitos = view.findViewById(R.id.editTextObitos)
        editTextData = view.findViewById(R.id.editTextData)
        spinnerCidade = view.findViewById(R.id.spinnerCidade)

    }

    fun navegaListaCasos(){
        findNavController().navigate(R.id.action_novoCasoFragment_to_fragment_lista_casos)
    }
    fun guardarCaso(){

    }
    fun processedOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_caso -> guardarCaso()
            R.id.action_cancelar_novo_caso -> navegaListaCasos()
            else -> return false
        }
        return true
    }
}