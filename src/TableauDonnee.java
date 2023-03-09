import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableauDonnee {
    // Stocke la liste de tout les noeuds (noeud = case du tableau)
    Noeud[][] liste_noeud;
    // Stocke la liste des Connexion utilisable
    ArrayList<PositionStockage> liste_connexion_dispo;
    //Stocke les valeurs du tableau
    int[][] tableau;

    // Décalage (ligne, colonne)
    int[][] decalage = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    //Dimension tableau
    int nb_colonnes;
    int nb_lignes;

    // Initialisation avec les arguments:
    // Nombre de colonnes, nombre de lignes, liste de nombre
    public TableauDonnee(int nb_colonnes, int nb_lignes, String entree){

        // Initialisation des dimensions du tableau
        this.nb_lignes = nb_lignes;
        this.nb_colonnes = nb_colonnes;

        // Découpage de l'entrée
        String[] liste_valeurs = entree.split(" ");

        // Vérification
        checkEntree(nb_colonnes, nb_lignes, liste_valeurs);


        // Formatage des valeurs entrées vers le tableau
        this.tableau = new int[this.nb_lignes][this.nb_colonnes];
        for (int i = 0; i < this.nb_lignes; i++) {
            for (int j = 0; j < this.nb_colonnes; j++) {
                this.tableau[i][j] = 1;
                System.out.println(liste_valeurs[i * this.nb_colonnes + j]);
            }
        }
    }

    // Initialisation avec les arguments:
    // Largeur, liste de nombre
    public TableauDonnee(int dimension, String entree){
        new TableauDonnee(dimension, dimension, entree);
    }

    // Initialisation avec les arguments:
    // liste de nombre
    // Fonctionne seulement pour une matrice carré
    public TableauDonnee(String entree) throws IllegalArgumentException{
        // Calcul le nombre de nombres et en fait la racine carré
        double taille = Math.sqrt(entree.split(" ").length);
        //Si la racine carré est un nombre entier, alors on peut créer le tableau
        if (taille == Math.floor(taille)){
            new TableauDonnee((int) taille, (int) taille, entree);
        }
        // Sinon on ne créé pas le tableau
        else {
            throw new IllegalArgumentException("Ne permet pas de faire une matrice carré");
        }
    }

    // Initialisation test
    public TableauDonnee() {
        // Initialisation des dimensions du tableau
        this.nb_colonnes = 3;
        this.nb_lignes = 4;

        // Initialisation des tableaux de stockage
        this.liste_noeud = new Noeud[this.nb_lignes][this.nb_colonnes];
        this.liste_connexion_dispo = new ArrayList<PositionStockage>();

        //Tableau[ligne][colonne]
        this.tableau = new int[][]{
                {1, 20, 30},   // (0, 1, 2)
                {40, 50, 50},   // (3, 4, 5)
                {7, 8, 8},   // (6, 7, 8)
                {10, 11, 10} // (9, 10, 11)
        };

        // Création des noeuds et des liaisons
        CreerNoeud();
        CreerLiaison();

        //Affiche la liste des liaisons
//        for (int i = 0; i < this.nb_lignes; i++) {
//            for (int j = 0; j < this.nb_colonnes; j++) {
//                System.out.println("i:" + i + " j:" + j);
//                liste_noeud[i][j].afficher_liaison();
//            }
//        }
        // Trouve le chemin le plus court
        Dijkstra(0, 1);
    }

    private void CreerNoeud() {
        for (int i = 0; i < this.nb_lignes; i++) {
            for (int j = 0; j < this.nb_colonnes; j++) {
                this.liste_noeud[i][j] = new Noeud(this.tableau[i][j], i*this.nb_colonnes + j);
            }
        }
    }

    private void CreerLiaison(){
        for (int i = 0; i < this.nb_lignes; i++) {
            for (int j = 0; j < this.nb_colonnes; j++) {
                for (int[] deplacement: decalage) {
                    try {
                        int cout = liste_noeud[i+deplacement[0]][j+deplacement[1]].getHauteur() - liste_noeud[i][j].getHauteur();
                        if (cout < 4){
                            if (cout > 0){
                                liste_noeud[i][j].ajouter_liaison(liste_noeud[i+deplacement[0]][j+deplacement[1]], cout);
                            }
                            else if (cout >= -3){
                                liste_noeud[i][j].ajouter_liaison(liste_noeud[i+deplacement[0]][j+deplacement[1]], 0);
                            }
                        }
                    }
                    finally {
                        continue;
                    }
                }
            }
        }
    }

    private void Dijkstra(int debut, int fin){
        // Stocke la liste des Connexion
        // Chaque nouvelle étape créé une nouvelle liste
        List<Connexion[]> etape = new ArrayList<Connexion[]>();

        // Stocke la position de la Connexion avec la plus petite distance en cour dans etape
        PositionStockage minimum;

        // Indique à quelle étape nous sommes
        int ligne = 0;

        // Indique de quelle case ont fait les calculs de l'étape
        int position = debut;

        // Ajoute une ligne d'étape
        etape.add(new Connexion[nb_colonnes * nb_lignes]);
        // La première connexion est le point de départ, donc avec un coût de 0
        etape.get(ligne)[position] = new Connexion(0, null);

        while (true){
            // Met le noueud sur lequel on se trouve comme non retour dessus possible par la suite
            liste_noeud[position/this.nb_colonnes][position%this.nb_colonnes].setRetour_possible(false);

            // On va supprimer dans la liste des connexions dispo celles dont le noeud n'a pas de retour posssible dessus
            int compteur = 0;
            for (int i = 0; i < liste_connexion_dispo.size(); i++) {
                if (liste_connexion_dispo.get(i-compteur).getColonne() == position){
                    liste_connexion_dispo.remove(i-compteur);
                    compteur += 1;
                }
            }

            // Si le noeud de fin n'a pas de retour possible dessus, cela veut dire que l'on est arrivé
            if (!liste_noeud[fin / this.nb_colonnes][fin % this.nb_colonnes].getRetourPossible()){
                // Affiche distance mini
                System.out.println("La distance mini est de: " + etape.get(ligne)[position].getCout());

                // Contient l'index des cases du chemin, dans l'ordre
                String chemin = "" + position;

                // Avant, la position était sur la dernière case
                // Maintenant on remonte
                position = etape.get(ligne)[position].getNoeud().getPosition();
                chemin = position + " " + chemin;

                // Tant que la position n'est pas sur la case de début
                while (position != debut){
                    int i = 0;
                    while (true) {
                        // On remonte les étapes petit à petit sur la position en cour
                        // On essaye de voir si il y a quelque chose
                        // Si il n'y a rien -> erreur donc on incrémente i et on continue
                        // Si il a quelque chose, on arrête, le dernier chemin de la case a été trouvé
                        try {
                            int a = etape.get(ligne - i)[position].getCout();
                            break;
                        }
                        catch (Exception e){
                            i++;
                        }
                    }
                    // On ajoute son index à la liste
                    chemin = etape.get(ligne - i)[position].getNoeud().getPosition() + " " + chemin;
                    position = etape.get(ligne - i)[position].getNoeud().getPosition();
                }

                System.out.println(chemin);
                System.out.println("On part");
                break;
            }

            // Parcourt la liste des liaisons de la case sur laquelle on se trouve
            for (Liaison liaison: liste_noeud[position/this.nb_colonnes][position%this.nb_colonnes].getListe_liaison()) {
                // Teste si la liaison est possible, c'est à dire si le noeud n'est pas marqué comme retour impposible dessus
                if (liaison.getNoeud_connecte().getRetourPossible()) {
                    // On créé une nouvelle Connexion avec son coût depuis la première case
                    etape.get(ligne)[liaison.getNoeud_connecte().getPosition()] = new Connexion(etape.get(ligne)[position].getCout() + liaison.getCout(), liste_noeud[position / this.nb_colonnes][position % this.nb_colonnes]);
                    // On ajoute cette Connexion dans la liste des connexions dispo
                    liste_connexion_dispo.add(new PositionStockage(ligne, liaison.getNoeud_connecte().getPosition()));
                }
            }

            // On cherche maintenant à trouver parmi la liste des connexion dispo, laquelle est la plus courte
            // On test en même temps si il y a des connexion dispo
            try {
                // On commence par définir la plus courte comme étant la première
                minimum = liste_connexion_dispo.get(0);
            }
            catch (Exception e){
                // Dans le cas où il n'y a pas de connexion dispo -> Erreur
                // Cela signifie que le point est inatteignable
                System.out.print("POINT INATTEIGNABLE");
                break;
            }


            // Puis on parcourt chacune des coonexion dispo pour voir si elle est plus courte
            for (PositionStockage position_stockage: liste_connexion_dispo) {
                if (etape.get(position_stockage.getLigne())[position_stockage.getColonne()].getCout() < etape.get(minimum.getLigne())[minimum.getColonne()].getCout()) {
                    minimum = position_stockage;
                }
            }

            // Affiche l'étape en cour
            int i = 0;
            for (Connexion connexion: etape.get(ligne)) {
                try {
                    System.out.print(connexion.getCout() + "-" + connexion.getNoeud().getPosition());
                }
                catch (Exception e){
                    System.out.print("N");
                }
                System.out.print( " " + liste_noeud[i/this.nb_colonnes][i%this.nb_colonnes].getRetourPossible() + "  ");
                i++;
            }
            System.out.println(position);

            // On place la nouvelle position sur la case où l'on a trouvé la distance la plus courte
            position = minimum.getColonne();

            // On créé l'étape suivante
            etape.add(new Connexion[this.nb_colonnes*this.nb_lignes]);
            // On incrémente l'index d'étape
            ligne += 1;

            // On ajoute la Conexion choisi sur la case dans la nouvelle étape
            etape.get(ligne)[position] = etape.get(minimum.getLigne())[minimum.getColonne()];

//            sc.nextLine();
//
        }
    }


    // Cette fonction permet de vérifier les paramètres avant de créer un tableau
    // On vérifie les points suivants:
    // °Le nombre de nombres correspond aux dimensions
    // °Il n'y a que des nombres
    private void checkEntree(int nb_colonnes, int nb_lignes, String[] entree) throws IllegalArgumentException {
        int compteur = 0;

        for (String nombre: entree ) {
            try {
                Integer.parseInt(nombre);
            }
            catch (NumberFormatException e){
                throw new IllegalArgumentException("Seul les nombres sont autorisés");
            }
            compteur++;
        }
        if (compteur < nb_colonnes * nb_colonnes){
            throw new IllegalArgumentException("Pas assez de nombre");
        }
    }
}
