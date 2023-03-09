import java.util.ArrayList;

// Stocke un noeud, c'est à dire une case
// Contient aussi une liste de ses connexions et d'autres infos
public class Noeud {
    // Hauteur de la case
    private final int hauteur;
    // Si un retour de calcul à partir de cette case est posible
    private boolean retour_possible;
    // Position de la case (version 1 ligne)
    private final int position;
    // Liste des liaison de cette case avec les autres cases
    private ArrayList<Liaison> liste_liaison;

    // Initialisation
    public Noeud(int hauteur, int position){
        this.hauteur = hauteur;
        this.liste_liaison = new ArrayList<Liaison>();
        retour_possible = true;
        this.position = position;
    }

    public void ajouter_liaison(Noeud noeud, int cout){
        liste_liaison.add(new Liaison(noeud, cout));
    }

    public void afficher_liaison(){
        for (int i = 0; i < liste_liaison.size(); i++) {
            Liaison liaison = liste_liaison.get(i);
            System.out.println(liaison.getNoeud_connecte().hauteur + " " + liaison.getCout());
        }
        System.out.println();
    }

    public ArrayList<Liaison> getListe_liaison(){
        return this.liste_liaison;
    }

    public int getPosition(){
        return this.position;
    }

    public int getHauteur(){
        return this.hauteur;
    }

    public boolean getRetourPossible(){
        return this.retour_possible;
    }

    public void setRetour_possible(boolean etat){
        this.retour_possible = etat;
    }

}
