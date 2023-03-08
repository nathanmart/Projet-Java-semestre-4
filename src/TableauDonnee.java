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
                {1, 2, 3},
                {4,5,1},
                {0,1,0}};

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
        Scanner sc = new Scanner(System.in);
        int debut = 6;
        int fin = 0;
        PositionStockage minimum;

        List<Connexion[]> etape = new ArrayList<Connexion[]>();

        int ligne = 0;
        int position = debut;
        etape.add(new Connexion[9]);
        etape.get(ligne)[position] = new Connexion(0, null);

        while (true){

//            System.out.println("Nous somme à la ligne: " + ligne);
//            System.out.println("La position de départ est " + position);
            liste_noeud[position/3][position%3].retour_possible = false;
            if (!liste_noeud[fin / 3][fin % 3].retour_possible){

                System.out.println("La distance mini est de: " + etape.get(ligne)[position].cout);
                String chemin = "" + position + " ";
                position = etape.get(ligne)[position].noeud.postion;
                chemin += position + " ";

                while (position != debut){
                    int i = 0;
                    while (true) {
                        try {
                            int a = etape.get(ligne - i)[position].cout;
                            break;
                        }
                        catch (Exception e){
                            i++;
                        }
                    }

                    chemin += etape.get(ligne - i)[position].noeud.postion + " ";
                    position = etape.get(ligne - i)[position].noeud.postion;
                }

                System.out.println(chemin);
                System.out.println("On part");
                break;

            }


            for (Liaison liaison: liste_noeud[position/3][position%3].liste_liaison) {
                if (liaison.noeud_connecte.retour_possible) {
                    etape.get(ligne)[liaison.noeud_connecte.postion] = new Connexion(etape.get(ligne)[position].cout + liaison.cout, liste_noeud[position / 3][position % 3]);
                    liste_connexion_dispo.add(new PositionStockage(ligne, liaison.noeud_connecte.postion));
                }
            }

            minimum = liste_connexion_dispo.get(0);

            for (PositionStockage position_stockage: liste_connexion_dispo) {
                if (etape.get(position_stockage.ligne)[position_stockage.colonne].cout < etape.get(minimum.ligne)[minimum.colonne].cout) {
                    minimum = position_stockage;
                }
            }

            for (Connexion connexion: etape.get(ligne)) {
                try {
                    System.out.print(connexion.cout + "-" + connexion.noeud.postion+ " ");
                }
                catch (Exception e){
                    System.out.print("N ");
                }
            }
            System.out.println();

//            for (int i = 0; i < 9; i++) {
//                System.out.print(liste_noeud[i/3][i%3].retour_possible + " ");
//            }
//            System.out.println("");

            position = minimum.colonne;
            etape.add(new Connexion[9]);
            ligne += 1;
            etape.get(ligne)[position] = etape.get(minimum.ligne)[minimum.colonne];

//            sc.nextLine();
//


            int compteur = 0;
            for (int i = 0; i < liste_connexion_dispo.size(); i++) {
                if (liste_connexion_dispo.get(i-compteur).colonne == position){
                    liste_connexion_dispo.remove(i-compteur);
                }
            }
        }

        System.out.println("Propre");


    }

}
