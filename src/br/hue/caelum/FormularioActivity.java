package br.hue.caelum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FormularioActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Button gravar = (Button) findViewById(R.id.gravar);
		gravar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(FormularioActivity.this, "Você clicou no botão", Toast.LENGTH_LONG).show();
				finish();
			}
		});
	}

}
