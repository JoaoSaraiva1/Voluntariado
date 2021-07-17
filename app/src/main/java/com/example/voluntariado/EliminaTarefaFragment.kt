package com.example.voluntariado

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class EliminaTarefaFragment : Fragment() {

    private lateinit var id_nome_tarefa: EditText
    private lateinit var id_estado: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_tarefa

        return inflater.inflate(R.layout.fragment_elimina_tarefa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_nome_tarefa = view.findViewById(R.id.id_nome_tarefa)
        id_estado = view.findViewById(R.id.id_estado)

        val tarefa = DadosApp.tarefaSelecionada!!
        id_nome_tarefa.setText(DadosApp.tarefaSelecionada!!.nome)
        id_estado.setText(DadosApp.tarefaSelecionada!!.estado)

    }

    fun navegaListaTarefas() {
        findNavController().navigate(R.id.action_eliminaTarefaFragment_to_listaTarefasFragment)
    }

    fun elimina() {
        val uriTarefa = Uri.withAppendedPath(
            ContentProviderVoluntariado.ENDERECO_TAREFAS,
            DadosApp.tarefaSelecionada!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriTarefa,
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                R.string.erro_eliminar,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            R.string.eliminado_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaTarefas()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_tarefa -> elimina()
            R.id.action_cancelar_eliminar_tarefa -> navegaListaTarefas()
            else -> return false
        }

        return true
    }
}