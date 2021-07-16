package com.example.voluntariado

import androidx.fragment.app.Fragment

class DadosApp {
    companion object {
        lateinit var activity: MainActivity
        lateinit var fragment: Fragment

        var insituicaoSelecionada : Instituicao? = null
        var voluntarioSelecionado : Voluntario? = null
    }
}