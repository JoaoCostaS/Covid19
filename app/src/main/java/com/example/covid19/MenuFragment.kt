package com.example.covid19

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MenuFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_cidade).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        view.findViewById<Button>(R.id.button_casos).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_fragment_lista_casos)
        }

        view.findViewById<Button>(R.id.button_foco_contagio).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_fragment_lista_foco_contagio)
        }
    }
}