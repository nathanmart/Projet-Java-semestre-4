//BOTHUA Pierre
//MARTORANA Nathan
//Semestre 2

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphTableauResultat extends JPanel{
    protected JLabel[][] liste_case;
    int nbligne;
    int nbcolonnes;

    public GraphTableauResultat(int nbLignes, int nbColonne, String valeur, String chemin){
        this.nbligne = nbLignes;
        this.nbcolonnes = nbColonne;
        liste_case= new JLabel[nbLignes][nbColonne];
        setLayout(new GridLayout(nbLignes, nbColonne));

        String[] donnee = valeur.split(" ");
        String[] liste_case_chemin = chemin.split(" ");

        int dimension = 600 / Math.max(nbColonne, nbLignes);

        Dimension dimension_bouton = new Dimension(dimension, dimension);
        Font font_text = new Font("SansSerif", Font.BOLD, 30);


        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonne; j++) {
                // Création des éléments
                JLabel bouton = new JLabel();

                //Taille
                bouton.setPreferredSize(dimension_bouton);
                bouton.setMaximumSize(dimension_bouton);
                bouton.setMinimumSize(dimension_bouton);
                bouton.setFont(font_text);
                bouton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                bouton.setHorizontalAlignment(JTextField.CENTER);
                bouton.setName(String.valueOf(i*nbColonne + j));
                bouton.setBackground(Color.WHITE);
                bouton.setOpaque(true);
                bouton.setText(donnee[i*nbColonne + j]);

                //Ajout au tableau de sauvegarde
                liste_case[i][j] = bouton;

                // Ajout graphiquement
                add(bouton, new GridLayout(i, j+1));
            }
        }
        //Coloration
        try {
            for (String casee : liste_case_chemin) {
                String[] coord = casee.split(",");
                System.out.println("X:" + coord[0] + " Y:" + coord[1]);
                liste_case[Integer.parseInt(coord[0]) - 1][Integer.parseInt(coord[1]) - 1].setBackground(Color.RED);
            }
        }
        catch (Exception f){
            JOptionPane.showMessageDialog(null, "Chemin impossible");
        }

    }

}
