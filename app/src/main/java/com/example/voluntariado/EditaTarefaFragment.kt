package com.example.voluntariado

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.navigation.fragment.findNavController

class EditaTarefaFragment : Fragment() {

    private lateinit var id_nome_tarefa: EditText
    private lateinit var id_estado: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_edita_tarefa

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edita_tarefa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_nome_tarefa = view.findViewById(R.id.id_nome_tarefa)
        id_estado = view.findViewById(R.id.id_estado)

        id_nome_tarefa.setText(DadosApp.tarefaSelecionada!!.nome)
        id_estado.setText(DadosApp.tarefaSelecionada!!.estado)

    }

    fun navegaListaTarefas() {
        findNavController().navigate(R.id.action_editaTarefaFragment_to_listaTarefasFragment)
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

        val tarefa = DadosApp.tarefaSelecionada!!
        tarefa.nome = nome_tarefa
        tarefa.estado = estado

        val uriTarefa = Uri.withAppendedPath(
            ContentProviderVoluntariado.ENDERECO_TAREFAS,
            tarefa.id.toString()
        )

        val registos = activity?.contentResolver?.update(
            uriTarefa,
            tarefa.toContentValues(),
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                R.string.erro_alterar_registo,
                Toast.LENGTH_LONG
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
            R.id.action_guardar_edita_tarefa -> guardar()
            R.id.action_cancelar_edita_tarefa -> navegaListaTarefas()
            else -> return false
        }

        return true
    }
}