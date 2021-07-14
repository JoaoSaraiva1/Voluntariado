package com.example.voluntariado

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import kotlin.collections.ArrayList

data class Instituicao(var id: Long = -1, var nome: String, var telefone: Long = 0, var morada: String, var idTarefa: Long, var nomeTarefas: String? = null) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(TabelaInstituicoes.CAMPO_NOME, nome)
        valores.put(TabelaInstituicoes.CAMPO_TELEFONE, telefone)
        valores.put(TabelaInstituicoes.CAMPO_MORADA, morada)
        valores.put(TabelaInstituicoes.CAMPO_ID_TAREFAS, idTarefa)
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Instituicao {
            val volId = cursor.getColumnIndex(BaseColumns._ID)
            val volNome = cursor.getColumnIndex(TabelaInstituicoes.CAMPO_NOME)
            val volTelefone = cursor.getColumnIndex(TabelaInstituicoes.CAMPO_TELEFONE)
            val volMorada = cursor.getColumnIndex(TabelaInstituicoes.CAMPO_MORADA)
            val volIdTarefa= cursor.getColumnIndex(TabelaInstituicoes.CAMPO_ID_TAREFAS)
            val colNomeTarefas = cursor.getColumnIndex(TabelaInstituicoes.CAMPO_EXTERNO_NOME_TAREFA)

            val id = cursor.getLong(volId)
            val nome = cursor.getString(volNome)
            val telefone = cursor.getLong(volTelefone)
            val morada = cursor.getString(volMorada)
            val idTarefa = cursor.getLong(volIdTarefa)
            val nomeTarefas = if (colNomeTarefas != -1) cursor.getString(colNomeTarefas) else null

            return Instituicao(id, nome, telefone, morada , idTarefa, nomeTarefas)
        }
    }
}
