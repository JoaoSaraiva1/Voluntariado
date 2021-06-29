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
import com.example.voluntariado.databinding.FragmentListaInstituicoesBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListaInstituicoesFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentListaInstituicoesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaInstituicoesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this)
             .initLoader(ID_LOADER_MANAGER_INSTITUICOES, null, this)

//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
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
            TabelaInstituicoes.CAMPO_NOME
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        TODO("Not yet implemented")
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }

    companion object {
        const val ID_LOADER_MANAGER_INSTITUICOES = 0
    }
}


