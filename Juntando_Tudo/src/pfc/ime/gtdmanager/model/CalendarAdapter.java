package pfc.ime.gtdmanager.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentUris;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.view.Menu;
import java.text.Format;

import pfc.ime.gtdmanager.controller.Controller;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.juntando_tudo.R;

public class CalendarAdapter {

	private int CALENDAR_ID;
	private List<CheckLine> lstChkLn;
	private Cursor mCursor = null;
	private static final String[] COLS = new String[]{ CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART};
	private Controller aController;


public List<CheckLine> getCheckLines(Activity curAct){
	
			mCursor = curAct.getContentResolver().query(


	    	CalendarContract.Events.CONTENT_URI, COLS, null, null, null
	    	
	    			);
	   
			Format df = DateFormat.getDateFormat(aController.getApplicationContext());
		    Format tf = DateFormat.getTimeFormat(aController.getApplicationContext());
		    
		    String title = "N/A";
		    Long start = 0L;
	    	
	    	if (mCursor.moveToFirst()) {
				do {
					// adding to checklines list
					CheckLine chkLnNew  = new CheckLine();
				    
				    
				    
				       
				    try {
				    title = mCursor.getString(0);

				    start = mCursor.getLong(1);

				    } catch (Exception e) {
				    //ignore

				    }
					chkLnNew.setText(title+" on "+df.format(start)+" at "+tf.format(start)); 
					
					// order
					
					chkLnNew.setOrder(mCursor.getPosition());
					
					//ActionBox
					chkLnNew.setActionbox_id(CALENDAR_ID);
					
					
					
				lstChkLn.add(chkLnNew);
				
					
				} while (mCursor.moveToNext());
			}
			return lstChkLn;

	
	}	
public CalendarAdapter(int actBoxID, Controller aController){
	this.CALENDAR_ID = actBoxID;
	lstChkLn = new ArrayList<CheckLine>() ;
	this.aController = aController; 
}
}