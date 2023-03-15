import java.util.Calendar;

public class TableauDonnee {
    private int[][] tableau;
    public TableauPassage deja_passe;

    // Initialisation test
    public TableauDonnee(){
        // TableauDonnee de test
        this.tableau = new int[][]{
                {-1, -1, -1, -1, -1},
                {-1, 1, 2, 3, -1},
                {-1, 6, 4, 4, -1},
                {-1, 7, 8, 5, -1},
                {-1, -1, -1, -1, -1},
        };        // TableauDonnee de test

        this.deja_passe = new TableauPassage();
    }

    // Initialisation pour n'importe quel tableau
    public TableauDonnee(int nb_ligne, int nb_colonne, String valeurs){
        // Conversion de la chaine de charactere en tableau
        String[] liste = valeurs.split(" ");
        // Initialisation du tableau en fonction de la taille demandé
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
        // Création du tableau contenant les indicateurs de passage
        this.deja_passe = new TableauPassage(nb_ligne, nb_colonne);
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

    // Détermine la prochaine case vers laquelle se dépacer
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

    // Renvoie la valeur du tableau pour un index donné
    public int getTableau(int i, int j) {
        return tableau[i][j];
    }

    // Vérifie qu'un trajet du point a vers le point b est possible
    // a --> b
    private boolean trajerPossible(int ia, int ja, int ib, int jb){
        int ecart = this.tableau[ib][jb] - this.tableau[ia][ja];
        if (this.tableau[ib][jb] == -1) return false;
        if(ecart <= 3 && ecart >= -3) return true;
        else return false;
    }

    // Donne le dénivelé positif du point a vers le point b
    // a --> b
    public int getDeniveleDeuxPoint(int ia, int ja, int ib, int jb){
        int ecart = this.tableau[ib][jb] - this.tableau[ia][ja];
        if (ecart < 0) ecart=0;
        return ecart;
    }


    // Algortihme de résolution du chemin le plus court
    public void Resolution(int i_debut, int j_debut, int i_fin, int j_fin) {
        // Permet de se replacer sur le mouvement précédent
        Euler coupPrecedent;

        // Coordonnée en cours sur le tableau
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
        int denivele;
        int dernier_denivele;
        String dernier_chemin = "";

        // Pour naviguer
        int choix_precedent = 0, nouveau_choix = 0;

        while (true){
            // Indique un retour impossible sur la case actuelle
            this.deja_passe.passer(i, j);

            // Sauvegarde le dénivelé du chemin en cour
            // Besoin d'un try/catch pour le premier cycle
            try {
                dernier_denivele = liste_ancienne_position.getDernierElement().denivele;
            } catch (Exception e){
                dernier_denivele = 0;
            }

            // Cas où on est arrivé sur la case finale
            if (i == i_fin && j == j_fin){
                // Cas où ce chemin jusqu'à la case finale est plus court qu'un chemin précédemment trouvé
                if (dernier_denivele < denivele_min){
                    denivele_min = dernier_denivele;
                    chemin_min = liste_ancienne_position.getDernierElement().chemin + "("+i+","+j+") ";
                }

                // Préparation pour la suite du programme afin de revenir à la case précédente
                nouveau_choix = -1;
                coupPrecedent = liste_ancienne_position.getDernierElement();
                this.deja_passe.revenir(coupPrecedent.posX, coupPrecedent.posY);
            }
            // Si le dénivelé en cours est plus important qu'un dénivelé de chemin final précédement trouve, rien ne sert de continuer
            // On revient donc en arrière
            else if(dernier_denivele >= denivele_min){
                nouveau_choix = -1;
                coupPrecedent = liste_ancienne_position.getDernierElement();
                this.deja_passe.revenir(coupPrecedent.posX, coupPrecedent.posY);
            }
            // Sinon on continu à avancer
            else {
                nouveau_choix = this.choix_suivant(i, j, choix_precedent);
            }


            // Dans le cas ou l'on peut encore se déplacer à partir de la case actuel
            if (nouveau_choix != -1){
                // Récupère le dénivelé du dernier parcourt pour l'additionner
                try {
                    dernier_chemin = liste_ancienne_position.getDernierElement().chemin;
                } catch (Exception e){
                    dernier_chemin = "";
                }

                // Haut
                if (nouveau_choix == 1) {
                    denivele = this.getDeniveleDeuxPoint(i, j, i-1, j) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    i -=1;
                }

                // Gauche
                if (nouveau_choix == 2) {
                    denivele = this.getDeniveleDeuxPoint(i, j, i, j-1) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    j -= 1;
                }

                // Droite
                if (nouveau_choix == 3) {
                    denivele = this.getDeniveleDeuxPoint(i, j, i, j+1) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    j +=1;
                }

                // Bas
                if (nouveau_choix == 4) {
                    denivele = this.getDeniveleDeuxPoint(i, j, i+1, j) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    i += 1;
                }

                choix_precedent = 0;
            }
            // Si le nouveau choix est -1 et que l'on est sur la première case, cela veut dire qu'il n'y a plus de possibilité de déplacement
            // On termine la boucle
            else if (i == i_debut && j == j_debut){
                break;
            }
            // Si il n'y a plus de possibilité de déplacement sur la case en cours, mais que l'on n'est pas sur la première case
            else {
                coupPrecedent = liste_ancienne_position.getDernierElement();
                liste_ancienne_position.depiler();
                this.deja_passe.revenir(i, j);
                i = coupPrecedent.posX;
                j = coupPrecedent.posY;
                choix_precedent = coupPrecedent.choixPrecedent;
            }
        }

        // Affiche résultat
        System.out.println(chemin_min + denivele_min);
        heure_fin = Calendar.getInstance();
        System.out.println((heure_fin.getTimeInMillis() - heure_debut.getTimeInMillis()));
    }
}
