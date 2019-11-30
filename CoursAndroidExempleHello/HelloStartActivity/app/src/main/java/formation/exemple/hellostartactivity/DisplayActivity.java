package formation.exemple.hellostartactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Récupérer les données fournies à l'Activity par putExtra
        String text=getIntent().getStringExtra("mainactivity.texte");

        int val=getIntent().getIntExtra("mainactivity.valeur", -1);

        //Affichage sur le formulaire
        setContentView(R.layout.activity_display);

        TextView myTexte=(TextView)findViewById(R.id.mytexte);
        myTexte.setText(text+" ("+val+")");

        //onClick "Fermer"
        Button buttonClose=(Button) findViewById(R.id.close);
        buttonClose.setOnClickListener(this);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        finish();
    }
}