package com.juntando_tudo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Lista extends Activity {
	private final List<String> selecionados = new ArrayList<String>();
	
	// Lista de Estados que será exibida
	 private final String[] ESTADOS = new String[] {
	     "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal",
	 "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul",
	 "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
	 "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina",
	 "São Paulo", "Sergipe", "Tocantins"
	 };
	
	@Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     Intent iRecebeDados = getIntent();
	      setupActionBar();
	     // Define o arquivo /layout/main.xml como layout principal da aplicação
	     setContentView(R.layout.main);
	      
	     // ListView
	     ListView lsvEstados = (ListView) findViewById(R.id.lsvEstados);
	      
	     // Adapter para implementar o layout customizado de cada item
	     ArrayAdapter<String> lsvEstadosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) {
	         @Override
	         public View getView(int position, View convertView, ViewGroup parent) {
	             // Recuperando o Estado selecionado de acordo com a sua posição no ListView
	         String estado = ESTADOS[position];
	
	             // Se o ConvertView for diferente de null o layout já foi "inflado"
	         View v = convertView;
	
	             if(v==null) {
	             // "Inflando" o layout do item caso o isso ainda não tenha sido feito
	             LayoutInflater inflater = getLayoutInflater();
	             v = (View) inflater.inflate(R.layout.item_estado, null);
	         }
	
	         // Recuperando o checkbox
	         CheckBox chk = (CheckBox) v.findViewById(R.id.chkEstados);
	
	         // Definindo um "valor" para o checkbox
	         chk.setTag(estado);
	
	             /** Definindo uma ação ao clicar no checkbox. Aqui poderiamos armazenar um valor chave
	          * que identifique o objeto selecionado para que o mesmo possa ser, por exemplo, excluído
	          * mais tarde.
	          */
	         chk.setOnClickListener(new View.OnClickListener() {
	         @Override
	         public void onClick(View v) {
	             CheckBox chk = (CheckBox) v;
	         String estado = (String) chk.getTag();
	         if(chk.isChecked()) {
	             Toast.makeText(getApplicationContext(), "Checbox de " + estado + " marcado!", Toast.LENGTH_SHORT).show();
	             if(!selecionados.contains(estado))
	                 selecionados.add(estado);
	                     } else {
	                 Toast.makeText(getApplicationContext(), "Checbox de " + estado + " desmarcado!", Toast.LENGTH_SHORT).show();
	                 if(selecionados.contains(estado))
	                     selecionados.remove(estado);
	                     }
	         
	                 }
	     });
	
	             // Preenche o TextView do layout com o nome do Estado
	         TextView txv = (TextView) v.findViewById(R.id.txvEstados);
	         txv.setText(estado);
	         if(selecionados.contains(estado)) {
	             chk.setChecked(true);
	         } else {
	             chk.setChecked(false);
	         }
	
	         return v;
	         }
	
	         @Override
	         public long getItemId(int position) {
	             return position;
	         }
	
	         @Override
	         public int getCount() {
	             return ESTADOS.length;
	         }
	     };
	     
	     lsvEstados.setAdapter(lsvEstadosAdapter);
	
	 }

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
