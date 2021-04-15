package plum.widget;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;

public class ComboDialog implements View.OnClickListener,
DialogInterface.OnClickListener{
	private CharSequence items[], values[];
	private int indexSelected; 
	private OnClickComboDialogListener callback;
	private AlertDialog alerte;private TextView view;
	private String title;
	private Context context;

	public ComboDialog (String title, CharSequence items[], CharSequence values[],
			TextView view, Context context) {
		this.title = title;
		this.items=items; this.values=values;
		this.callback=null;
		this.indexSelected=-1;
		this.view=view;
		this.context = context;

		if ( view !=null ) view.setOnClickListener(this);
		}

	public ComboDialog (String title,
						ArrayList<String> items,
						ArrayList<String> values,
						ArrayList exclude,
						TextView view, Context context) {
		this.title = title;

		int sizeExclude = 0;
		if (exclude != null) sizeExclude=exclude.size();

		this.items = new CharSequence[items.size()- sizeExclude];
		this.values =new CharSequence[items.size()- sizeExclude];

		int j=0;
		for( int i = 0; i< items.size();i++){
			if ( existeValueInArrayList (values.get(i), exclude)){
				continue;
			}
			this.items[j] = items.get(i);
			this.values[j] = values.get(i);
			j++;
		}

		this.callback=null;
		this.indexSelected=-1;
		this.view=view;
		this.context = context;

		if ( view !=null ) view.setOnClickListener(this);
	}

	private boolean existeValueInArrayList (String val, ArrayList<String> arr){
		for ( String str : arr){
			if ( str.equals(val)) return true;
		}
		return false;
	}

	public void show(){
		//AlertDialog
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		//builder.setItems(items,this);
		builder.setSingleChoiceItems(items,indexSelected, this);

		alerte=builder.create();

		alerte.show();
	}
	
	public int getIndexSelected() {return indexSelected;}
	
	public int getCompteur() { return items.length;}
	
	public CharSequence item(int index) { return items[index];}
	
	public CharSequence value(int index) { return values[index];}
	
	public void selected(int index){indexSelected=index;}

	public void selectedValue ( String value ){
		for (int i = 0; i < values.length; i++) {
			String val = (String) values[i];
			if ( value.equals(val)) {
				indexSelected = i;
				return;
			}
        indexSelected = -1;
		}
	}
	
	public void setOnClickComboDialogListener(OnClickComboDialogListener onClickListener)
	{ callback=onClickListener;}
	
	//// ------ évènements ////
	public void onClick(DialogInterface dialog, int item) {
		this.indexSelected=item;
		alerte.cancel();
		if ( view != null) view.setText(items[item]);
		if (callback!=null) {callback.onClickComboDialog( this );}
	}
	public void onClick(View v) {
		this.show();
	}


	/// ----- interface Listener : public static interface Listener  ////	
	public static interface  OnClickComboDialogListener {
		public abstract void onClickComboDialog( ComboDialog comboDialog);}
}
