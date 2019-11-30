package formation.exemple.hellostartactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button buttonStart=(Button) findViewById(R.id.startActivity);
        Button buttonClose=(Button) findViewById(R.id.close);

        buttonStart.setOnClickListener(this);
        buttonClose.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.startActivity :

                EditText editTexte=(EditText)findViewById(R.id.editTexte);
                String myTexte=editTexte.getText().toString();

                int myValeur = myTexte.length();

                //créer une instance de "DisplayActivity"
                Intent intent = new Intent(this,DisplayActivity.class);

                //données fournies à l'Activity "DisplayActivity"
                intent.putExtra("mainactivity.texte", myTexte);//un texte
                intent.putExtra("mainactivity.valeur", myValeur);//un nombre

                //Démarrer l'activity DisplayActivity
                startActivity(intent);

                break;

            case R.id.close:
                finish();
                break;
        }
    }
}