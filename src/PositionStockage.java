//Stocke l'emplacement d'une Liaison stocké dans un tableau "etape"
public class PositionStockage {
    //Position
    private int ligne;
    private int colonne;

    // Initialisation
    public PositionStockage(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }
}
