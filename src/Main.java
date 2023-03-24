public class Main {
    public static void main(String[] args) {
//        Resolution test = new Resolution();

        TableauDonnee test = new TableauDonnee(4, 4, "1 1 1 1 1 1 1 1 1 10 10 10 1 1 1 1 ");
//        TableauDonnee test = new TableauDonnee(3, 3, "0 1 2 3 0 1 2 3 2");
        test.afficher_tableau();
        test.Resolution(1, 1, 4, 4);
        test.Resolution(4, 4, 1, 1);
    }
}