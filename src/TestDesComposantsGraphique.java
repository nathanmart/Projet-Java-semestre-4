//BOTHUA Pierre
//MARTORANA Nathan
//Semestre 2
import javax.swing.*;

public class TestDesComposantsGraphique extends JFrame {
    public TestDesComposantsGraphique(){
        setContentPane(new GraphTableauResultat(3, 3, "1 2 3 4 5 6 7 8 9", "1,1"));
        setTitle("Programme INF");
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        TestDesComposantsGraphique myFrame = new TestDesComposantsGraphique();
    }
}
