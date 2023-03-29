import javax.swing.*;
import java.awt.*;

public class GraphTableauIN extends JPanel{
    protected JTextField[][] liste_texte;
    int nbligne;
    int nbcolonnes;

    public GraphTableauIN(int nbLignes, int nbColonne){
        this.nbligne = nbLignes;
        this.nbcolonnes = nbColonne;
        liste_texte = new JTextField[nbLignes][nbColonne];
        setLayout(new GridLayout(nbLignes, nbColonne));

        int dimension = 600 / Math.max(nbColonne, nbLignes);

        Dimension dimension_texte = new Dimension(dimension, dimension);
        Font font_text = new Font("SansSerif", Font.BOLD, 30);

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonne; j++) {
                // Création des éléments
                JTextField texte = new JTextField();

                //Taille
                texte.setPreferredSize(dimension_texte);
                texte.setMaximumSize(dimension_texte);
                texte.setMinimumSize(dimension_texte);
                texte.setFont(font_text);
                texte.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                texte.setHorizontalAlignment(JTextField.CENTER);

                //Ajout au tableau de sauvegarde
                liste_texte[i][j] = texte;

                // Ajout graphiquement
                add(texte, new GridLayout(i, j+1));
            }
        }

    }

    public void setValue(String valeur){
        String[] valeurs = valeur.split(" ");
        for (int i = 0 ; i < nbligne; i++) {
            for (int j = 0; j < nbcolonnes; j++) {
                liste_texte[i][j].setText(valeurs[i*nbligne + j]);
            }
        }
    }

    public String getString(){
        String texte = "";
        for (int i = 0; i < this.nbligne; i++) {
            for (int j = 0; j < this.nbcolonnes; j++) {
                texte += liste_texte[i][j].getText() + " ";
            }
        }
        return texte;
    }
}
