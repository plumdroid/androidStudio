package formation.exemple.hellomessagedialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import plum.widget.MessageDialog;

public class  MainActivity extends AppCompatActivity implements
        View.OnClickListener, MessageDialog.OnClickMessageDialogListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** écouteur */
        Button monBouton=(Button) findViewById(R.id.button1);
        monBouton.setOnClickListener(this);
    }

    //// Evènements ////
    public void onClick(View v) {

        MessageDialog.show(this,
                "Testez les boutons Gauche et Droite, en cliquant sur OUI ou NON",
                "OUI","NON", this);

    }

    @Override
    public void onClickMessageDialog(MessageDialog messageDialog, char c) {
        switch (c) {
            case 'G':
                MessageDialog.show(this,"click Bouton Gauche","Fermer" );
                break;
            case 'D':
                MessageDialog.show(this,"click Bouton Droit","Fermer" );
                break;
        }
    }
}
