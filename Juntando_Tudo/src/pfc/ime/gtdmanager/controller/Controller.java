package pfc.ime.gtdmanager.controller;

import java.util.List;

import pfc.ime.gtdmanager.DataAccessLayer.DBHelper;
import pfc.ime.gtdmanager.model.ActionBox;
import pfc.ime.gtdmanager.model.CheckLine;
import android.app.Application;

public class Controller extends Application {
	private ActionBox actBox;
	
	private DBHelper dbHelper;
	
	public void setActionBox(ActionBox actBox){
		
		this.actBox = actBox; 
	}
	public void setActionBox(int id){
		if(dbHelper == null){
			dbHelper = new DBHelper(getApplicationContext());
		}
		this.actBox = dbHelper.getActionBoxById(id);
	}
	
	
	
	public ActionBox getActionBox(){
		
		return actBox;
		
	}
	
	
	private void loadActionBox(DBHelper dbHlpCurrent){
		this.actBox.setCheckLines(this.getAllChecklines(dbHlpCurrent)); 
	}
	
	public List<CheckLine> getAllChecklines(DBHelper dbHlpCurrent){
		
		return    dbHlpCurrent.getAllToDosByTag(actBox.getId());
	
}
	

	public void addCheckLine(String strText, DBHelper dbHlpCurrent){
		CheckLine chkLnNew = new CheckLine();
		
		//----------------------setting values---------------------/
		
		// text
		chkLnNew.setText(strText); 
		
		// order
		if(actBox.getCheckLines() == null) {
			getAllChecklines(dbHlpCurrent);
		}
		chkLnNew.setOrder(actBox.size() + 1);
		
		//ActionBox
		chkLnNew.setActionbox_id(actBox.getId());
		
		dbHlpCurrent.createCheckLine(chkLnNew);	

		
	}
public void addCheckLine(String strText, DBHelper dbHlpCurrent, String strDateCreated){
		
		CheckLine chkLnNew = new CheckLine();
		
		//----------------------setting values---------------------//
		
		// text
		chkLnNew.setText(strText); 
		
		// order
		if(actBox.getCheckLines() == null) {
			actBox.setCheckLines(getAllChecklines(dbHlpCurrent));
		}
		chkLnNew.setOrder(actBox.size() + 1);
		
		//ActionBox
		chkLnNew.setActionbox_id(actBox.getId());
		
	
		//Created at..
		
		chkLnNew.setCreated_at(strDateCreated);
		
		// ------------inserting---------------------------------//
		dbHlpCurrent.createCheckLine(chkLnNew);	


	}
public void persistCheckLine (CheckLine chlnCheckLine, DBHelper dbHelper){
	
	
	dbHelper.updateCheckLine( actBox.get(actBox.getCheckLines().indexOf(chlnCheckLine)));
	
}
public void codigoTeste(){
	dbHelper = new DBHelper(this);
	//    ActionBox listaTeste = new ActionBox("bla");
	    
	// getAllActionBoxes
	//    List<ActionBox> lAB = new 	ArrayList<ActionBox>();
	//    lAB = dbHelper.getAllActionBoxes();
	//    Iterator<ActionBox> iAB = lAB.iterator();
	//    String strActionBox = "";
	//    while (iAB.hasNext()) {
	//		ActionBox actionBox = (ActionBox) iAB.next();
	//		strActionBox +=  "" + actionBox.getName() + "ID: "+ actionBox.getId() +  " \n";
	//		}
	//    loadList(1, dbHelper);
	loadActionBox(dbHelper);
//	lstCheckLine = getAllChecklines(dbHelper);
//	actBox.loadAllCheckLines(dbHelper);

}

public void setupBD(){
	if(dbHelper == null){
		dbHelper = new DBHelper(getApplicationContext());
	}
	dbHelper.populateActionBoxesTable();
}
	
	
	
	
	
}
