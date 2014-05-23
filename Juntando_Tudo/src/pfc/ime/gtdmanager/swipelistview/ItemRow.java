package pfc.ime.gtdmanager.swipelistview;

import android.widget.CheckBox;

public class ItemRow {

	String itemName;
	CheckBox icon;
	
	
	
	public ItemRow(String itemName) {
		super();
		this.itemName = itemName;
		
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public CheckBox getIcon() {
		return icon;
	}
	public void setIcon(CheckBox icon) {
		this.icon = icon;
	}

	
	
}
