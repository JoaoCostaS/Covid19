package com.example.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup


class NovoCasoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment= this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_caso
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novo_caso, container, false)
    }
    fun navegaListaCasos(){

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