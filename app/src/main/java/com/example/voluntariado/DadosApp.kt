package com.example.voluntariado

class DadosApp {
    companion object {
        lateinit var activity: MainActivity
        lateinit var listaInstitucoesFragment: Lista_Institucoes_Fragment
        var novoInstituicaoFragment: NovoInstituicaoFragment? = null

        var insituicaoSelecionada : Instituicao? = null
    }
}