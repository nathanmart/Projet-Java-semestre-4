public class TableauPassage {
    private int[][] tableau;

    public TableauPassage(){
        this.tableau = new int[][]{
                {-1, -1, -1, -1, -1},
                {-1, 0, 0, 0, -1},
                {-1, 0, 0, 0, -1},
                {-1, 0, 0, 0, -1},
                {-1, -1, -1, -1, -1},
        };
    }

    public void passer(int i, int j){
        this.tableau[i][j] = 1;
    }

    public void revenir(int i, int j){
        this.tableau[i][j] = 0;
    }

    public int getTableau(int i, int j) {
        return tableau[i][j];
    }

    public void afficher_tableau(){
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau[i].length; j++) {
                System.out.print(tableau[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
