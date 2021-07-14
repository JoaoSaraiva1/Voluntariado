package com.example.voluntariado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.voluntariado.databinding.FragmentNovoInstituicaoBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NovoInstituicaoFragment : Fragment() {

    private var _binding: FragmentNovoInstituicaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.novoInstituicaoFragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_nova_instituicao

        _binding = FragmentNovoInstituicaoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}