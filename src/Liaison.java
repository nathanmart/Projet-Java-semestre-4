//Format de donnée pour une liaison entre deux Noeuds
public class Liaison {
    // Noeud rataché
    final private Noeud noeud_connecte;
    // Coût du déplacement jusqu'à ce nœud
    final private int cout;

    // Initialisation
    public Liaison(Noeud noeud_connecte, int cout){
        this.noeud_connecte = noeud_connecte;
        this.cout = cout;
    }

    public int getCout() {
        return cout;
    }

    public Noeud getNoeud_connecte() {
        return noeud_connecte;
    }
}
