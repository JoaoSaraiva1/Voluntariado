package com.example.voluntariado

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.util.*

data class Voluntario(var id: Long = -1, var nome: String, var dataNascimento: Date, var telefone: String, var genero: String) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()
        valores.put(TabelaVoluntarios.CAMPO_NOME, nome)
        valores.put(TabelaVoluntarios.CAMPO_DATA_NASCIMENTO, dataNascimento.time)
        valores.put(TabelaVoluntarios.CAMPO_TELEFONE, telefone)
        valores.put(TabelaVoluntarios.CAMPO_GENERO, genero)
        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Voluntario {
            val volId = cursor.getColumnIndex(BaseColumns._ID)
            val volNome = cursor.getColumnIndex(TabelaVoluntarios.CAMPO_NOME)
            val volDataNascimento = cursor.getColumnIndex(TabelaVoluntarios.CAMPO_DATA_NASCIMENTO)
            val volTelefone = cursor.getColumnIndex(TabelaVoluntarios.CAMPO_TELEFONE)
            val volGenero = cursor.getColumnIndex(TabelaVoluntarios.CAMPO_GENERO)

            val id = cursor.getLong(volId)
            val nome = cursor.getString(volNome)
            val dataNascimento = Date(cursor.getLong(volDataNascimento))
            val telefone = cursor.getString(volTelefone)
            val genero = cursor.getString(volGenero)

            return Voluntario(id, nome, dataNascimento, telefone, genero)
        }
    }
}

