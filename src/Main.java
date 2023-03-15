public class Main {
    public static void main(String[] args) {
//        Resolution test = new Resolution();

        Tableau test = new Tableau(8, 7, "0 1 2 3 0 2 3 0 1 3 0 1 3 0 1 2 0 1 2 3 0 1 2 3 0 1 2 3 0 1 2 3 0 2 3 0 1 3 0 1 3 0 1 2 0 1 2 3 0 1 2 3 0 1 2 3 0 1 2 3 0 2 3 0 1 3 0 1 3 0 1 2 0 1 2 3 0 1 2 3 0 1 2 3 0 1 2 3 0 2 3 0 1 3 0 1 3 0 1 2 0 1 2 3 0 1 2 3 0 1 2 3 0 1 2 3 0 2 3 0 1 3 0 1 3 0 1 2 0 1 2 3 0 1 2 3 0 1 2 3 0 1 2 3 0 2 3 0 1 3 0 1 3 0 1 2 0 1 2 3 0 1 2 3 0 1 2 3 0 1 2 3 0 2 3 0 1 3 0 1 3 0 1 2 0 1 2 3 0 1 2 3 0 1 2 3 ");
//        Tableau test = new Tableau(2, 2, "0 1 2 3 ");
        test.afficher_tableau();
        test.Resolution(1, 1, 4, 4);
    }

}