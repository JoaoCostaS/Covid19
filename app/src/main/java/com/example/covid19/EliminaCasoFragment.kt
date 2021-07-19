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


class EliminaCasoFragment : Fragment() {
    private lateinit var textViewCidadeCa: TextView
    private lateinit var textViewInfetadosC: TextView
    private lateinit var textViewAtivosC: TextView
    private lateinit var textViewObitosC: TextView
    private lateinit var textViewDataC: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_caso
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elimina_caso, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewCidadeCa = view.findViewById(R.id.textViewCidadeCa)
        textViewInfetadosC = view.findViewById(R.id.textViewInfetadosC)
        textViewAtivosC = view.findViewById(R.id.textViewAtivosC)
        textViewObitosC = view.findViewById(R.id.textViewObitosC)
        textViewDataC = view.findViewById(R.id.textViewDataC)


         val caso = DadosApp.casoSelecionado!!
         textViewCidadeCa.setText(caso.nomeCidade)
         textViewInfetadosC.setText(caso.infetados.toString())
         textViewAtivosC.setText(caso.ativos.toString())
         textViewObitosC.setText(caso.obitos.toString())
         textViewDataC.setText(caso.data.toString())

    }
    fun navegaListaCasos() {
        findNavController().navigate(R.id.action_eliminaCasoFragment_to_fragment_lista_casos)
    }
    fun elimina(){
        val uriCaso = Uri.withAppendedPath(
            ContentProviderCovid.ENDERECO_CASOS,
            DadosApp.casoSelecionado!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriCaso,
            null,
            null
        )
        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                R.string.erro_eliminar_caso,
                Toast.LENGTH_LONG
            ).show()
            return
        }
        Toast.makeText(
            requireContext(),
            R.string.caso_eliminado_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaCasos()
    }
    fun processedOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_caso -> elimina()
            R.id.action_cancelar_eliminar_caso -> navegaListaCasos()
            else -> return false
        }
        return true
    }

}