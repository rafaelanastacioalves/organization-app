package pfc.ime.gtdmanager.model;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import pfc.ime.gtdmanager.controller.Controller;
import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.text.format.Time;

public class CalendarAdapter {

	private int CALENDAR_ID;
	private List<CheckLine> lstChkLn;
	private Cursor mCursor = null;
	private static final String[] COLS = new String[] {
			CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART };
	private Controller aController;
	private Activity currAct;

	public List<CheckLine> getCheckLines(Activity curAct) {
		this.currAct = curAct;
		Format df = DateFormat.getDateFormat(aController
				.getApplicationContext());
		Format tf = DateFormat.getTimeFormat(aController
				.getApplicationContext());
		Long id = 0L;
		String title = "N/A";
		Long start = 0L;

		mCursor = query3();

		if (mCursor.moveToFirst()) {
			do {
				// adding to checklines list
				CheckLine chkLnNew = new CheckLine();

				try {
					title = mCursor.getString(0);

					start = mCursor.getLong(1);
				

				} catch (Exception e) {
					// ignore

				}
				chkLnNew.setText(title + " on " + df.format(start) + " at "
						+ tf.format(start));

				// order

				chkLnNew.setOrder(mCursor.getPosition());

				// ActionBox
				chkLnNew.setActionbox_id(CALENDAR_ID);

				lstChkLn.add(chkLnNew);

			} while (mCursor.moveToNext());
		}
		return lstChkLn;

	}

	public Cursor query2() {
		mCursor = currAct.getContentResolver().query(

		CalendarContract.Events.CONTENT_URI, COLS, null, null, null

		);
		return mCursor;
	}

	public Cursor query1() {
		Cursor cur = null;
		String selection = "((" + CalendarContract.Events.DTSTART
				+ " <= ?) AND (" + CalendarContract.Events.DTEND + " >= ?))";

		Time t = new Time();
		t.setToNow();
		String dtStart = Long.toString(t.toMillis(false));
		t.set(59, 59, 23, t.monthDay, t.month, t.year);
		String dtEnd = Long.toString(t.toMillis(false));
		t.set(00, 00, 00, t.monthDay, t.month, t.year);
		String[] selectionArgs = new String[] { dtStart, dtEnd };
		cur = currAct.getContentResolver().query(
				CalendarContract.Events.CONTENT_URI, COLS, selection,
				selectionArgs, null);
		return cur;

	}

	public Cursor query3() {
		Uri.Builder eventsUriBuilder = CalendarContract.Instances.CONTENT_URI
				.buildUpon();
		Time t = new Time(Time.getCurrentTimezone());
		t.setToNow();
		String dtStart = Long.toString(t.toMillis(false));
		t.set(59, 59, 23, t.monthDay, t.month, t.year);
		String dtEnd = Long.toString(t.toMillis(false));
		ContentUris.appendId(eventsUriBuilder, t.toMillis(true));
		t.set(59, 59, 23, t.monthDay, t.month, t.year);
		ContentUris.appendId(eventsUriBuilder, t.toMillis(true));
		Uri eventsUri = eventsUriBuilder.build();
		Cursor cursor = null;
		cursor = aController
				.getApplicationContext()
				.getContentResolver()
				.query(eventsUri, COLS, null, null,
						CalendarContract.Instances.DTSTART + " ASC");
		return cursor;
	}

	public CalendarAdapter(int actBoxID, Controller aController) {
		this.CALENDAR_ID = actBoxID;
		lstChkLn = new ArrayList<CheckLine>();
		this.aController = aController;
	}
}