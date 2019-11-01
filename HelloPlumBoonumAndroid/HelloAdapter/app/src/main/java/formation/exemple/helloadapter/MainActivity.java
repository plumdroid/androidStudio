package formation.exemple.helloadapter;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /** Called when the activity is first created. */
    private ListView lv;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //-- Liste des produits :  ArrayList
        ArrayList<Produit> lesProduits = getProduits();

        //-- Lier les données avec l'adapter de Type ProduitArrayAdapter
        ProduitArrayAdapter arrayAdapterProduit =
                new ProduitArrayAdapter(this, lesProduits);

        //-- L'adaptateur Sert de source de données au ListView
        lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(arrayAdapterProduit );

    }

    private ArrayList<Produit> getProduits(){

        ArrayList<Produit> lesproduits = new ArrayList<Produit>();


        String[] produits = {
                "Calculette", "Stylo", "Crayon", "Buvard", "Agrapheuse",
                "Chiffon", "Chemise cartonnée", "Agraphe", "Coupe papier", "Armoire",
                "Chaise", "Bureau", "Imprimante HP", "Imprimante Canon",
                "Cartouche XR34","Ciseau", "Cutter T1", "Cutter T2", "Cutter T3",
                "Cutter T4","Boite de tangement 2", "Boite de tangement 2", "Boite de tangement 3",
                "Boite de tangement 4", "Boite de tangement 5",
                "Crayon 4 couleurs", "Crayon papier BB1",
                "Crayon papier BB2", "Crayon papier BB3", "Papier Crépon",
                "Peinture rouge", "Peinture verte", "Peinture jaune",
                "Peinture bleue", "Peinture noire"};

        for( String p : produits ){
            int prix = (int)(Math.random()*300) + 2;
            int qte =  (int)(Math.random()*20);
            lesproduits.add(new Produit(p, prix, qte));
        }

        return lesproduits;

    }
}
