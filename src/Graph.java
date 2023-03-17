import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Graph extends JFrame{
    private JButton validerButton;
    private JButton calculerButton;
    private JButton chargerButton;
    private JTextField[] liste_texte;
    private JPanel place_tableau;
    private JPanel principale;
    private JPanel placeBtnGauche;
    private JPanel placeBtnDroite;
    private JLabel texte;
    private JButton button2;
    private GraphTableauIN graphTableauIN1;
    private GraphTableauButton graphTableauButton1;

    TableauDonnee tableau;

    public Graph() {
        setContentPane(principale);
        setTitle("Programme INF");
        setSize(700, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Ajoute le tableau
        graphTableauIN1 = new GraphTableauIN(3, 3);
        place_tableau.add(graphTableauIN1);

        // Tant que le tableau n'est pas validé, on ne peut pas calculer
        calculerButton.setVisible(false);

        //Actualise pour afficher
        revalidate();

        // Une fois que toutes les valeurs sont entrées
        // Bloque la modification des cases
        // Création d'un tableau
        validerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texte = graphTableauIN1.getString();
                System.out.println(texte);

                try {
                    tableau = new TableauDonnee(3, 3, texte);
                    validerButton.setVisible(false);
                    chargerButton.setVisible(false);

                    calculerButton.setVisible(true);

                    graphTableauButton1 = new GraphTableauButton(3, 3, graphTableauIN1.getString());
                    place_tableau.removeAll();
                    place_tableau.add(graphTableauButton1);
                    //Actualise pour afficher
                    revalidate();
                }
                catch (IllegalArgumentException error){
                    System.out.println(error.getMessage());
                }
            }
        });

        // Calcul chemin plus court
        calculerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphTableauButton1.getCompteur() != 2){
                    texte.setText("Veuillez selectionner 2 points");
                }
                else {
                    texte.setText("Calcul en cour");
                }
            }
        });
    }

    public static void main(String[] args) {
        Graph myFrame = new Graph();
    }

}
