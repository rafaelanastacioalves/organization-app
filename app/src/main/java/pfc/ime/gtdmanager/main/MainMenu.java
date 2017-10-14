package pfc.ime.gtdmanager.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import pfc.ime.gtdmanager.R;
import pfc.ime.gtdmanager.controller.Controller;

public class MainMenu extends Activity {
	
	Controller aController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		aController = (Controller) getApplicationContext();
		aController.setupBD();
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public void listar ( View v){
		View bTela = (View) findViewById(v.getId());
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
