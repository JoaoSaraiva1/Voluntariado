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
import com.example.voluntariado.databinding.FragmentListaTarefasBinding
import com.example.voluntariado.databinding.FragmentListaVoluntariosBinding


class ListaTarefasFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor>   {

    private var _binding: FragmentListaTarefasBinding? = null
    private var adapterTarefas : AdapterTarefas? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DadosApp.fragment = this
        (activity as MainActivity).menuAtual = R.menu.menu_lista_tarefas

        _binding = FragmentListaTarefasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val RecyclerViewTarefas = view.findViewById<RecyclerView>(R.id.RecyclerViewTarefas)
        adapterTarefas = AdapterTarefas(this)
        RecyclerViewTarefas.adapter = adapterTarefas
        RecyclerViewTarefas.layoutManager = LinearLayoutManager(requireContext())

        LoaderManager.getInstance(this)
            .initLoader(ID_LOADER_MANAGER_TAREFAS, null, this)

    }

    fun navegaNovoTarefa() {
        //findNavController().navigate(R.id.action_listaVoluntariosFragment_to_novoVoluntarioFragment)
    }
    fun navegaAlterarTarefa() {
        //findNavController().navigate(R.id.action_listaVoluntariosFragment_to_editaVoluntarioFragment)
    }

    fun navegaEliminarTarefa() {
        //findNavController().navigate(R.id.action_listaVoluntariosFragment_to_eliminaVoluntarioFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_novo_tarefa -> navegaNovoTarefa()
            R.id.action_alterar_tarefa -> navegaAlterarTarefa()
            R.id.action_eliminar_tarefa -> navegaEliminarTarefa()
            else -> return false
        }

        return true
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            ContentProviderVoluntariado.ENDERECO_TAREFAS,
            TabelaTarefas.TODAS_COLUNAS,
            null, null,
            TabelaTarefas.CAMPO_NOME
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterTarefas!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterTarefas!!.cursor = null
    }

    companion object {
        const val ID_LOADER_MANAGER_TAREFAS = 0
    }
}