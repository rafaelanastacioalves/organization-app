package pfc.ime.gtdmanager.model;

import java.util.List;

import android.database.Cursor;
import pfc.ime.gtdmanager.helper.DBHelper;

public class ActionBox extends Table {

	String name;
	
	List<CheckLine> lstCheckLines; // added by Rafael
	
	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public ActionBox() { }
	
	
	public ActionBox(String Name) {
		setName(Name);
	}
	
	public ActionBox(Cursor c) {
		this.setId				(c.getInt	(c.getColumnIndex(DBHelper.KEY_ID)));
		this.setName			(c.getString(c.getColumnIndex(DBHelper.KEY_ACTIONBOXES_NAME)));
		this.setCreated_at		(c.getString(c.getColumnIndex(DBHelper.KEY_CREATED_AT)));
	}
	
	public String toS () {
		return "(" + getId() + "," + getName() + "," + getCreated_at() + ")";
	}
	
	//--------------------------------------added by Rafael Alves -----------------------------------//
	
	public void addCheckLine(String strText, DBHelper dbHlpCurrent){
		CheckLine chkLnNew = new CheckLine();
		
		//----------------------setting values---------------------/
		
		// text
		chkLnNew.setText(strText); 
		
		// order
		if(this.lstCheckLines == null) {
			getAllChecklines(dbHlpCurrent);
		}
		chkLnNew.setOrder(lstCheckLines.size() + 1);
		
		//ActionBox
		chkLnNew.setActionbox_id(this.id);
		
		dbHlpCurrent.createCheckLine(chkLnNew);	

		
	}
	
	//added by Rafael Alves
	
	/**
	 * @param strText
	 * @param dbHlpCurrent
	 * @param strDateCreated
	 */
	public void addCheckLine(String strText, DBHelper dbHlpCurrent, String strDateCreated){
		
		CheckLine chkLnNew = new CheckLine();
		
		//----------------------setting values---------------------//
		
		// text
		chkLnNew.setText(strText); 
		
		// order
		if(this.lstCheckLines == null) {
			lstCheckLines = getAllChecklines(dbHlpCurrent);
		}
		chkLnNew.setOrder(lstCheckLines.size() + 1);
		
		//ActionBox
		chkLnNew.setActionbox_id(this.id);
		
	
		//Created at..
		
		chkLnNew.setCreated_at(strDateCreated);
		
		// ------------inserting---------------------------------//
		dbHlpCurrent.createCheckLine(chkLnNew);	


	}
	
	
	//retrieve all checklines of this ActionBox -- added by Rafael
	/**
	 * @param dbHlpCurrent
	 * @return List<CheckLine>
	 */
	public List<CheckLine> getAllChecklines(DBHelper dbHlpCurrent){
		
				return    dbHlpCurrent.getAllToDosByTag(this.id);
				
	}
	public ActionBox( int id,  DBHelper dbhpCurrent){
		this.id= id;
		dbhpCurrent.createActionBox(this);
	}
}
