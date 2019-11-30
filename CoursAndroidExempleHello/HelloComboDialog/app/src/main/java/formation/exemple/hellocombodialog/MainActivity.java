package formation.exemple.hellocombodialog;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import plum.widget.ComboDialog;

public class MainActivity extends AppCompatActivity implements
        ComboDialog.OnClickComboDialogListener {
    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Le 'ComboDialog' affichera dans myTextViewItem le libellé (item) choisi par l'utilisateur*/

        TextView myTextViewItem = (TextView)findViewById( R.id.item );

        final CharSequence[] items = {"Rouge","Vert","Bleu"};
        final CharSequence[] values = {"1","2","3"};

        ComboDialog comboCouleur = new ComboDialog( "Choisir une couleur",
                                                     items,
                                                     values,
                                                     myTextViewItem,
                                             this );

        //Mise en place d'un écouteur sur ComboDialog
        comboCouleur.setOnClickComboDialogListener(this);

        //--- sans support View
        ComboDialog c;
        CharSequence[] itemsc={"a","b"};
        CharSequence[] valuesc={"a","b"};
        c=new ComboDialog("titre", itemsc, valuesc, null, this);
        c.setOnClickComboDialogListener(this);
        c.show();

    }


    // Le choix dans ComboDialog déclenche un clic
    public void onClickComboDialog( ComboDialog comboDialog )
    {
        String value = (String) comboDialog.value( comboDialog.getIndexSelected());
        String item = (String) comboDialog.item( comboDialog.getIndexSelected());

        Toast toast = Toast.makeText(getApplicationContext(),
                value+":" + item,
                Toast.LENGTH_LONG);
        toast.show();
    }
}