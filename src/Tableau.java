import java.util.Calendar;

public class Tableau {
    private int[][] tableau;
    private int nb_case_occupe;
    public TableauPassage deja_passe;

    // Initialisation
    public Tableau(){
        int i, j;

        // Tableau de test
        this.tableau = new int[][]{
                {-1, -1, -1, -1, -1},
                {-1, 1, 2, 3, -1},
                {-1, 6, 4, 4, -1},
                {-1, 7, 8, 5, -1},
                {-1, -1, -1, -1, -1},
        };        // Tableau de test


        this.deja_passe = new TableauPassage();
    }

    public Tableau(int nb_ligne, int nb_colonne, String valeurs){
        String[] liste = valeurs.split(" ");
        this.tableau = new int[nb_ligne + 2][nb_colonne + 2];
        // Ajout des -1 autour
        for (int i = 0; i < nb_ligne + 2; i++) {
            for (int j = 0; j < nb_colonne + 2; j++) {
                if (i == 0 || i == nb_ligne + 1 || j == 0 || j == nb_colonne + 1){
                    this.tableau[i][j] = -1;
                } else {
                    tableau[i][j] = Integer.parseInt(liste[(i-1)*nb_colonne + (j-1)]);
                }
            }
        }
        this.deja_passe = new TableauPassage();
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


    public void Resolution(int i_debut, int j_debut, int i_fin, int j_fin) {
        // Permet de de replacer sur le mouvement précédent
        Euler coupPrecedent;

        // Cordonnée début et fin
//        int i_debut = 1;
//        int j_debut = 1;
//        int i_fin = 3;
//        int j_fin = 1;

        // Coordonnée en cour sur le tableau
        int i = i_debut;
        int j = j_debut;

        // Stocke la liste des mouvements précédents sur un chemin
        Pile<Euler> liste_ancienne_position = new Pile<Euler>();

        // Pour calculer le temps d'execution
        Calendar heure_debut = Calendar.getInstance(), heure_fin;

        // Stocke chemin et dénivelé chemin mini
        int denivele_min = Integer.MAX_VALUE;
        String chemin_min = "";

        // Pour stocker temporairement un dénivelé
        int denivele = 0;
        int dernier_denivele;
        String dernier_chemin = "";

        // Pour naviguer
        int choix_precedent = 0, nouveau_choix = 0;

        while (true){
            // Indique un retour impossible sur la case actuelle
            this.deja_passe.passer(i, j);

            //Test si arrivé
            if (i == i_fin && j == j_fin){
                int cout = liste_ancienne_position.getDernierElement().denivele;
                if (cout < denivele_min){
                    denivele_min = cout;
                    chemin_min = liste_ancienne_position.getDernierElement().chemin + "("+i+","+j+") ";
                }

                nouveau_choix = -1;
                coupPrecedent = liste_ancienne_position.getDernierElement();
                this.deja_passe.revenir(coupPrecedent.posX, coupPrecedent.posY);
            } else {
                nouveau_choix = this.choix_suivant(i, j, choix_precedent);
            }


            if (nouveau_choix != -1){
                // Récupère le denivelé du dernier parcourt pour l'additionner
                try {
                    dernier_denivele = liste_ancienne_position.getDernierElement().denivele;
                } catch (Exception e){
                    dernier_denivele = 0;
                }
                try {
                    dernier_chemin = liste_ancienne_position.getDernierElement().chemin;
                } catch (Exception e){
                    dernier_chemin = "";
                }

                if (nouveau_choix == 1) {
                    denivele = this.getDeniveleDeuxPoint(i, j, i-1, j) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    i -=1;
                }
                if (nouveau_choix == 2) {
                    denivele = this.getDeniveleDeuxPoint(i, j, i, j-1) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    j -= 1;
                }
                if (nouveau_choix == 3) {
                    denivele = this.getDeniveleDeuxPoint(i, j, i, j+1) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    j +=1;
                }
                if (nouveau_choix == 4) {
                    denivele = this.getDeniveleDeuxPoint(i, j, i+1, j) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    i += 1;
                }

                choix_precedent = 0;
            } else if (i == i_debut && j == j_debut){
                break;
            }

            else {
                coupPrecedent = liste_ancienne_position.getDernierElement();
                liste_ancienne_position.depiler();
                this.deja_passe.revenir(i, j);
                i = coupPrecedent.posX;
                j = coupPrecedent.posY;
                choix_precedent = coupPrecedent.choixPrecedent;
            }

        }

        System.out.println(chemin_min + denivele_min);
    }
}
