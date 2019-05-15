package formation.exemple.helloplumdatabase3;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContentValuesArrayAdapter extends ArrayAdapter<ContentValues> {

    public ContentValuesArrayAdapter (Context context, ArrayList<ContentValues> rows) {
        super(context, 0, rows);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ContentValues produit = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row,
                    parent, false);
        }

        // Lookup view for data population
        TextView txtNom = (TextView) convertView.findViewById(R.id.txtNom);
        TextView txtPrix = (TextView) convertView.findViewById(R.id.txtPrix);

        // Populate the data into the template view using the data object
        txtNom.setText(produit.nom);
        txtPrix.setText(produit.prix + " €");

        // Return the completed view to render on screen
        return convertView;
    }
}
