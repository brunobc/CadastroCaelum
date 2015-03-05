package br.hue.caelum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import br.hue.caelum.dao.AlunoDAO;
import br.hue.caelum.dao.DBHelper;
import br.hue.caelum.modelo.Aluno;
import br.hue.caelum.utils.Extras;

public class FormularioActivity extends Activity {

    private FormularioHelper helper;
    private AlunoDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		this.helper = new FormularioHelper(this);
		
		final Button gravar = (Button) findViewById(R.id.gravar);
		
		final Aluno alunoParaSerAlterado = (Aluno) getIntent().getSerializableExtra(Extras.ALUNO_SELECIONADO);
		
		if (alunoParaSerAlterado != null) {
			System.out.println(alunoParaSerAlterado);
			System.out.println(alunoParaSerAlterado.getId());
			this.helper.colocaNoFormulario(alunoParaSerAlterado);
		}
		
		gravar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Aluno aluno = helper.pegaAlunoDoFormulario();
				
				DBHelper dbHelper = new DBHelper(FormularioActivity.this);
				dao = new AlunoDAO(dbHelper);
				
                if (alunoParaSerAlterado != null) {
                	System.out.println(alunoParaSerAlterado);
                	System.out.println(alunoParaSerAlterado.getId());
                	aluno.setId(alunoParaSerAlterado.getId());
                	gravar.setText("Alterar");
                	dao.altera(aluno);
                } else {
                	dao.insere(aluno);
                }
                dao.close();
				finish();
			}
		});
	}
	
}
