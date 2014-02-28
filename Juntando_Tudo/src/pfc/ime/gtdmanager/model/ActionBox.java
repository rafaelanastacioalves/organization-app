package pfc.ime.gtdmanager.model;

import android.database.Cursor;
import pfc.ime.gtdmanager.helper.DBHelper;

public class ActionBox extends Table {

	String name;
	
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
}
