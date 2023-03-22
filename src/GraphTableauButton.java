import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphTableauButton extends JPanel{
    protected JButton[][] liste_bouton;
    int nbligne;
    int nbcolonnes;
    private int compteur;
    int index1;
    int index2;

    public GraphTableauButton(int nbLignes, int nbColonne, String valeur){
        this.nbligne = nbLignes;
        this.nbcolonnes = nbColonne;
        this.compteur = 0;
        this.index1 = -1;
        this.index2 = -1;
        liste_bouton = new JButton[nbLignes][nbColonne];
        setLayout(new GridLayout(nbLignes, nbColonne));

        String[] donnee = valeur.split(" ");

        int dimension = 600 / Math.max(nbColonne, nbLignes);

        Dimension dimension_bouton = new Dimension(dimension, dimension);
        Font font_text = new Font("SansSerif", Font.BOLD, 30);


        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonne; j++) {
                // Création des éléments
                JButton bouton = new JButton();

                //Taille
                bouton.setPreferredSize(dimension_bouton);
                bouton.setMaximumSize(dimension_bouton);
                bouton.setMinimumSize(dimension_bouton);
                bouton.setFont(font_text);
                bouton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                bouton.setHorizontalAlignment(JTextField.CENTER);
                bouton.setName(String.valueOf(i*nbColonne + j));
                bouton.setBackground(Color.WHITE);
                bouton.setText(donnee[i*nbColonne + j]);

                //Action
                bouton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Récupère l'index du bouton
                        int index = Integer.parseInt(((JButton)e.getSource()).getName());

                        // Si reclique sur départ
                        if (index == index1){
                            compteur = 0;
                            liste_bouton[index1/nbColonne][index1%nbColonne].setBackground(Color.WHITE);
                            if(index2 != -1){
                                liste_bouton[index2/nbColonne][index2%nbColonne].setBackground(Color.WHITE);
                            }
                            index1 = -1;
                            index2 = -1;
                        }
                        // Si reclique sur arrivé
                        else if (index == index2){
                            System.out.println("LA ON Y EST");
                            compteur = 1;
                            liste_bouton[index/nbColonne][index%nbColonne].setBackground(Color.WHITE);
                            index2 = -1;
                        }
                        // Si le compteur est à 0, choisis départ
                        else if (compteur == 0){
                            compteur ++;
                            index1 = index;
                            liste_bouton[index/nbColonne][index%nbColonne].setBackground(Color.GREEN);
                        }
                        // Si le compteur est à 1, choisis l'arrivé
                        else if (compteur == 1){
                            compteur ++;
                            index2 = index;
                            liste_bouton[index/nbColonne][index%nbColonne].setBackground(Color.RED);
                        }
                    }
                });

                //Ajout au tableau de sauvegarde
                liste_bouton[i][j] = bouton;

                // Ajout graphiquement
                add(bouton, new GridLayout(i, j+1));
            }
        }

    }

    public int getCompteur() {
        return compteur;
    }
}
