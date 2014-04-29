package pfc.ime.gtdmanager.main;

import pfc.ime.gtdmanager.controller.Controller;

import com.juntando_tudo.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

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
		String sNome = getResources().getString(v.getId());
//		mostrarMSG(sNome, "Info Button" );
		aController.goToList(sNome, this);
		
	}
	public void mostrarMSG(String strMSG, String strTitle){
		AlertDialog adMensagem = new AlertDialog.Builder(this).create();
		adMensagem.setMessage(strMSG);
		adMensagem.setTitle(strTitle);
		adMensagem.show();
	}

}
