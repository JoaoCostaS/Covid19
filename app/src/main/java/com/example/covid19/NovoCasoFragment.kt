package com.example.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class NovoCasoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).menuAtual = R.menu.menu_lista_casos
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novo_caso, container, false)
    }
}