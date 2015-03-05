package br.hue.caelum.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import br.hue.caelum.modelo.Aluno;

public class AlunoDAO {
    private static final String TABELA = "ALUNOS";
    private SQLiteOpenHelper helper;

	public AlunoDAO(DBHelper helper) {
		this.helper = helper;
	}

	public void insere(Aluno aluno) {
		ContentValues values = toContentValues(aluno);
		
		this.helper.getWritableDatabase().insert(TABELA, null, values);
	}
	
	public void close() {
		this.helper.close();
	}
	
	public ContentValues toContentValues(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("telefone", aluno.getTelefone());
        values.put("caminhoFoto", aluno.getCaminhoFoto());
		return values;
	}
	
	public List<Aluno> getLista() {
		String sql = "SELECT * FROM " + TABELA + ";";
		Cursor cursor = this.helper.getReadableDatabase().rawQuery(sql, null);
		
		List<Aluno> alunos = new ArrayList<>();
		while(cursor.moveToNext()) {
		    Aluno aluno = new Aluno();
		    aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
		    aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
		    aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
		    aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
		    aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
		    alunos.add(aluno);
		}
		return alunos;
	}

}

