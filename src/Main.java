public class Main {
    public static void main(String[] args) {
//        Resolution test = new Resolution();

        Tableau test = new Tableau(5, 5, "0 1 2 3 0 2 3 0 1 3 0 1 3 0 1 2 0 1 2 3 0 1 2 3 0 1 2 3 ");
        test.afficher_tableau();
        test.Resolution(1, 1, 3, 3);
    }

}