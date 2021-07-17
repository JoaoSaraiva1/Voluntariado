package com.example.voluntariado


    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import androidx.navigation.findNavController
    import androidx.navigation.ui.AppBarConfiguration
    import androidx.navigation.ui.navigateUp
    import androidx.navigation.ui.setupActionBarWithNavController
    import android.view.Menu
    import android.view.MenuItem
    import android.widget.Toast
    import com.example.voluntariado.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity() {

        private lateinit var appBarConfiguration: AppBarConfiguration
        private lateinit var binding: ActivityMainBinding
        private lateinit var menu: Menu

        var menuAtual = R.menu.menu_inicial
            set(value) {
                field = value
                invalidateOptionsMenu()
            }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setSupportActionBar(binding.toolbar)

            val navController = findNavController(R.id.nav_host_fragment_content_main)
            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController, appBarConfiguration)

            DadosApp.activity = this
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(menuAtual, menu)
            this.menu = menu

            if (menuAtual == R.menu.menu_lista_instituicoes) {
                atualizaMenuListaInstituicoes(false)
            }

            if (menuAtual == R.menu.menu_lista_voluntarios) {
                atualizaMenuListaVoluntarios(false)
            }
            if (menuAtual == R.menu.menu_lista_tarefas) {
                atualizaMenuListaTarefas(false)
            }
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            val opcaoProcessada = when (item.itemId) {
                R.id.action_settings -> {
                    Toast.makeText(this, R.string.versao, Toast.LENGTH_LONG).show()
                    true
                }

                else -> when (menuAtual) {
                    R.menu.menu_lista_instituicoes -> (DadosApp.fragment as ListaInstitucoesFragment).processaOpcaoMenu(item)
                    R.menu.menu_nova_instituicao -> (DadosApp.fragment as NovoInstituicaoFragment).processaOpcaoMenu(item)
                    R.menu.menu_edita_instituicao -> (DadosApp.fragment as EditaInstituicaoFragment).processaOpcaoMenu(item)
                    R.menu.menu_elimina_instituicao -> (DadosApp.fragment as EliminaInstituicaoFragment).processaOpcaoMenu(item)
                    R.menu.menu_lista_voluntarios -> (DadosApp.fragment as ListaVoluntariosFragment).processaOpcaoMenu(item)
                    R.menu.menu_novo_voluntario -> (DadosApp.fragment as NovoVoluntarioFragment).processaOpcaoMenu(item)
                    R.menu.menu_edita_voluntario -> (DadosApp.fragment as EditaVoluntarioFragment).processaOpcaoMenu(item)
                    R.menu.menu_elimina_voluntario -> (DadosApp.fragment as EliminaVoluntarioFragment).processaOpcaoMenu(item)
                    R.menu.menu_lista_tarefas -> (DadosApp.fragment as ListaTarefasFragment).processaOpcaoMenu(item)
                    R.menu.menu_novo_tarefa -> (DadosApp.fragment as NovoTarefaFragment).processaOpcaoMenu(item)
                    R.menu.menu_edita_tarefa -> (DadosApp.fragment as EditaTarefaFragment).processaOpcaoMenu(item)
                    //R.menu.menu_elimina_tarefa -> (DadosApp.fragment as EliminaVoluntarioFragment).processaOpcaoMenu(item)
                    else -> false
                }
            }

            return if(opcaoProcessada) true else super.onOptionsItemSelected(item)
        }

        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            return navController.navigateUp(appBarConfiguration)
                    || super.onSupportNavigateUp()
        }

        fun atualizaMenuListaInstituicoes(mostraBotoesAlterarEliminar : Boolean) {
            menu.findItem(R.id.action_alterar_instituicao).setVisible(mostraBotoesAlterarEliminar)
            menu.findItem(R.id.action_eliminar_instituicao).setVisible(mostraBotoesAlterarEliminar)
        }
        fun atualizaMenuListaVoluntarios(mostraBotoesAlterarEliminar : Boolean) {
            menu.findItem(R.id.action_alterar_voluntario).setVisible(mostraBotoesAlterarEliminar)
            menu.findItem(R.id.action_eliminar_voluntario).setVisible(mostraBotoesAlterarEliminar)
        }
        fun atualizaMenuListaTarefas(mostraBotoesAlterarEliminar : Boolean) {
            menu.findItem(R.id.action_alterar_tarefa).setVisible(mostraBotoesAlterarEliminar)
            menu.findItem(R.id.action_eliminar_tarefa).setVisible(mostraBotoesAlterarEliminar)
        }
    }