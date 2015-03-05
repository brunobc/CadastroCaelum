package br.hue.caelum;

import java.util.List;

import br.hue.caelum.dao.AlunoDAO;
import br.hue.caelum.dao.DBHelper;
import br.hue.caelum.modelo.Aluno;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends Activity {

	private ListView listaAlunos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);
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
		
		listaAlunos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ListaAlunosActivity.this, "Posição selecionada: " + position, Toast.LENGTH_LONG).show();
			}
		});
		
		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
                Toast.makeText(ListaAlunosActivity.this, "Aluno clicado é: " + adapter.getItemAtPosition(posicao), Toast.LENGTH_SHORT).show();
                return true;
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

}
