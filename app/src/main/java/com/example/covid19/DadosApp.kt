package com.example.covid19

class DadosApp {
    companion object{
        lateinit var activity: MainActivity
        var listaCidadesFragment: ListaCidadesFragment? = null
        var novaCidadeFragment: NovaCidadeFragment? = null

        var cidadeSelecionado : Cidade? = null
    }
}