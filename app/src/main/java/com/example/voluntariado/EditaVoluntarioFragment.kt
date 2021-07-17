package com.example.voluntariado

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat


class EditaVoluntarioFragment : Fragment() {

    private lateinit var id_nome_voluntario: EditText
    private lateinit var id_telefone: EditText
    private lateinit var id_data_nascimento: EditText
    private lateinit var id_genero: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_edita_voluntario

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edita_voluntario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_nome_voluntario = view.findViewById(R.id.id_nome_voluntario)
        id_telefone = view.findViewById(R.id.id_telefone)
        id_data_nascimento = view.findViewById(R.id.id_data_nascimento)
        id_genero = view.findViewById(R.id.id_genero)

        id_nome_voluntario.setText(DadosApp.voluntarioSelecionado!!.nome)
        id_telefone.setText(DadosApp.voluntarioSelecionado!!.telefone)
        id_data_nascimento.setText(DadosApp.voluntarioSelecionado!!.dataNascimento.toString())
        id_genero.setText(DadosApp.voluntarioSelecionado!!.genero)

    }

    fun navegaListaVoluntarios() {
        findNavController().navigate(R.id.action_editaVoluntarioFragment_to_listaVoluntariosFragment)
    }

    fun guardar() {
        val nome_voluntario = id_nome_voluntario.text.toString()
        if (nome_voluntario.isEmpty()) {
            id_nome_voluntario.setError(getString(R.string.preencha_nome))
            return
        }

        val telefone = id_telefone.text.toString()
        if (telefone.isEmpty()) {
            id_telefone.setError(getString(R.string.preencha_telefone))
            id_telefone.requestFocus()
            return
        }

        val data = id_data_nascimento .text.toString()
        val simpleDateFormat  = SimpleDateFormat("dd/MM/yyyy")
        val date = simpleDateFormat.parse(data)

        val genero = id_genero.text.toString()
        if (genero.isEmpty()) {
            id_genero.setError(getString(R.string.preencha_genero))
            id_genero.requestFocus()
            return
        }


        val voluntario = DadosApp.voluntarioSelecionado!!
        voluntario.nome = nome_voluntario
        voluntario.telefone = telefone
        voluntario.dataNascimento = date
        voluntario.genero = genero

        val uriVoluntario = Uri.withAppendedPath(
            ContentProviderVoluntariado.ENDERECO_VOLUNTARIOS,
            voluntario.id.toString()
        )

        val registos = activity?.contentResolver?.update(
            uriVoluntario,
            voluntario.toContentValues(),
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
            R.string.voluntario_guardado_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaVoluntarios()

    }
    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar_edita_voluntario -> guardar()
            R.id.action_cancelar_edita_voluntario -> navegaListaVoluntarios()
            else -> return false
        }

        return true
    }

    companion object {

    }
}