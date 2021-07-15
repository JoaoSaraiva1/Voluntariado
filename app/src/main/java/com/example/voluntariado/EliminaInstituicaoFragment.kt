package com.example.voluntariado

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EliminaLivroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminaInstituicaoFragment : Fragment() {

    private lateinit var id_nome_instituicao: EditText
    private lateinit var id_telefone: EditText
    private lateinit var id_morada: EditText
    private lateinit var spinner_tarefas: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_edita_instituicao

        return inflater.inflate(R.layout.fragment_elimina_instituicao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        id_nome_instituicao = view.findViewById(R.id.id_nome_instituicao)
        id_telefone = view.findViewById(R.id.id_telefone)
        id_morada = view.findViewById(R.id.id_morada)
        spinner_tarefas = view.findViewById(R.id.spinner_tarefas)

        id_nome_instituicao.setText(DadosApp.insituicaoSelecionada!!.nome)
        id_telefone.setText(DadosApp.insituicaoSelecionada!!.telefone)
        id_morada.setText(DadosApp.insituicaoSelecionada!!.morada)

    }

    fun navegaListaInstituicoes() {
        findNavController().navigate(R.id.action_Novo_Instituicao_Fragment_to_Lista_Instituicoes_Fragment)
    }
    fun elimina() {
        val uriInstituicao = Uri.withAppendedPath(
            ContentProviderInstituicoes.ENDERECO_INSTITUICOES,
            DadosApp.insituicaoSelecionada!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriInstituicao,
            null,
            null
        )

        if (registos != 1) {
            Toast.makeText(
                requireContext(),
                R.string.erro_eliminar_instituicao,
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            R.string.instituicao_eliminado_sucesso,
            Toast.LENGTH_LONG
        ).show()
        navegaListaInstituicoes()
    }
    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_confirma_eliminar_instituicao -> elimina()
            R.id.action_cancelar_eliminar_instituicao -> navegaListaInstituicoes()
            else -> return false
        }

        return true
    }
}