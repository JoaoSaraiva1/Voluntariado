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
import com.example.voluntariado.databinding.FragmentListaVoluntariosBinding


class ListaVoluntariosFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor>  {

    private var _binding: FragmentListaVoluntariosBinding? = null
    private var adapterVoluntarios : AdapterVoluntarios? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_lista_voluntarios

        _binding = FragmentListaVoluntariosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val RecyclerViewVoluntarios = view.findViewById<RecyclerView>(R.id.RecyclerViewVoluntarios)
        adapterVoluntarios = AdapterVoluntarios(this)
        RecyclerViewVoluntarios.adapter = adapterVoluntarios
        RecyclerViewVoluntarios.layoutManager = LinearLayoutManager(requireContext())

        LoaderManager.getInstance(this)
            .initLoader(ID_LOADER_MANAGER_VOLUNTARIOS, null, this)

    }

    fun navegaNovoVoluntario() {
        findNavController().navigate(R.id.action_listaVoluntariosFragment_to_novoVoluntarioFragment)
    }
    fun navegaAlterarVoluntario() {
        findNavController().navigate(R.id.action_listaVoluntariosFragment_to_editaVoluntarioFragment)
    }

    fun navegaEliminarVoluntario() {
        //findNavController().navigate(R.id.action_Lista_Instituicoes_Fragment_to_Elimina_Instituicao_Fragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_novo_voluntario -> navegaNovoVoluntario()
            R.id.action_alterar_instituicao -> navegaAlterarVoluntario()
            R.id.action_eliminar_instituicao -> navegaEliminarVoluntario()
            else -> return false
        }

        return true
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            ContentProviderVoluntariado.ENDERECO_VOLUNTARIOS,
            TabelaVoluntarios.TODAS_COLUNAS,
            null, null,
            TabelaVoluntarios.CAMPO_NOME
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterVoluntarios!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterVoluntarios!!.cursor = null
    }

    companion object {
        const val ID_LOADER_MANAGER_VOLUNTARIOS = 0
    }
}