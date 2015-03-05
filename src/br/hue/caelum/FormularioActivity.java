package br.hue.caelum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.hue.caelum.dao.AlunoDAO;
import br.hue.caelum.dao.DBHelper;
import br.hue.caelum.modelo.Aluno;

public class FormularioActivity extends Activity {

    private FormularioHelper helper;
    private AlunoDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		this.helper = new FormularioHelper(this);
		
		Button gravar = (Button) findViewById(R.id.gravar);
		
		gravar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Aluno aluno = helper.pegaAlunoDoFormulario();
				
				DBHelper dbHelper = new DBHelper(FormularioActivity.this);
				dao = new AlunoDAO(dbHelper);
				dao.insere(aluno);
				finish();
			}
		});
	}
	
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    dao.close();
	}

}
