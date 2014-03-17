package pfc.ime.gtdmanager.DataAccessLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pfc.ime.gtdmanager.model.ActionBox;
import pfc.ime.gtdmanager.model.CheckLine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	// Logcat tag
	public static final String LOG = "DBHelper";

	// Database Version
	public static final int DATABASE_VERSION = 1;

	// Database Name
	public static final String DATABASE_NAME = "GTDManagerDB";

	// Table Names
	public static final String TABLE_ACTIONBOXES = 	"actionboxes";
	public static final String TABLE_CHECKLINES = 	"checklines";

	// Common column names
	public static final String KEY_ID = 			"id";
	public static final String KEY_CREATED_AT = 	"created_at";

	// ACTIONBOXES Table - column names
	public static final String KEY_ACTIONBOXES_NAME = "name";

	// CHECKLINES Table - column names
	public static final String KEY_CHECKLINES_TEXT = 			"text";
	public static final String KEY_CHECKLINES_ACTIONBOX_ID = 	"actionbox_id";
	public static final String KEY_CHECKLINES_ORDER = 			"line_order";
	public static final String KEY_CHECKLINES_CHECKED = 		"checked";
	public static final String KEY_CHECKLINES_UPDATE_TIME = 	"update_time";
	public static final String KEY_CHECKLINES_CHECK_TIME = 		"check_time";
	
	// Table Create Statements
	// ACTIONBOXES table create statement
	private static final String CREATE_TABLE_ACTIONBOXES = "CREATE TABLE " + TABLE_ACTIONBOXES 
			+ "(" 
			+ KEY_ID 				+ " INTEGER PRIMARY KEY," 
			+ KEY_ACTIONBOXES_NAME 	+ " TEXT UNIQUE NOT NULL," 
			+ KEY_CREATED_AT 		+ " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" 
			+ ")";

	// CHECKLINES table create statement
	private static final String CREATE_TABLE_CHECKLINES = "CREATE TABLE " + TABLE_CHECKLINES
			+ "("
			+ KEY_ID 						+ " INTEGER PRIMARY KEY," 
			+ KEY_CHECKLINES_TEXT 			+ " TEXT NOT NULL,"
			+ KEY_CHECKLINES_ACTIONBOX_ID 	+ " INTEGER NOT NULL DEFAULT 1," 
			+ KEY_CHECKLINES_ORDER 			+ " INTEGER NOT NULL," 
			+ KEY_CHECKLINES_CHECKED 		+ " BOOLEAN NOT NULL DEFAULT FALSE," 
			+ KEY_CHECKLINES_UPDATE_TIME 	+ " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," 
			+ KEY_CHECKLINES_CHECK_TIME 	+ " DATETIME," 
			+ KEY_CREATED_AT 				+ " DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP" 
			+ ")";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		// creating required tables
		db.execSQL(CREATE_TABLE_ACTIONBOXES);
		db.execSQL(CREATE_TABLE_CHECKLINES);		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ACTIONBOXES);
		db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CHECKLINES);
		
		// create new tables
		onCreate(db);
	}
	
	// ------------------------ CHECKLINES table methods ----------------//

		// creating a checkline
		public long createCheckLine(CheckLine checkline) {
			SQLiteDatabase db = this.getWritableDatabase();
			
			Log.e(LOG, "Creating CHECKLINE at: " + checkline.toS());

			ContentValues values = new ContentValues();
			values.put(KEY_CHECKLINES_TEXT			, checkline.getText());
			values.put(KEY_CHECKLINES_ACTIONBOX_ID	, checkline.getActionbox_id());
			values.put(KEY_CHECKLINES_ORDER			, checkline.getOrder());
			values.put(KEY_CHECKLINES_CHECKED		, checkline.isChecked());
			
			if(checkline.getCheck_time() != null)
				values.put(KEY_CHECKLINES_CHECK_TIME, checkline.getCheck_time().toString());

			// insert row and return id
			return db.insert(TABLE_CHECKLINES, null, values);
		}

		// get checkline by id
		public CheckLine getCheckLineById(long id) {
			SQLiteDatabase db = this.getReadableDatabase();

			String selectQuery = "SELECT  * FROM " + TABLE_CHECKLINES + " WHERE " 
					+ KEY_ID + " = " + id;

			Log.e(LOG, selectQuery);

			Cursor c = db.rawQuery(selectQuery, null);

			if (c != null)
				c.moveToFirst();
			return new CheckLine(c);
		}

		// getting all checklines
		public List<CheckLine> getAllCheckLines() {
			List<CheckLine> checklines = new ArrayList<CheckLine>();
			String selectQuery = "SELECT  * FROM " + TABLE_CHECKLINES;

			Log.e(LOG, selectQuery);

			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (c.moveToFirst()) {
				do {
					// adding to checklines list
					checklines.add(new CheckLine(c));
				} while (c.moveToNext());
			}
			return checklines;
		}

		// getting all checklines at a single actionbox
		public List<CheckLine> getAllToDosByTag(int actionbox_id) {
			List<CheckLine> checklines = new ArrayList<CheckLine>();

			String selectQuery = "SELECT  * FROM " + TABLE_CHECKLINES + " WHERE "
					+ KEY_CHECKLINES_ACTIONBOX_ID + " = " + actionbox_id;

			Log.e(LOG, selectQuery);

			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (c.moveToFirst()) {
				do {
					// adding to checklines list
					checklines.add(new CheckLine(c));
				} while (c.moveToNext());
			}
			return checklines;
		}

		// Count checklines
		public int getCheckLinesCount() {
			String countQuery = "SELECT  * FROM " + TABLE_CHECKLINES;
			
			Log.e(LOG, countQuery);
			
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor c = db.rawQuery(countQuery, null);

			return c.getCount();
		}

		// Update a checkline
		public int updateCheckLine(CheckLine checkline) {
			SQLiteDatabase db = this.getWritableDatabase();
			
			Log.e(LOG, "Updating CHECKLINES at: " + checkline.toS());
			
			ContentValues values = new ContentValues();
			values.put(KEY_ID						, checkline.getId());
			values.put(KEY_CHECKLINES_TEXT			, checkline.getText());
			values.put(KEY_CHECKLINES_ACTIONBOX_ID	, checkline.getActionbox_id());
			values.put(KEY_CHECKLINES_ORDER			, checkline.getOrder());
			values.put(KEY_CHECKLINES_CHECKED		, checkline.isChecked());
			values.put(KEY_CHECKLINES_UPDATE_TIME	, (new Date()).toString());
			values.put(KEY_CREATED_AT				, checkline.getCreated_at());
			
			if(checkline.getCheck_time() != null)
				values.put(KEY_CHECKLINES_CHECK_TIME, checkline.getCheck_time().toString());
			
			
			// updating row
			return db.update(TABLE_CHECKLINES, values, KEY_ID + " = ?",
					new String[] { String.valueOf(checkline.getId()) });
		}

		// delete a checkline
		public void deleteCheckLine(long id) {
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(TABLE_CHECKLINES, KEY_ID + " = ?",
					new String[] { String.valueOf(id) });
		}
		
		// ------------------------ ACTIONBOXES table methods ----------------//

				// creating an actionbox
				public long createActionBox(ActionBox actionbox) {
					
					SQLiteDatabase db = this.getWritableDatabase();
					
					Log.e(LOG, "Creating ACTIONBOX at: " + actionbox.toS());

					ContentValues values = new ContentValues();
					values.put(KEY_ACTIONBOXES_NAME	, actionbox.getName());
					
					
					// insert row and return id
					return db.insert(TABLE_ACTIONBOXES, null, values);
					
				
				}

				// get actionbox by id
				public ActionBox getActionBoxById(long id) {
					SQLiteDatabase db = this.getReadableDatabase();

					String selectQuery = "SELECT  * FROM " + TABLE_ACTIONBOXES + " WHERE " 
							+ KEY_ID + " = " + id;

					Log.e(LOG, selectQuery);

					Cursor c = db.rawQuery(selectQuery, null);

					if (c != null)
						c.moveToFirst();
					return new ActionBox(c);
				}

				// getting all actionboxes
				public List<ActionBox> getAllActionBoxes() {
					List<ActionBox> actionboxes = new ArrayList<ActionBox>();
					String selectQuery = "SELECT  * FROM " + TABLE_ACTIONBOXES;

					Log.e(LOG, selectQuery);

					SQLiteDatabase db = this.getReadableDatabase();
					Cursor c = db.rawQuery(selectQuery, null);

					// looping through all rows and adding to list
					if (c.moveToFirst()) {
						do {
							// adding to actionboxes list
							actionboxes.add(new ActionBox(c));
						} while (c.moveToNext());
					}
					return actionboxes;
				}

				// Count actionboxes
				public int getActionBoxesCount() {
					String countQuery = "SELECT  * FROM " + TABLE_ACTIONBOXES;
					
					Log.e(LOG, countQuery);
					
					SQLiteDatabase db = this.getReadableDatabase();
					Cursor c = db.rawQuery(countQuery, null);

					return c.getCount();
				}

				// Update a checkline
				public int updateActionBox(ActionBox actionbox) {
					SQLiteDatabase db = this.getWritableDatabase();
					
					Log.e(LOG, "Updating ACTIONBOXES at: " + actionbox.toS());
					
					ContentValues values = new ContentValues();
					values.put(KEY_ID						, actionbox.getId());
					values.put(KEY_ACTIONBOXES_NAME			, actionbox.getName());
					values.put(KEY_CREATED_AT				, actionbox.getCreated_at());
					
					// updating row
					return db.update(TABLE_CHECKLINES, values, KEY_ID + " = ?",
							new String[] { String.valueOf(actionbox.getId()) });
				}

				// delete a actionbox
				public void deleteActionBox(long id) {
					SQLiteDatabase db = this.getWritableDatabase();
					db.delete(TABLE_ACTIONBOXES, KEY_ID + " = ?",
							new String[] { String.valueOf(id) });
				}
				
				public void populateActionBoxesTable() {
					createActionBox(new ActionBox("Inbox"));
					createActionBox(new ActionBox("Maybe Later"));
					createActionBox(new ActionBox("ASAP"));
					createActionBox(new ActionBox("File"));
				}

				
}
