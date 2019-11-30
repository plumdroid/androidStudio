package formation.exemple.helloexercicegererliste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> monArrayList = new ArrayList<String>();
        monArrayList.add("hello1");
        arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item,
                monArrayList);

        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(arrayAdapter );
        lv.setOnItemClickListener(this);

        arrayAdapter.add("hello2");
        arrayAdapter.add("hello3");


        // -- pas utile ici
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> ad, View v, int pos, long id) {
        // When clicked, show a toast with the TextView text
        String  itemValue = (String) ad.getItemAtPosition(pos);
         arrayAdapter.remove(itemValue);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Position : " + pos
                        +" Item : "+ itemValue,
                Toast.LENGTH_LONG);
        toast.show();
    }
}
