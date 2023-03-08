public class Connexion {
    int cout;
    Noeud noeud;
    boolean etat; //True->normal, False->Pas de retour sur ce tuple

    public Connexion(int cout, Noeud noeud){
        this.cout = cout;
        this.noeud = noeud;
        this.etat = true;

    }
    public Connexion(boolean etat){
        this.etat = etat;
    }
}
