package pfc.ime.gtdmanager.otherListsView;

import java.util.List;

import pfc.ime.gtdmanager.controller.Controller;
import pfc.ime.gtdmanager.model.ActionBox;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.juntando_tudo.R;

public class OtherListAdapter extends ArrayAdapter<ActionBox> {

	List<ActionBox> data;
	Context context;
	int layoutResID;
	Controller aController;
	private ActionBox itemdata;

	public OtherListAdapter(Context context, int layoutResourceId,
			List<ActionBox> data) {
		super(context, layoutResourceId, data);

		this.data = data;
		this.context = context;
		this.layoutResID = layoutResourceId;
		aController = (Controller) context.getApplicationContext();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		NewsHolder holder;// =null;
		View row = convertView;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResID, parent, false);

			holder = new NewsHolder();

			holder.itemText = (TextView) row
					.findViewById(R.id.example_itemname);
			holder.itemText.setTextAppearance(context, R.style.MyListTitle);
			holder.button1 = (ImageButton) row.findViewById(R.id.swipe_delete);
			holder.button2 = (ImageButton) row.findViewById(R.id.swipe_share);
			holder.button3 = (Button) row.findViewById(R.id.swipe_button3);
			row.setTag(holder);
		} else {
			holder = (NewsHolder) row.getTag();
		}

		itemdata = data.get(position);

		holder.button1.setTag(position);
		holder.button2.setTag(position);
		holder.button3.setTag(position);

		holder.itemText.setText(itemdata.getName());

		holder.button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Button 1 Clicked", Toast.LENGTH_SHORT)
						.show();
				int position = (Integer) v.getTag();
				aController.deleteActionBoxAt(position);

			}
		});

		holder.button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Button 2 Clicked", Toast.LENGTH_SHORT)
						.show();
				int position = (Integer) v.getTag();
				// aController.shareChecklineAt(position,(Activity)getContext());
			}
		});

		holder.button3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(context, "Button 3 Clicked", Toast.LENGTH_SHORT);
				// DialogFragment dfChange = new ChangeDialog();
				// dfChange.show(((Activity)getContext()).getFragmentManager() ,
				// "change");
			}
		});

		return row;

	}

	@Override
	public ActionBox getItem(int position) {
		return data.get(position);
	}

	static class NewsHolder {

		TextView itemText;
		ImageButton button1;
		ImageButton button2;
		Button button3;

	}

}
