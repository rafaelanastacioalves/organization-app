package pfc.ime.gtdmanager.model;

import java.util.Date;

import pfc.ime.gtdmanager.DataAccessLayer.DBHelper;
import android.database.Cursor;

public class CheckLine extends Table {

	String text;
	int actionbox_id;
	int order;
	boolean checked;
	String update_time;
	String check_time;
	int calendar_id;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		setUpdate_time(new Date().toString());
	}

	public int getActionbox_id() {
		return actionbox_id;
	}

	public void setActionbox_id(int actionbox_id) {
		this.actionbox_id = actionbox_id;
		setUpdate_time(new Date().toString());

	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
		setCheck_time(new Date().toString());
		setUpdate_time(new Date().toString());
	}

	public void setChecked(int checked) {
		this.checked = (checked != 0);
		setCheck_time(new Date().toString());
		setUpdate_time(new Date().toString());
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getCheck_time() {
		if (check_time == null) {
			check_time = new Date().toString();
		}
		return check_time;
	}

	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}

	public int getCalendarId() {
		return calendar_id;
	}

	public void setCalendarId(int calendar_id) {
		this.calendar_id = calendar_id;
	}

	public CheckLine() {
	}

	public CheckLine(Cursor c) {
		this.setId(c.getInt(c.getColumnIndex(DBHelper.KEY_ID)));
		this.setText(c.getString(c.getColumnIndex(DBHelper.KEY_CHECKLINES_TEXT)));
		this.setActionbox_id(c.getInt(c
				.getColumnIndex(DBHelper.KEY_CHECKLINES_ACTIONBOX_ID)));
		this.setOrder(c.getInt(c.getColumnIndex(DBHelper.KEY_CHECKLINES_ORDER)));
		this.setChecked(c.getInt(c
				.getColumnIndex(DBHelper.KEY_CHECKLINES_CHECKED)));
		this.setUpdate_time(c.getString(c
				.getColumnIndex(DBHelper.KEY_CHECKLINES_UPDATE_TIME)));
		this.setCheck_time(c.getString(c
				.getColumnIndex(DBHelper.KEY_CHECKLINES_CHECK_TIME)));
		this.setCreated_at(c.getString(c
				.getColumnIndex(DBHelper.KEY_CREATED_AT)));
		this.setCalendarId(c.getInt(c
				.getColumnIndex(DBHelper.KEY_CHECKLINES_CALENDAR_ID)));
	}

	public void toogleCheck() {
		checked = !checked;
		setCheck_time((new Date()).toString());
	}

	public String toS() {
		return "(" + getId() + "," + getText() + "," + getActionbox_id() + ","
				+ getOrder() + "," + isChecked() + ","
				+ (new Date()).toString() + "," + getCreated_at() + ","
				+ getCheck_time().toString() + "," + getCalendarId() + ")";
	}

}
