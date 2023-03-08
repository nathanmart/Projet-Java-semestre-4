import java.util.ArrayList;

public class Noeud {

    int hauteur;
    ArrayList<Liaison> liste_liaison;
    boolean retour_possible;
    int postion;
    public Noeud(int hauteur, int postion){
        this.hauteur = hauteur;
        this.liste_liaison = new ArrayList<Liaison>();
        retour_possible = true;
        this.postion = postion;
    }

    public void ajouter_liaison(Noeud noeud, int cout){
        liste_liaison.add(new Liaison(noeud, cout));
    }

    public void afficher_liaison(){
        for (int i = 0; i < liste_liaison.size(); i++) {
            Liaison liaison = liste_liaison.get(i);
            System.out.println(liaison.noeud_connecte.hauteur + " " + liaison.cout);
        }
        System.out.println();
    }

}
