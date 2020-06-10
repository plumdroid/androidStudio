package plum.widget;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.widget.TextView;

public class MessageDialog implements
DialogInterface.OnClickListener{
	private String message;
	private AlertDialog alerte;
	private TextView view;
	private Context context;

	private OnClickMessageDialogListener callBack =null;
	
	private  MessageDialog (Context context, String message,
							String captionGauche, String captionDroit,
							OnClickMessageDialogListener callBack,
							boolean isCanceledOnTouchOutside, boolean isCanceledOnBackButton) {
		this.message=message;
		this.context=context;
		this.callBack = callBack;



		//AlertDialog
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
        AlertDialog alerte=builder.create();

		//AlertDialog alerte=new AlertDialog.Builder(context).create();
		//alerte.setMessage(message);

		String captionGaucheLocal ="Ok"; if(captionGauche!=""){ captionGaucheLocal=captionGauche;}
		alerte.setButton(DialogInterface.BUTTON_NEUTRAL,captionGaucheLocal,this);

		if ( captionGauche != null)
		alerte.setButton(DialogInterface.BUTTON_NEGATIVE,captionDroit,this);

		alerte.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
		alerte.setCancelable(isCanceledOnBackButton);
		alerte.show();



		}
	/*AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("hello");
            builder.setMessage(message);
	//builder.setItems(items,this);
	//builder.setSingleChoiceItems(items,indexSelected, this);
	AlertDialog alerte=builder.create();
            alerte.show();*/
	public static void show(Context context,String message, String caption){
		MessageDialog myMessage=new MessageDialog(context,message,
				caption, null,null,
				true,true);
	}

	public static void show(Context context,String message, String caption,
							OnClickMessageDialogListener callBack ){
		MessageDialog myMessage=new MessageDialog(context,message,
				caption, null, callBack,
				true,true);
	}

	public static void show(Context context,String message, String captionGauche, String captionDroit){
		MessageDialog myMessage=new MessageDialog(context,message,
				captionGauche,captionDroit, null,
				true,true);
	}

	public static void show(Context context,String message, String captionGauche, String captionDroit,
							OnClickMessageDialogListener callBack){
		MessageDialog myMessage=new MessageDialog(context,message,
				captionGauche,captionDroit, callBack,
				true,true);
	}

	public static void showModal(Context context,String message, String caption){
		MessageDialog myMessage=new MessageDialog(context,message,
				caption, null,null,
				true,true);
	}

	public static void showModal(Context context,String message, String caption,
								 OnClickMessageDialogListener callBack ){
		MessageDialog myMessage=new MessageDialog(context,message,
				caption, null, callBack,
				false,false);
	}

	public static void showModal(Context context,String message, String captionGauche, String captionDroit){
		MessageDialog myMessage=new MessageDialog(context,message,
				captionGauche,captionDroit, null,
				false,false);
	}

	public static void showModal(Context context,String message, String captionGauche, String captionDroit,
								 OnClickMessageDialogListener callBack){
		MessageDialog myMessage=new MessageDialog(context,message,
				captionGauche,captionDroit, callBack,
				false,false);
	}
	//// ------ évènements ////
	public void onClick(DialogInterface dialog, int button) {
		char buttonChoice = '.';
		switch (button) {
			case DialogInterface.BUTTON_NEUTRAL:
				buttonChoice = 'G';
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				buttonChoice = 'D';
		}

			if (callBack != null){
				callBack.onClickMessageDialog(this, buttonChoice);
			}
	}

	/// ----- interface Listener : public static interface Listener  ////
	public static interface  OnClickMessageDialogListener {
		public abstract void onClickMessageDialog( MessageDialog messageDialog, char buttonChoice);}
}
