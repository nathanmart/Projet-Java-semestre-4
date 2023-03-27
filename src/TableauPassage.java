public class TableauPassage extends Tableau {
    int nbligne;
    int nbcolonne;

    public TableauPassage(int nb_ligne, int nb_colonne){
        super(nb_ligne, nb_colonne);
        this.nbligne = nb_ligne;
        this.nbcolonne = nb_colonne;
    }

    public void passer(int i, int j){
        this.tableau[i][j] = 1;
    }

    public void revenir(int i, int j){
        this.tableau[i][j] = 0;
    }

    public void reset(){
        for (int i = 0; i < nbligne; i++) {
            for (int j = 0; j < nbcolonne; j++) {
                this.tableau[i+1][j+1] = 0;
            }
        }
    }

}
