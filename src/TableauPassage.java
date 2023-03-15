public class TableauPassage extends Tableau {

    public TableauPassage(){
        super(3, 3);
        this.tableau = new int[][]{
                {-1, -1, -1, -1, -1},
                {-1, 0, 0, 0, -1},
                {-1, 0, 0, 0, -1},
                {-1, 0, 0, 0, -1},
                {-1, -1, -1, -1, -1},
        };
    }

    public TableauPassage(int nb_ligne, int nb_colonne){
        super(nb_ligne, nb_colonne);
    }

    public void passer(int i, int j){
        this.tableau[i][j] = 1;
    }

    public void revenir(int i, int j){
        this.tableau[i][j] = 0;
    }

}
