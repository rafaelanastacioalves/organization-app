package ime.pfc.CheckApp.Data.model;

import ime.pfc.CheckApp.Data.DBHelper;

import java.util.Iterator;
import java.util.List;

import android.database.Cursor;

public class ActionBox extends Table {

	String name;

	protected List<CheckLine> lstCheckLines;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActionBox() {
	}

	public ActionBox(String Name) {
		setName(Name);
	}

	public ActionBox(Cursor c) {
		this.setId(c.getInt(c.getColumnIndex(DBHelper.KEY_ID)));
		this.setName(c.getString(c
				.getColumnIndex(DBHelper.KEY_ACTIONBOXES_NAME)));
		this.setCreated_at(c.getString(c
				.getColumnIndex(DBHelper.KEY_CREATED_AT)));
	}

	public String toS() {
		return "(" + getId() + "," + getName() + "," + getCreated_at() + ")";
	}

	public List<CheckLine> getCheckLines() {
		return lstCheckLines;
	}

	public void setCheckLines(List<CheckLine> lstCheckLines) {
		this.lstCheckLines = lstCheckLines;
	}

	public CheckLine getCheckLineByID(int ID) {
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
	 * Returns a CheckLine at the position specified. The object who receives it
	 * take the responsability for manipulation of the {@link CheckLine}.
	 * 
	 * @param intPostion
	 * @return {@link CheckLine}
	 */
	public CheckLine get(int intPostion) {

		return lstCheckLines.get(intPostion);
	}

	public int size() {

		return lstCheckLines.size();
	}

	public boolean getCheckedStatusByID(int ID) {

		return getCheckLineByID(ID).isChecked();

	}

	public void toogleCheckByID(int ID) {
		getCheckLineByID(ID).toogleCheck();
	}

	public void setChecked(CheckLine chlnCheckLine) {
		lstCheckLines.get(lstCheckLines.indexOf(chlnCheckLine))
				.setChecked(true);

	}

	public void setUnCheked(CheckLine chlnCheckLine) {
		lstCheckLines.get(lstCheckLines.indexOf(chlnCheckLine)).setChecked(
				false);
	}

	public void addCheckLine(CheckLine chkLn) {

		this.lstCheckLines.add(chkLn);
	}

}
