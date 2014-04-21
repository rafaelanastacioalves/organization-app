package pfc.ime.gtdmanager.swipelistview;

import java.util.List;

import pfc.ime.gtdmanager.controller.Controller;
import pfc.ime.gtdmanager.model.CheckLine;


import com.fortysevendeg.swipelistview.SwipeListView;
import com.juntando_tudo.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ItemAdapter extends ArrayAdapter<CheckLine> {

	List<CheckLine>   data; 
	Context context;
	int layoutResID;
	Controller aController; 
	private CheckLine itemdata;

public ItemAdapter(Context context, int layoutResourceId,List<CheckLine> data) {
	super(context, layoutResourceId, data);
	
	this.data=data;
	this.context=context;
	this.layoutResID=layoutResourceId;
	aController = (Controller) context.getApplicationContext();
//	aController.

	// TODO Auto-generated constructor stub
}
 
@Override
public View getView(int position, View convertView, ViewGroup parent) {
	
	NewsHolder holder ;//=null;
	   View row = convertView;
	    
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResID, parent, false);
            
            holder = new NewsHolder();
           
            holder.itemText = (TextView)row.findViewById(R.id.example_itemname);
            holder.itemText.setTextAppearance(context, R.style.MyListTitle);
            holder.chk=(CheckBox)row.findViewById(R.id.example_image);
            holder.button1=(ImageButton)row.findViewById(R.id.swipe_button1);
            holder.button2=(Button)row.findViewById(R.id.swipe_button2);
            holder.button3=(Button)row.findViewById(R.id.swipe_button3);
            row.setTag(holder);
        }
        else
        {
            holder = (NewsHolder)row.getTag();
        }
        
        itemdata = data.get(position);
        
        holder.chk.setTag(position);
        holder.button1.setTag(position);
        
        holder.itemText.setText(itemdata.getText());
        holder.chk.setOnClickListener(chkClickListener);
        if(itemdata.isChecked() ) {
        	holder.chk.setChecked(true);
        	holder.itemText.setTextAppearance(context, R.style.MyListTitleChecked);
		} else {
			holder.chk.setChecked(false);
			holder.itemText.setTextAppearance(context, R.style.MyListTitle);		}
        
        
        
        
        
        //holder.icon.setImageDrawable(itemdata.getIcon());
      
        holder.button1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Button 1 Clicked",Toast.LENGTH_SHORT).show();
				int position = (Integer) v.getTag();
				aController.deleteChecklineAt(position);
				
			}
		});
        
		 holder.button2.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "Button 2 Clicked",Toast.LENGTH_SHORT).show();
					}
				});
		 
		 holder.button3.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "Button 3 Clicked",Toast.LENGTH_SHORT);
				}
			});
        
        
        return row;
	
}
private OnClickListener chkClickListener = new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		CheckBox chk = (CheckBox) v;
		View vParent =  (View) v.getParent();
		TextView tbTxt =  (TextView) vParent.findViewById(R.id.example_itemname);
		int position =  (Integer) v.getTag();
//		CheckLine chlnTemp =  (CheckLine) chk.getTag();
//		CheckLine chlnTemp = actBxInbox.getCheckLineByID(chlnID);
		if(chk.isChecked()) {
			
			tbTxt.setTextAppearance(context, R.style.MyListTitleChecked);
			Toast.makeText(context, "Checbox de ID " + String.valueOf(data.get(position).getId()) + " marcado! Seu texto Ž: " + tbTxt.getText() , Toast.LENGTH_SHORT).show();
			data.get(position).setChecked(true);
//			if(!selecionados.contains(chlnID))
//				selecionados.add(chlnID);
		} else {
			tbTxt.setTextAppearance(context, R.style.MyListTitle);
//			tbTxt.setTextColor(Color.BLACK);
			Toast.makeText(context, "Checbox de ID " + String.valueOf(data.get(position).getId()) + " desmarcado!Seu texto Ž: " + tbTxt.getText(), Toast.LENGTH_SHORT).show();
			data.get(position).setChecked(false);
//			if(selecionados.contains(chlnID))
//				selecionados.remove(Integer.valueOf(chlnID));
		}
//		actBxInbox.persistCheckLine(chlnTemp, dbTeste);
	}

};


static class NewsHolder{
	
	TextView itemText;
	CheckBox chk;
	ImageButton button1;
	Button button2;
	Button button3;
	
	}
	
	
}




