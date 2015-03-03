package br.hue.caelum;

import android.app.Activity;
import android.os.Bundle;
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
		final String[] alunos = { "Anderson", "Filipe", "Guilherme" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);

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

}
