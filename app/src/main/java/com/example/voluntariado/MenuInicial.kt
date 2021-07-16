package com.example.voluntariado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.voluntariado.databinding.FragmentListaInstitucoesBinding
import com.example.voluntariado.databinding.FragmentMenuInicialBinding


class MenuInicial : Fragment() {

    private var _binding: FragmentMenuInicialBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_inicial

        _binding = FragmentMenuInicialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonVoluntarios.setOnClickListener {
            //findNavController().navigate(R.id.action_FragmentInicio_to_FragmentPaciente)
        }
        binding.buttonTarefas.setOnClickListener {
            //findNavController().navigate(R.id.action_FragmentInicio_to_FragmentLocal)
        }
        binding.buttonInstituicoes.setOnClickListener {
            findNavController().navigate(R.id.action_menuInicial_to_Lista_Instituicoes_Fragment)
        }
        binding.buttonSair.setOnClickListener {

        }

    }
    companion object {

    }
}