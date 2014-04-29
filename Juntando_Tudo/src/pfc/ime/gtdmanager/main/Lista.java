package pfc.ime.gtdmanager.main;

import pfc.ime.gtdmanager.controller.Controller;
import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.juntando_tudo.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.NavUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Lista extends Activity {

	public static SwipeListView swipelistview;
	Controller aController; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);
       
        aController = (Controller ) getApplicationContext();
        setSwipeListView();
        
        aController.setListView(swipelistview);
        
        
        // Controller.LoadActionBox();
//        aController.populaActionBoxTeste();
//        Toast.makeText(this , String.valueOf(aController.getId() ), Toast.LENGTH_SHORT).show();
        aController.setAdapter(this);
        
    
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
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
		case R.id.add:
			add_method();

		}
		return super.onOptionsItemSelected(item);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista, menu);
        getActionBar().setTitle(aController.getActionBoxName());
        return true;
    }
    @Override
    protected void onStop(){
    	super.onStop();
//    	Controller aController = (Controller) getApplicationContext();
    	aController.persist();
    }
    private boolean add_method(){
		// get prompts.xml view
		LayoutInflater liAdd = LayoutInflater.from(this);
		View promptsView = liAdd.inflate(R.layout.add, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
		final EditText userInput =  (EditText) promptsView.findViewById(R.id.etItem_new);
		userInput.requestFocus();
		alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// get user input and set it to result
				// edit text
				String result = String.valueOf(userInput.getText());
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
				aController.addCheckLine(result);
				//aController.persist();
				//aController.loadActionBox(); 
				
			}
		});
		alertDialogBuilder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
			}
		});



		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// settin position
		//WindowManager.LayoutParams alDlgParemters = alertDialog.getWindow().getAttributes();
		//	alDlgParemters.gravity = Gravity.TOP;
		//	alDlgParemters.y = 100;


		// forcing showing soft input
		alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

		// show it
		alertDialog.show();
		return true;
		
	}
    
    public void setSwipeListView(){
    	swipelistview=(SwipeListView)findViewById(R.id.example_swipe_lv_list);
        
        
     
        
        swipelistview.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
            }

            @Override
            public void onListChanged() {
            }

            @Override
            public void onMove(int position, float x) {
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
                Log.d("swipe", String.format("onStartOpen %d - action %d", position, action));
            }

            @Override
            public void onStartClose(int position, boolean right) {
                Log.d("swipe", String.format("onStartClose %d", position));
            }

            @Override
            public void onClickFrontView(int position) {
                Log.d("swipe", String.format("onClickFrontView %d", position));
                
             
                //swipelistview.openAnimate(position); //when you touch front view it will open
               
             
            }

            @Override
            public void onClickBackView(int position) {
                Log.d("swipe", String.format("onClickBackView %d", position));
                
                swipelistview.closeAnimate(position);//when you touch back view it will close
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions) {
            	
            }

        });
        
        //These are the swipe listview settings. you can change these
        //setting as your requirement 
        swipelistview.setSwipeMode(SwipeListView.SWIPE_ACTION_NONE); // there are five swiping modes
        swipelistview.setSwipeActionLeft(SwipeListView.SWIPE_MODE_LEFT); //there are four swipe actions 
        swipelistview.setSwipeActionRight(SwipeListView.SWIPE_MODE_NONE);
        swipelistview.setOffsetLeft(convertDpToPixel(80f)); // left side offset
        swipelistview.setOffsetRight(convertDpToPixel(0f)); // right side offset
        swipelistview.setAnimationTime(64); // Animation time
        swipelistview.setSwipeOpenOnLongPress(true); // enable or disable SwipeOpenOnLongPress
	
       
    }
}
