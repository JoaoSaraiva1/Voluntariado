package com.example.voluntariado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.example.voluntariado.databinding.FragmentNovoInstituicaoBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NovoInstituicaoFragment : Fragment() {

    private var _binding: FragmentNovoInstituicaoBinding? = null

    private lateinit var id_nome_instituicao: EditText
    private lateinit var id_telefone: EditText
    private lateinit var id_morada: EditText
    private lateinit var spinner_tarefas: Spinner

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

        id_nome_instituicao = view.findViewById(R.id.id_nome_instituicao)
        id_telefone = view.findViewById(R.id.id_telefone)
        id_morada = view.findViewById(R.id.id_morada)
        spinner_tarefas = view.findViewById(R.id.spinner_tarefas)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaInstituicoes() {
        findNavController().navigate(R.id.action_Novo_Instituicao_Fragment_to_Lista_Instituicoes_Fragment)
    }

    fun guardar() {
        // todo: guardar livro
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_instituicao -> guardar()
            R.id.action_cancelar_novo_instituicao -> navegaListaInstituicoes()
            else -> return false
        }

        return true
    }
}