package pfc.ime.gtdmanager.main;

import pfc.ime.gtdmanager.controller.Controller;

import com.juntando_tudo.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeDialog extends DialogFragment {

	private Controller aController;
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
		
		aController = (Controller) (getActivity()).getApplicationContext();
		LayoutInflater liAdd = LayoutInflater.from(getActivity());
		AlertDialog.Builder builderSingle = new AlertDialog.Builder(
                getActivity());
        builderSingle.setIcon(R.drawable.ic_launcher);
        builderSingle.setTitle("Select One Name:-");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.select_dialog_item);
        arrayAdapter.addAll(aController.getAllListsName());
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int listNamePosition) {
                        dialog.dismiss();
                    }
                });

        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        int position =  getArguments().getInt("position");
                        
                        AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                getActivity());
                        aController.forwardChecklineToAnotherList(position, which);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Your Selected Item is");
                        builderInner.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builderInner.show();
                    }
                });
        return builderSingle.create();

	}
}
