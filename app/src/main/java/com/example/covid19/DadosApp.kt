package com.example.covid19

class DadosApp {
    companion object{
        lateinit var activity: MainActivity
        lateinit var listaCidadesFragment: ListaCidadesFragment
        var cidadeSelecionado : Cidade? = null
    }
}