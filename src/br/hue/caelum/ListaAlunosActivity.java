package br.hue.caelum;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.hue.caelum.dao.AlunoDAO;
import br.hue.caelum.dao.DBHelper;
import br.hue.caelum.modelo.Aluno;
import br.hue.caelum.utils.Extras;

public class ListaAlunosActivity extends Activity {

	private ListView listaAlunos;
    private Aluno alunoSelecionado;
    private AlunoDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);
		
		listaAlunos = (ListView) findViewById(R.id.lista_alunos);
		registerForContextMenu(listaAlunos);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.carregaLista();
	}
	
	private void carregaLista() {
		DBHelper dbHelper = new DBHelper(ListaAlunosActivity.this);
		AlunoDAO dao = new AlunoDAO(dbHelper);
		List<Aluno> lista = dao.getLista();
		System.out.println(lista);
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, lista);

		listaAlunos = (ListView) findViewById(R.id.lista_alunos);
		listaAlunos.setAdapter(adapter);
		
		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
	        @Override
	        public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
	        	alunoSelecionado = (Aluno) adapter.getItemAtPosition(posicao);
	            return false;
	        }
	    });
		
		listaAlunos.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                
                alunoSelecionado = (Aluno) adapter.getItemAtPosition(posicao);
                edicao.putExtra(Extras.ALUNO_SELECIONADO, alunoSelecionado);
                
                startActivity(edicao);
            }
        });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = this.getMenuInflater();
	    inflater.inflate(R.menu.menu_principal, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
        case R.id.menu_novo:
        	Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
            startActivity(intent);
            return false;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add("Ligar");
	    menu.add("Enviar SMS");
	    menu.add("Achar no Mapa");
	    menu.add("Navegar no site");
	    MenuItem deletar = menu.add("Deletar");
	    menu.add("Enviar E-mail");
	    
	    deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				DBHelper dbHelper = new DBHelper(ListaAlunosActivity.this);
				dao = new AlunoDAO(dbHelper);
				dao.deleta(alunoSelecionado);
				dao.close();
				
				carregaLista();
				return false;
			}
		});
	}

}
