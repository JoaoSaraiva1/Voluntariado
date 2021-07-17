package com.example.voluntariado

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.voluntariado.databinding.FragmentNovoTarefaBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat


class NovoTarefaFragment : Fragment() {

    private var _binding: FragmentNovoTarefaBinding? = null

    private lateinit var id_nome_tarefa: EditText
    private lateinit var id_estado: EditText

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_tarefa

        _binding = FragmentNovoTarefaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_nome_tarefa = view.findViewById(R.id.id_nome_tarefa)
        id_estado = view.findViewById(R.id.id_estado)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaTarefas() {
        findNavController().navigate(R.id.action_novoTarefaFragment_to_listaTarefasFragment)
    }

    fun guardar() {
        val nome_tarefa = id_nome_tarefa.text.toString()
        if (nome_tarefa.isEmpty()) {
            id_nome_tarefa.setError(getString(R.string.preencha_nome))
            return
        }

        val estado = id_estado.text.toString()
        if (estado.isEmpty()) {
            id_estado.setError(getString(R.string.preencha_estado))
            id_estado.requestFocus()
            return
        }

        val tarefa = Tarefa(
            nome = nome_tarefa,
            estado = estado,
        )

        val uri = activity?.contentResolver?.insert(
            ContentProviderVoluntariado.ENDERECO_TAREFAS,
            tarefa.toContentValues()
        )

        if (uri == null) {
            Snackbar.make(
                id_nome_tarefa,
                getString(R.string.erro_inserir_tarefa),
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            R.string.tarefa_guardada_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaTarefas()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_novo_tarefa -> guardar()
            R.id.action_cancelar_novo_tarefa -> navegaListaTarefas()
            else -> return false
        }

        return true
    }
}