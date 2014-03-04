package pfc.ime.gtdmanager.model;

import java.util.Iterator;
import java.util.List;

import android.database.Cursor;
import pfc.ime.gtdmanager.helper.DBHelper;

/**
 * @author rafaelanastacioalves
 *
 */
/**
 * @author rafaelanastacioalves
 *
 */
public class ActionBox extends Table {

	String name;
	
	private List<CheckLine> lstCheckLines; // added by Rafael
	
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
	public void loadAllCheckLines(DBHelper dbHlpCurrent){
		this.lstCheckLines = this.getAllChecklines(dbHlpCurrent);
	}

	//----------------------- GET------------------------//
	
//	/**
//	 * Take the CheckLine at that position and returns its String Value
//	 * @param intPosition
//	 * @return String
//	 */
//	public String get(int intPosition){
//		
//		return lstCheckLines.get(intPosition).getText();
//				
//	}

	public CheckLine getCheckLineByID (int ID) {
		Iterator<CheckLine> i = lstCheckLines.iterator();
		while (i.hasNext()) {
			CheckLine chlnCurrent = (CheckLine) i.next();
			if (chlnCurrent.getId() == ID) {
				return chlnCurrent;
			}
		}
		return null;
	}
	
	/**
	 * Returns a CheckLine at the position specified. The object who receives it take the responsability 
	 * for manipulation of the {@link CheckLine}.
	 * @param intPostion
	 * @return {@link CheckLine}
	 */
	public CheckLine get(int intPostion){
		
		return lstCheckLines.get(intPostion);
	}
	
	public int size(){
				
		return lstCheckLines.size();
	}
	
	public boolean getCheckedStatusByID(int ID){
		
		return  getCheckLineByID(ID).isChecked();
		 
	}
	public void toogleCheckByID(int ID){
		getCheckLineByID(ID).toogleCheck();
	}
	
	public void setChecked(CheckLine chlnCheckLine){
		lstCheckLines.get(lstCheckLines.indexOf(chlnCheckLine)).setChecked(true);
		
		
	}
	
	public void setUnCheked(CheckLine chlnCheckLine){
		lstCheckLines.get(lstCheckLines.indexOf(chlnCheckLine)).setChecked(false);
	}
	
	public void persistCheckLine (CheckLine chlnCheckLine, DBHelper dbHelper){
		
		
		dbHelper.updateCheckLine( lstCheckLines.get(lstCheckLines.indexOf(chlnCheckLine)));
		
	}
		
	
	
}
