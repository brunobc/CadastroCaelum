package br.hue.caelum.dao;

import br.hue.caelum.utils.Extras;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
    public DBHelper(Context context) {
        super(context, Extras.DATABASE, null, Extras.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String primeiraTabela = "CREATE TABLE " + Extras.TABELA +
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
		String sql = "DROP TABLE IF EXISTS " + Extras.TABELA;
		db.execSQL(sql);
		onCreate(db);
	}

}
