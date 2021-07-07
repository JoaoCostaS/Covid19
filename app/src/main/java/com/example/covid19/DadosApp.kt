package com.example.covid19

import androidx.fragment.app.Fragment

class DadosApp {
    companion object{
        lateinit var activity: MainActivity
        lateinit var fragment: Fragment



        var cidadeSelecionado : Cidade? = null
        var casoSelecionado : Caso? = null
        var focoContagioSelecionado : FocoContagio? = null
    }
}