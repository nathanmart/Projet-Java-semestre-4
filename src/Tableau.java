public abstract class Tableau {
    protected int[][] tableau;

    // Initialisation
    public Tableau(int nb_ligne, int nb_colonne){
        this.tableau = new int[nb_ligne + 2][nb_colonne + 2];
        // Ajout des -1 autour
        for (int i = 0; i < nb_ligne + 2; i++) {
            for (int j = 0; j < nb_colonne + 2; j++) {
                if (i == 0 || i == nb_ligne + 1 || j == 0 || j == nb_colonne + 1){
                    this.tableau[i][j] = -1;
                }
            }
        }
    }

    // Affiche les valeurs du tableau
    public void afficher_tableau(){
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau[i].length; j++) {
                System.out.print(tableau[i][j] + " ");
            }
            System.out.println("");
        }
    }

    // Renvoie la valeur du tableau pour un index donné
    public int getTableau(int i, int j) {
        return tableau[i][j];
    }

}
