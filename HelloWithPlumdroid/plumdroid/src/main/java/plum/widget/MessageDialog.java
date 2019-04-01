package plum.widget;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Context;
import android.widget.TextView;

public class MessageDialog implements
DialogInterface.OnClickListener{
	private String message; private String caption;
	private AlertDialog alerte;private TextView view;
	private Context context;
	
	private MessageDialog (Context context,String message, String caption) {
		this.message=message;
		this.caption="Ok"; if(caption!=""){ this.caption=caption;}
		this.context=context;

		//AlertDialog
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(message);
        AlertDialog alerte=builder.create();

		//AlertDialog alerte=new AlertDialog.Builder(context).create();
		//alerte.setMessage(message);
		
		alerte.setButton(DialogInterface.BUTTON_NEUTRAL,this.caption,this);
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
		MessageDialog myMessage=new MessageDialog(context,message, caption);
		}
	
	//// ------ évènements ////
	public void onClick(DialogInterface dialog, int button) {
			//alerte.cancel();
		}	
}
