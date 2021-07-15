package com.example.voluntariado

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.voluntariado.databinding.FragmentListaInstitucoesBinding


class Lista_Institucoes_Fragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaInstitucoesBinding? = null
    private var adapterInstituicoes : AdapterInstituicoes? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.listaInstitucoesFragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_lista_instituicoes

        _binding = FragmentListaInstitucoesBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val RecyclerViewInstituicoes = view.findViewById<RecyclerView>(R.id.RecyclerViewInstituicoes)
        adapterInstituicoes = AdapterInstituicoes(this)
        RecyclerViewInstituicoes.adapter = adapterInstituicoes
        RecyclerViewInstituicoes.layoutManager = LinearLayoutManager(requireContext())

        LoaderManager.getInstance(this)
            .initLoader(ID_LOADER_MANAGER_INSTITUICOES, null, this)

//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    fun navegaNovoInstituicao() {
        findNavController().navigate(R.id.action_Lista_Instituicoes_Fragment_to_Novo_Instituicao_Fragment)
    }
    fun navegaAlterarInstituicao() {
        //todo: navegar para o fragmento da edição de uma instituicao
    }

    fun navegaEliminarInstituicao() {
        //todo: navegar para o fragmento para confirmar eliminação de uma instituicao
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_novo_instituicao -> navegaNovoInstituicao()
            R.id.action_alterar_instituicao -> navegaAlterarInstituicao()
            R.id.action_eliminar_instituicao -> navegaEliminarInstituicao()
            else -> return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            ContentProviderInstituicoes.ENDERECO_INSTITUICOES,
            TabelaInstituicoes.TODAS_COLUNAS,
            null, null,
            TabelaInstituicoes.NOME_TABELA+ "." + TabelaInstituicoes.CAMPO_NOME
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterInstituicoes!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterInstituicoes!!.cursor = null
    }

    companion object {
        const val ID_LOADER_MANAGER_INSTITUICOES = 0
    }
}