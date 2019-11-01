package formation.exemple.helloadapter;

public class Produit {
    private String nom;
    private double prix;
    private int qte;

    public Produit (String nom, double prix, int qte) {
        this.nom = nom;
        this.prix = prix;
        this.qte = qte;
    }

    public String getNom(){
        return nom;
    }

    public double getPrix(){
        return prix;
    }

    public int getQte(){
        return qte;
    }

    public void destocker(){
        qte = qte - 1;
    }
}
