package pfc.ime.gtdmanager.main;

import java.util.ArrayList;
import java.util.List;

import pfc.ime.gtdmanager.swipelistview.ItemAdapter;
import pfc.ime.gtdmanager.swipelistview.ItemRow;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.juntando_tudo.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;

public class Lista extends Activity {

	SwipeListView swipelistview;
	ItemAdapter adapter;
	List<ItemRow> itemData;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        swipelistview=(SwipeListView)findViewById(R.id.example_swipe_lv_list); 
        itemData=new ArrayList<ItemRow>();
        adapter=new ItemAdapter(this,R.layout.custom_row,itemData);
        
     
        
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
        swipelistview.setAnimationTime(500); // Animation time
        swipelistview.setSwipeOpenOnLongPress(true); // enable or disable SwipeOpenOnLongPress
	
        swipelistview.setAdapter(adapter);
        
        
        for(int i=0;i<10;i++)
        {
        	itemData.add(new ItemRow("Swipe BLALBABLA Item"+i ));
        	
        }
        
        adapter.notifyDataSetChanged();
    
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
