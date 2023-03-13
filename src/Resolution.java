import java.util.Calendar;

public class Resolution {
    // Pile qui contient les anciens états de l'échiquier
    Pile<Euler> liste_ancienne_position;
    Euler coupPrecedent;
    Tableau tableau;
    int choix_precedent, nouveau_choix;
//    int numero_coup; //OSEF je pense

    // Coordonnée en cour sur le tableau
    int i, j;

    // Cordonnée début et fin
    int i_debut, j_debut, i_fin, j_fin;

    // Pour calculer le temps d'execution
    Calendar heure_debut, heure_fin;

    public Resolution() {
        liste_ancienne_position = new Pile<Euler>();
        tableau = new Tableau();
        heure_debut = Calendar.getInstance();

        i_debut = 1;
        j_debut = 1;
        i_fin = 3;
        j_fin = 1;

        int i = i_debut;
        int j = j_debut;

        int denivele_min = Integer.MAX_VALUE;
        String chemin_min = "";

        tableau = new Tableau();

        choix_precedent = 0;
        int denivele = 0;
        int dernier_denivele;
        String dernier_chemin = "";
        int k = 0;
        while (true){
            tableau.deja_passe.passer(i, j);


            //Test si arrivé
            if (i == i_fin && j == j_fin){
                int cout = liste_ancienne_position.getDernierElement().denivele;
                if (cout < denivele_min){
                    denivele_min = cout;
                    chemin_min = liste_ancienne_position.getDernierElement().chemin + "("+i+","+j+") ";
                }


                nouveau_choix = -1;
                System.out.println("");
                coupPrecedent = liste_ancienne_position.getDernierElement();
                tableau.deja_passe.revenir(coupPrecedent.posX, coupPrecedent.posY);
            } else {
                nouveau_choix = tableau.choix_suivant(i, j, choix_precedent);
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
                    denivele = tableau.getDeniveleDeuxPoint(i, j, i-1, j) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    i -=1;
                }
                if (nouveau_choix == 2) {
                    denivele = tableau.getDeniveleDeuxPoint(i, j, i, j-1) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    j -= 1;
                }
                if (nouveau_choix == 3) {
                    denivele = tableau.getDeniveleDeuxPoint(i, j, i, j+1) + dernier_denivele;
                    dernier_chemin += "("+i+","+j+") ";
                    liste_ancienne_position.Emplier(new Euler(i, j, nouveau_choix, denivele, dernier_chemin));
                    j +=1;
                }
                if (nouveau_choix == 4) {
                    denivele = tableau.getDeniveleDeuxPoint(i, j, i+1, j) + dernier_denivele;
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
                tableau.deja_passe.revenir(i, j);
                i = coupPrecedent.posX;
                j = coupPrecedent.posY;
                choix_precedent = coupPrecedent.choixPrecedent;
            }

        }

        System.out.println(chemin_min + denivele_min);
    }
}
