public class Tableau {
    private int[][] tableau;
    private int nb_case_occupe;
    public TableauPassage deja_passe;

    // Initialisation
    public Tableau(){
        int i, j;
//        this.nb_case_occupe = 0; // OSEF je pense

        // Tableau de test
        this.tableau = new int[][]{
                {-1, -1, -1, -1, -1},
                {-1, 1, 2, 3, -1},
                {-1, 6, 4, 4, -1},
                {-1, 7, 8, 5, -1},
                {-1, -1, -1, -1, -1},
        };        // Tableau de test


        this.deja_passe = new TableauPassage();
        // Set la case de départ comme déja passé
        this.deja_passe.passer(1, 1);
    }



    public void afficher_tableau(){
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau[i].length; j++) {
                System.out.print(tableau[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public int choix_suivant(int i, int j, int choix_precedent){

        if (choix_precedent == 0){
            // Dessus
            if (this.deja_passe.getTableau(i-1, j) == 0 && trajerPossible(i, j, i-1, j)) return 1;
            // A gauche
            else if (this.deja_passe.getTableau(i, j-1) == 0 && trajerPossible(i, j, i, j-1)) return 2;
            // A droite
            else if (this.deja_passe.getTableau(i, j+1) == 0 && trajerPossible(i, j, i, j+1)) return 3;
            // Dessous
            else if (this.deja_passe.getTableau(i+1, j) == 0 && trajerPossible(i, j, i+1, j)) return 4;
        }

        else if (choix_precedent == 1){
            // A gauche
            if (this.deja_passe.getTableau(i, j-1) == 0 && trajerPossible(i, j, i, j-1)) return 2;
            // A droite
            else if (this.deja_passe.getTableau(i, j+1) == 0 && trajerPossible(i, j, i, j+1)) return 3;
            // Dessous
            else if (this.deja_passe.getTableau(i+1, j) == 0 && trajerPossible(i, j, i+1, j)) return 4;
        }

        else if (choix_precedent == 2){
            // A droite
            if (this.deja_passe.getTableau(i, j+1) == 0 && trajerPossible(i, j, i, j+1)) return 3;
            // Dessous
            else if (this.deja_passe.getTableau(i+1, j) == 0 && trajerPossible(i, j, i+1, j)) return 4;
        }

        else if (choix_precedent == 3){
            // Dessous
            if (this.deja_passe.getTableau(i+1, j) == 0 && trajerPossible(i, j, i+1, j)) return 4;
        }
        else if (choix_precedent == 4) return -1;

        return -1;
    }

    public int getTableau(int i, int j) {
        return tableau[i][j];
    }

    // a --> b
    private boolean trajerPossible(int ia, int ja, int ib, int jb){
        int ecart = this.tableau[ib][jb] - this.tableau[ia][ja];

        if(ecart <= 3 && ecart >= -3) return true;
        else return false;
    }
    // a --> b
    public int getDeniveleDeuxPoint(int ia, int ja, int ib, int jb){
        int ecart = this.tableau[ib][jb] - this.tableau[ia][ja];
        if (ecart < 0) ecart=0;
        return ecart;

    }
}
