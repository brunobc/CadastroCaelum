package br.hue.caelum;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import br.hue.caelum.modelo.Aluno;

public class FormularioHelper {

	private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private SeekBar nota;
    private ImageView botaoImagem;
    
    private Aluno aluno;
    
	public FormularioHelper(FormularioActivity activity) {
        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site = (EditText) activity.findViewById(R.id.site);
        nota = (SeekBar) activity.findViewById(R.id.nota);
        botaoImagem = (ImageView) activity.findViewById(R.id.foto);
        
        aluno = new Aluno();
    }
	
	public Aluno pegaAlunoDoFormulario() {
        aluno.setNome(nome.getEditableText().toString());
        aluno.setTelefone(telefone.getEditableText().toString());
        aluno.setEndereco(endereco.getEditableText().toString());
        aluno.setSite(site.getEditableText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));

        return aluno;
    }
	
	public void colocaNoFormulario(Aluno aluno) {
		nome.setText(aluno.getNome());
		telefone.setText(aluno.getTelefone());
		endereco.setText(aluno.getEndereco());
		site.setText(aluno.getSite());
		nota.setProgress(aluno.getNota().intValue());
		
		this.aluno = aluno;
	}
	
}
