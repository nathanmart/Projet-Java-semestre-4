import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphTableauResultat extends JPanel{
    protected JLabel[][] liste_case;
    int nbligne;
    int nbcolonnes;

    public GraphTableauResultat(int nbLignes, int nbColonne, String valeur){
        this.nbligne = nbLignes;
        this.nbcolonnes = nbColonne;
        liste_case = new JLabel[nbLignes][nbColonne];
        setLayout(new GridLayout(nbLignes, nbColonne));

        String[] donnee = valeur.split(" ");

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
                bouton.setName(String.valueOf(i * 3 + j));
                bouton.setBackground(Color.WHITE);
                bouton.setText(donnee[i * 3 + j]);

                //Ajout au tableau de sauvegarde
                liste_case[i][j] = bouton;

                // Ajout graphiquement
                add(bouton, new GridLayout(i, j+1));
            }
        }

    }

}
