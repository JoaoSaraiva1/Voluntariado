package com.example.voluntariado

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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