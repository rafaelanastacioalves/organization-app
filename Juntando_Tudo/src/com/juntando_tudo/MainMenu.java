package com.juntando_tudo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void listar ( View v){
		ImageButton bTela = (ImageButton) findViewById(v.getId());
		String sNome = bTela.toString();
		mostrarMSG(sNome, "Info Button" );
		Intent iChamaLista = new Intent(this,Lista.class);
		startActivity(iChamaLista);
		this.overridePendingTransition(R.anim.slide2, R.anim.slide);
	}
	public void mostrarMSG(String strMSG, String strTitle){
		AlertDialog adMensagem = new AlertDialog.Builder(this).create();
		adMensagem.setMessage(strMSG);
		adMensagem.setTitle(strTitle);
		adMensagem.show();
	}

}
