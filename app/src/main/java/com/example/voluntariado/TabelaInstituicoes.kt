package com.example.voluntariado

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns


class TabelaInstituicoes(db: SQLiteDatabase) {
    private val db: SQLiteDatabase = db

    fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL, $CAMPO_TELEFONE NUMBER NOT NULL, $CAMPO_MORADA TEXT NOT NULL, $CAMPO_ID_TAREFAS INTEGER NOT NULL, FOREIGN KEY($CAMPO_ID_TAREFAS) REFERENCES ${TabelaTarefas.NOME_TABELA})")
    }

    fun insert(values: ContentValues): Long {
        return db.insert(NOME_TABELA, null, values)
    }

    fun update(values: ContentValues, whereClause: String, whereArgs: Array<String>): Int {
        return db.update(NOME_TABELA, values, whereClause, whereArgs)
    }

    fun delete(whereClause: String, whereArgs: Array<String>): Int {
        return db.delete(NOME_TABELA, whereClause, whereArgs)
    }

    fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor? {

        val ultimaColuna = columns.size - 1

        var posColNomeTarefa = -1 // -1 indica que a coluna não foi pedida
        for (i in 0..ultimaColuna) {
            if (columns[i] == CAMPO_EXTERNO_NOME_TAREFA) {
                posColNomeTarefa = i
                break
            }
        }

        if (posColNomeTarefa == -1) {
            return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy)
        }

        var colunas = ""
        for (i in 0..ultimaColuna) {
            if (i > 0) colunas += ","

            colunas += if (i == posColNomeTarefa) {
                "${TabelaTarefas.NOME_TABELA}.${TabelaTarefas.CAMPO_NOME} AS $CAMPO_EXTERNO_NOME_TAREFA"
            } else {
                "${NOME_TABELA}.${columns[i]}"
            }
        }

        val tabelas =
            "$NOME_TABELA INNER JOIN ${TabelaTarefas.NOME_TABELA} ON ${TabelaTarefas.NOME_TABELA}.${BaseColumns._ID}=$CAMPO_ID_TAREFAS"

        var sql = "SELECT $colunas FROM $tabelas"

        if (selection != null) sql += " WHERE $selection"

        if (groupBy != null) {
            sql += " GROUP BY $groupBy"
            if (having != null) " HAVING $having"
        }

        if (orderBy != null) sql += " ORDER BY $orderBy"

        return db.rawQuery(sql, selectionArgs)
    }

    companion object {

        const val NOME_TABELA = "instituicoes"
        const val CAMPO_NOME = "nome"
        const val CAMPO_TELEFONE = "telefone"
        const val CAMPO_MORADA = "morada"
        const val CAMPO_ID_TAREFAS = "id_tarefas"
        const val CAMPO_EXTERNO_NOME_TAREFA = "nome_tarefa"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME,CAMPO_TELEFONE,CAMPO_MORADA,CAMPO_ID_TAREFAS, CAMPO_EXTERNO_NOME_TAREFA)
    }
}

