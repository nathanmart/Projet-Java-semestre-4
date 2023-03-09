// Format de donnée qui stocke une partie de chemin avec sa distance
public class Connexion {
    // Cout, somme des Noeuds (cases) parcouru
    private final int cout;
    // Noeud de ratachement
    private final Noeud noeud;

    // Initialisation
    public Connexion(int cout, Noeud noeud){
        this.cout = cout;
        this.noeud = noeud;
    }

    public int getCout() {
        return cout;
    }

    public Noeud getNoeud() {
        return noeud;
    }
}
