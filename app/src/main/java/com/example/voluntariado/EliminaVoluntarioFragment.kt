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
import androidx.navigation.fragment.findNavController

class EliminaVoluntarioFragment : Fragment() {

    private lateinit var id_nome_voluntario: EditText
    private lateinit var id_telefone: EditText
    private lateinit var id_data_nascimento: EditText
    private lateinit var id_genero: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_elimina_voluntario

        return inflater.inflate(R.layout.fragment_elimina_voluntario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_nome_voluntario = view.findViewById(R.id.id_nome_voluntario)
        id_telefone = view.findViewById(R.id.id_telefone)
        id_data_nascimento = view.findViewById(R.id.id_data_nascimento)
        id_genero = view.findViewById(R.id.id_genero)

        val voluntario = DadosApp.voluntarioSelecionado!!
        id_nome_voluntario.setText(DadosApp.voluntarioSelecionado!!.nome)
        id_telefone.setText(DadosApp.voluntarioSelecionado!!.telefone)
        id_data_nascimento.setText(DadosApp.voluntarioSelecionado!!.dataNascimento.toString())
        id_genero.setText(DadosApp.voluntarioSelecionado!!.genero)

    }

    fun navegaListaVoluntarios() {
        findNavController().navigate(R.id.action_eliminaVoluntarioFragment_to_listaVoluntariosFragment)
    }

    fun elimina() {
        val uriVoluntario = Uri.withAppendedPath(
            ContentProviderVoluntariado.ENDERECO_VOLUNTARIOS,
            DadosApp.voluntarioSelecionado!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriVoluntario,
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
        navegaListaVoluntarios()
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_voluntario -> elimina()
            R.id.action_cancelar_eliminar_voluntario -> navegaListaVoluntarios()
            else -> return false
        }

        return true
    }
}