package formation.exemple.hellomessagedialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import plum.widget.MessageDialog;

public class  MainActivity extends AppCompatActivity implements
        View.OnClickListener {
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
        MessageDialog.show(this,"Hello...","Fermer" );
    }
}
