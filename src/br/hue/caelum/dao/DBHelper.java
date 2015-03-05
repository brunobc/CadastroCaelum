package br.hue.caelum.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	private static final int VERSAO = 1;
    private static final String DATABASE = "CadastroCaelum";
    private static final String TABELA = "ALUNOS";

    public DBHelper(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String primeiraTabela = "CREATE TABLE " + TABELA +
                "(" +
                " id INTEGER PRIMARY KEY," +
                " nome TEXT UNIQUE NOT NULL," +
                " telefone TEXT," +
                " endereco TEXT," +
                " site TEXT," +
                " nota REAL," +
                " caminhoFoto TEXT" +        
                ");";
        //String segundaTabela = "CREATE TABLE ...";

        db.execSQL(primeiraTabela);
        //db.execSQL(segundaTabela);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		onCreate(db);
	}

}
