import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableauDonnee {
    Noeud[][] liste_noeud;
    ArrayList<PositionStockage> liste_connexion_dispo;
    int[][] tableau;

    // Décalage (ligne, colonne)
    int[][] decalage = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public TableauDonnee() {
        // ATTENTION BUG: si dépasse 9 ça bug
        this.tableau = new int[][]{
                {14, 11, 12}, // (0, 1 ,2)
                {13, 12, 13},   // (3, 4, 5)
                {10, 11, 13}};  // (6, 7, 8)

        this.liste_noeud = new Noeud[3][3];
        this.liste_connexion_dispo = new ArrayList<PositionStockage>();

        CreerNoeud();
        CreerLiaison();


//        //Affiche la liste des liaisons
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.println("i:" + i + " j:" + j);
//                liste_noeud[i][j].afficher_liaison();
//            }
//        }

        Dijkstra();

    }

    public void CreerNoeud() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.liste_noeud[i][j] = new Noeud(this.tableau[i][j], i*3 + j);
            }
        }
    }

    public void CreerLiaison(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int[] deplacement: decalage) {
                    try {
                        int cout = liste_noeud[i+deplacement[0]][j+deplacement[1]].hauteur - liste_noeud[i][j].hauteur;
                        if (cout <= 4){
                            if (cout > 0){
                                liste_noeud[i][j].ajouter_liaison(liste_noeud[i+deplacement[0]][j+deplacement[1]], cout);
                            }
                            else if (cout > -3){
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

    public void Dijkstra(){
        System.out.println("I0       I1       I2       I3       I4       I5       I6       I7       I8        POSITION ");

        // A suprimmer, sert juste pour faire des pauses
        Scanner sc = new Scanner(System.in);

        // Index du début et de la fin du chemin
        int debut = 1;
        int fin = 0;

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
        etape.add(new Connexion[9]);
        // La première connexion est le point de départ, donc avec un coût de 0
        etape.get(ligne)[position] = new Connexion(0, null);

        while (true){
            // Met le noueud sur lequel on se trouve comme non retour dessus possible par la suite
            liste_noeud[position/3][position%3].retour_possible = false;

            // On va supprimer dans la liste des connexions dispo celles dont le noeud n'a pas de retour posssible dessus
            int compteur = 0;
            for (int i = 0; i < liste_connexion_dispo.size(); i++) {
                if (liste_connexion_dispo.get(i-compteur).colonne == position){
                    liste_connexion_dispo.remove(i-compteur);
                    compteur += 1;
                }
            }

            // Si le noeud de fin n'a pas de retour possible dessus, cela veut dire que l'on est arrivé
            if (!liste_noeud[fin / 3][fin % 3].retour_possible){
                // Affiche distance mini
                System.out.println("La distance mini est de: " + etape.get(ligne)[position].cout);

                // Contient l'index des cases du chemin, dans l'ordre
                String chemin = "" + position + " ";

                // Avant, la position était sur la dernière case
                // Maintenant on remonte
                position = etape.get(ligne)[position].noeud.postion;
                chemin += position + " ";

                // Tant que la position n'est pas sur la case de début
                while (position != debut){
                    int i = 0;
                    while (true) {
                        // On remonte les étapes petit à petit sur la position en cour
                        // On essaye de voir si il y a quelque chose
                        // Si il n'y a rien -> erreur donc on incrémente i et on continue
                        // Si il a quelque chose, on arrête, le dernier chemin de la case a été trouvé
                        try {
                            int a = etape.get(ligne - i)[position].cout;
                            break;
                        }
                        catch (Exception e){
                            i++;
                        }
                    }
                    // On ajoute son index à la liste
                    chemin += etape.get(ligne - i)[position].noeud.postion + " ";
                    position = etape.get(ligne - i)[position].noeud.postion;
                }

                System.out.println(chemin);
                System.out.println("On part");
                break;
            }

            // Parcourt la liste des liaisons de la case sur laquelle on se trouve
            for (Liaison liaison: liste_noeud[position/3][position%3].liste_liaison) {
                // Teste si la liaison est possible, c'est à dire si le noeud n'est pas marqué comme retour impposible dessus
                if (liaison.noeud_connecte.retour_possible) {
                    // On créé une nouvelle Connexion avec son coût depuis la première case
                    etape.get(ligne)[liaison.noeud_connecte.postion] = new Connexion(etape.get(ligne)[position].cout + liaison.cout, liste_noeud[position / 3][position % 3]);
                    // On ajoute cette Connexion dans la liste des connexions dispo
                    liste_connexion_dispo.add(new PositionStockage(ligne, liaison.noeud_connecte.postion));
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
                if (etape.get(position_stockage.ligne)[position_stockage.colonne].cout < etape.get(minimum.ligne)[minimum.colonne].cout) {
                    minimum = position_stockage;
                }
            }

            // Affiche l'étape en cour
            int i = 0;
            for (Connexion connexion: etape.get(ligne)) {
                try {
                    System.out.print(connexion.cout + "-" + connexion.noeud.postion);
                }
                catch (Exception e){
                    System.out.print("N");
                }
                System.out.print( " " + liste_noeud[i/3][i%3].retour_possible + "  ");
                i++;
            }
            System.out.println(position);

            // On place la nouvelle position sur la case où l'on a trouvé la distance la plus courte
            position = minimum.colonne;

            // On créé l'étape suivante
            etape.add(new Connexion[9]);
            // On incrémente l'index d'étape
            ligne += 1;

            // On ajoute la Conexion choisi sur la case dans la nouvelle étape
            etape.get(ligne)[position] = etape.get(minimum.ligne)[minimum.colonne];

//            sc.nextLine();
//


        }
    }
}
