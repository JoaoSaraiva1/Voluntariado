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
import com.example.voluntariado.databinding.FragmentNovoVoluntarioBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


class NovoVoluntarioFragment : Fragment() {

    private var _binding: FragmentNovoVoluntarioBinding? = null

    private lateinit var id_nome_voluntario: EditText
    private lateinit var id_data_nascimento: EditText
    private lateinit var id_telefone: EditText
    private lateinit var id_genero: EditText

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_novo_voluntario

        _binding = FragmentNovoVoluntarioBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_nome_voluntario = view.findViewById(R.id.id_nome_voluntario)
        id_telefone = view.findViewById(R.id.id_telefone)
        id_data_nascimento = view.findViewById(R.id.id_data_nascimento)
        id_genero = view.findViewById(R.id.id_genero)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navegaListaVoluntarios() {
        findNavController().navigate(R.id.action_novoVoluntarioFragment_to_listaVoluntariosFragment)
    }

    fun guardar() {
        val nome_voluntario = id_nome_voluntario.text.toString()
        if (nome_voluntario.isEmpty()) {
            id_nome_voluntario.setError(getString(R.string.preencha_nome_voluntario))
            return
        }

        val data_nascimento  =  id_data_nascimento.text.toString()
        val simpleDateFormat  = SimpleDateFormat("dd/MM/yyyy")
        val date = simpleDateFormat.parse(data_nascimento)
        if (data_nascimento.isEmpty()) {
            id_data_nascimento.setError(getString(R.string.preencha_data_nascimento))
            id_data_nascimento.requestFocus()
            return
        }


        val telefone = id_telefone.text.toString()
        if (telefone.isEmpty()) {
            id_telefone.setError(getString(R.string.preencha_telefone))
            id_telefone.requestFocus()
            return
        }

        val genero = id_genero.text.toString()
        if (genero.isEmpty()) {
            id_genero.setError(getString(R.string.preencha_genero))
            id_genero.requestFocus()
            return
        }

        val voluntario = Voluntario(
            nome = nome_voluntario,
            telefone = telefone,
            dataNascimento = date,
            genero = genero
        )

        val uri = activity?.contentResolver?.insert(
            ContentProviderVoluntariado.ENDERECO_VOLUNTARIOS,
            voluntario.toContentValues()
        )

        if (uri == null) {
            Snackbar.make(
                id_nome_voluntario,
                getString(R.string.erro_inserir_voluntario),
                Snackbar.LENGTH_LONG
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
            R.id.action_guardar_novo_voluntario -> guardar()
            R.id.action_cancelar_novo_voluntario -> navegaListaVoluntarios()
            else -> return false
        }

        return true
    }

}