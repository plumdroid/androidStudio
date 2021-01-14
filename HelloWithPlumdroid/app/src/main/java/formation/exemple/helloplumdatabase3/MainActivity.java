package formation.exemple.helloplumdatabase3;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import plum.webservice.norest.PlumDataBase;
import plum.webservice.norest.PlumDataBaseException;
import plum.webservice.norest.PlumDataBaseReponse;
import plum.widget.MessageDialog;

public class MainActivity extends AppCompatActivity
        implements MessageDialog.OnClickMessageDialogListener {
    PlumDataBase webdata = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("hello.info", "hello.onCreate");

        //Création de PlumDataBase : paramètre=URL du webservice
        //le localhost avec AVD android est http://10.0.2.2/
        //"http://10.0.2.2:8080/PlumWebServiceDb/www/e/norest/"

        webdata = new PlumDataBase("https://suivistage.boonum.fr/e/norest/");

        //-- Contact --
        webdata.contact(
                new PlumDataBase.OnReponseListener() {
                    @Override
                    public void onReponseSucceed(PlumDataBaseReponse reponse) {
                        String message = "OK.CONTACT.." + "etat:" + reponse.etat;
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    }
                    public void onReponseError(PlumDataBaseReponse reponse) {
                        String message = "ERROR CONTACT.." + "etat:" + reponse.etat;
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    }
                },
                new PlumDataBase.OnExceptionListener() {
                    @Override
                    public void onException(PlumDataBaseException error) {
                        Context c = getApplicationContext();
                        String message = "...EXCEPTION CONTACT..." + error.toString();
                        Toast.makeText(c, message, Toast.LENGTH_LONG).show();

                        Log.i("CONTACT...", message);
                    }
                });

        ExecuteListener e = new ExecuteListener(this);
        Button button_execute = (Button) findViewById(R.id.button_execute);
        button_execute.setOnClickListener(e);

        QueryListener q = new QueryListener(this);
        Button button_query = (Button) findViewById(R.id.button_query);
        button_query.setOnClickListener(q);

        Button button_authentification = (Button) findViewById(R.id.button_authentification);
        AuthentificationListener auth = new AuthentificationListener(this);
        button_authentification.setOnClickListener(auth);

    }

    protected void onStart() {
        super.onStart();

        // D�marrer la cam�ra ici
        //-permet restart de la cam�ra apr�s onPause()
        Log.i("hello.info", "hello.onStart");

    }


    protected void onResume() {
        super.onResume();
        Log.i("hello.info", "hello.onResume");


    }


    protected void onPause() {
        super.onPause();
        Log.i("hello.info", "hello.onPause");

    }

    protected void onStop() {
        super.onStop();
        Log.i("hello.info", "hello.onStop");
    }

    protected void onRestart() { // si onStop
        super.onRestart();
        Log.i("hello.info", "hello.onRestart");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("hello.info", "hello.onDestroy");
    }


    @Override
    public void onClickMessageDialog(MessageDialog messageDialog, char button) {
        Toast.makeText(this, "click" + button, Toast.LENGTH_LONG).show();


    }

    public class AuthentificationListener implements View.OnClickListener,
            PlumDataBase.OnExceptionListener,
            PlumDataBase.OnReponseListener {
        Context context;

        public AuthentificationListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            String sql;
            EditText edit_sql = (EditText) findViewById(R.id.edit_sql);
            sql = edit_sql.getText().toString();
            webdata.authentification("@hsggn7hhhHHHHTTT777hhhTTFQ@", "@@", this, this);
        }

        @Override
        public void onReponseError(PlumDataBaseReponse reponse) {
            String message = "ECHEC AUTHENTIFICATION..." + reponse.message;
            MessageDialog.show(context, message, "Fermer");
            Log.i("Error contact", message);
        }

        @Override
        public void onException(PlumDataBaseException e) {
            String message = "EXCEPTION AUTHENTIFICATION..." + e.toString();
            MessageDialog.show(context, message, "Fermer");
            Log.i("Error contact", message);
        }

        @Override
        public void onReponseSucceed(PlumDataBaseReponse reponse) {

            String message = "AUTHENTIFICATION OK..."
                    .concat("::Token = " + reponse.secure_token);

            MessageDialog.show(context, message, "Fermer");
        }
    }

    public class ExecuteListener implements View.OnClickListener,
            PlumDataBase.OnExceptionListener,
            PlumDataBase.OnReponseListener {
        Context context;

        public ExecuteListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            String sql;
            EditText edit_sql = (EditText) findViewById(R.id.edit_sql);
            sql = edit_sql.getText().toString();
            webdata.execute(sql, this, this);
        }

        @Override
        public void onReponseError(PlumDataBaseReponse reponse) {
            String message = "ERROR EXECUTE..." + reponse.message;
            MessageDialog.show(context, message, "Fermer");
            Log.i("Error Execute", message);
        }

        @Override
        public void onReponseSucceed(PlumDataBaseReponse reponse) {
            String sql;
            EditText edit_sql = (EditText) findViewById(R.id.edit_sql);
            sql = edit_sql.getText().toString();

            String message = "";
            if (reponse.pdo.error == 0) {
                message = "EXECUTE OK..."
                        .concat("::SQL = " + sql)
                        .concat("::rowCount = " + reponse.pdo.rowCount);
            } else {
                message = "::ERREUR EXECUTE SQL..."
                        .concat("::SQL = " + sql)
                        .concat("::erreur SQL = " + reponse.pdo.errorInfo);

            }

            MessageDialog.show(context, message, "Fermer");
        }

        @Override
        public void onException(PlumDataBaseException e) {
            String message = "EXCEPTION EXECUTE..." + e.toString();
            MessageDialog.show(context, message, "Fermer");
            Log.i("Error Execute", message);
        }
    }


    public class QueryListener implements View.OnClickListener,
            PlumDataBase.OnExceptionListener,
            PlumDataBase.OnReponseListener {
        Context context;

        public QueryListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            String sql;
            EditText edit_sql = (EditText) findViewById(R.id.edit_sql);
            sql = edit_sql.getText().toString();
            webdata.query(sql, this, this);
        }

        @Override
        public void onReponseError(PlumDataBaseReponse reponse) {
            String message = "ERROR  QUERY..." + reponse.message;
            MessageDialog.show(context, message, "Fermer");
            Log.i("Error query", message);
        }

        @Override
        public void onReponseSucceed(PlumDataBaseReponse reponse) {
            String sql;
            EditText edit_sql = (EditText) findViewById(R.id.edit_sql);
            sql = edit_sql.getText().toString();

            String message;
            Log.i("handle_query1", this.toString());
            // Uniquement en phase de test !!
            if (reponse.pdo.error != 0) {
                message = "::ERREURQUERY..."
                        .concat("::SQL = " + sql)
                        .concat("::erreur SQL = " + reponse.pdo.errorInfo);
                MessageDialog.show(context, message, "Fermer");
            }

            MessageDialog.show(context, "rwocount=" + reponse.pdo.rowCount,
                    "Fermer");


            ArrayList<ContentValues> rows = reponse.pdo.listContentValues;

            if (reponse.pdo.rowCount == 0) {
                Toast.makeText(getApplicationContext(),
                        "QUERY : table vide", Toast.LENGTH_LONG).show();
            }

            // Affichage du ListView
            //-- Lier les données avec l'adapter de Type ProduitArrayAdapter
            ContentValuesArrayAdapter ad =
                    new ContentValuesArrayAdapter(context, rows);

            //-- L'adaptateur Sert de source de données au ListView
            ListView lv = (ListView) findViewById(R.id.list);
            lv.setAdapter(ad);
        }

        @Override
        public void onException(PlumDataBaseException e) {
            String message = "EXCEPTION QUERY..." + e.toString();
            MessageDialog.show(context, message, "Fermer");
            Log.i("Error query", message);
        }
    }
}