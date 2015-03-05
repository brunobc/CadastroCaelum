package br.hue.caelum.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import br.hue.caelum.modelo.Aluno;
import br.hue.caelum.utils.Extras;

public class AlunoDAO {
    
    private SQLiteOpenHelper helper;

	public AlunoDAO(DBHelper helper) {
		this.helper = helper;
	}

	public void insere(Aluno aluno) {
		ContentValues values = toContentValues(aluno);
		
		this.helper.getWritableDatabase().insert(Extras.TABELA, null, values);
	}
	
	public void altera(Aluno aluno) {
		ContentValues values = toContentValues(aluno);
		System.out.println(aluno);
		System.out.println(aluno.getId());
		String[] idParaSerAlterado = { aluno.getId().toString() }; 

		this.helper.getWritableDatabase().update(Extras.TABELA, values, "id=?", idParaSerAlterado);
	}
	
	public void close() {
		this.helper.close();
	}
	
	public ContentValues toContentValues(Aluno aluno) {
		ContentValues values = new ContentValues();
		values.put("nome", aluno.getNome());
		values.put("nota", aluno.getNota());
		values.put("endereco", aluno.getEndereco());
		values.put("site", aluno.getSite());
		values.put("telefone", aluno.getTelefone());
        values.put("caminhoFoto", aluno.getCaminhoFoto());
		return values;
	}
	
	public List<Aluno> getLista() {
		/*String sql = "SELECT * FROM " + Extras.TABELA + ";";
		Cursor cursor = this.helper.getReadableDatabase().rawQuery(sql, null);
		
		List<Aluno> alunos = new ArrayList<>();
		while(cursor.moveToNext()) {
		    Aluno aluno = new Aluno();
		    aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
		    aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
		    aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
		    aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
		    aluno.setCaminhoFoto(cursor.getString(cursor.getColumnIndex("caminhoFoto")));
		    aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
		    
		    alunos.add(aluno);
		}
		return alunos;*/
		List<Aluno> alunos = new ArrayList<Aluno>();
		Cursor cursor = this.helper.getWritableDatabase().query(Extras.TABELA,
				Extras.COLUNAS, null, null, null, null, null);
		while (cursor.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(cursor.getLong(0));
			aluno.setNome(cursor.getString(1));
			aluno.setTelefone(cursor.getString(2));
			aluno.setEndereco(cursor.getString(3));
			aluno.setSite(cursor.getString(4));
			aluno.setNota(cursor.getDouble(5));
			aluno.setCaminhoFoto(cursor.getString(6));

			alunos.add(aluno);

		}

		cursor.close();
		return alunos;
	}
	
	public void deleta(Aluno aluno) {
		String[] args = {aluno.getId().toString()};
		this.helper.getWritableDatabase().delete(Extras.TABELA, "id=?", args);
	}

}

