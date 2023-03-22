import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Graph extends JFrame{
    private JButton validerButton;
    private JButton calculerButton;
    private JButton chargerButton;
    private JTextField[] liste_texte;
    private JPanel place_tableau;
    private JPanel principale;
    private JPanel placeBtnGauche;
    private JPanel placeBtnDroite;
    private JPanel placeChoixTaille;
    private JLabel textNbLignes;
    private JLabel textNbColonnes;
    private JTextField entreeNbLigne;
    private JTextField entreeNbColonnes;
    private JButton validerDimensionButton;
    private JButton nouveauButton;
    private JButton nouveauTrajetButton;
    private JButton enregisterCarteButton;
    private JButton button2;
    private GraphTableauIN graphTableauIN1;
    private GraphTableauButton graphTableauButton1;
    private GraphTableauResultat graphTableauResultat1;
    private String valeurs;
    private int nb_ligne;
    private int nb_colonne;

    TableauDonnee tableau;

    public Graph() {
        // Initialisation
        setContentPane(principale);
        setTitle("Programme INF");
        setSize(720, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//        Color couleur = new Color(0xFF1E1F22, true);
//        principale.setBackground(couleur);

        setVisible(true);
        setResizable(false);

        nb_colonne = 4;
        nb_ligne = 4;


        // Affichage du tableau d'entrée des valeurs
        affichage_tableau_in();


        // Une fois que toutes les valeurs sont entrées
        // Bloque la modification des cases
        // Création d'un tableau
        validerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texte = graphTableauIN1.getString();
                valeurs = texte;
                System.out.println(texte);
                try {
                    tableau = new TableauDonnee(nb_ligne, nb_colonne, texte);
                    afficher_tableau_boutton();
                }
                catch (IllegalArgumentException error){
                    JOptionPane.showMessageDialog(principale, error.getMessage());
                }
            }
        });

        // Calcul chemin plus court
        calculerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphTableauButton1.getCompteur() != 2){
                    JOptionPane.showMessageDialog(principale, "Veuillez selectionner 2 points");
                }
                else {
                    afficher_tableau_resultat();
                }
            }
        });
        nouveauButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                affichage_tableau_in();
            }
        });

        validerDimensionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ligne, colonne;
                try {
                    ligne = Integer.parseInt(entreeNbLigne.getText());
                    colonne = Integer.parseInt(entreeNbColonnes.getText());

                    if (ligne*colonne > 1600){
                        JOptionPane.showMessageDialog(principale, "Trop de case !!!");
                    }
                    else {
                        if (ligne * colonne > 100) {
                            JOptionPane.showMessageDialog(principale, "Attention, le nombre de case risque d'être trop important");
                        }
                        nb_ligne = ligne;
                        nb_colonne = colonne;
                        affichage_tableau_in();
                    }


                }
                catch (Exception f){
                    JOptionPane.showMessageDialog(principale, "Veuillez entrer des nombres seulement");
                }
            }
        });

        nouveauTrajetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficher_tableau_boutton();
            }
        });

        chargerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers projet (*.prj)", "prj");
                fileChooser.setFileFilter(filter);
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        String line;
                        nb_ligne = Integer.parseInt(reader.readLine());
                        nb_colonne = Integer.parseInt(line = reader.readLine());
                        valeurs = reader.readLine();
                        reader.close();

                        try {
                            affichage_tableau_in();
                            tableau = new TableauDonnee(nb_ligne, nb_colonne, valeurs);
                            afficher_tableau_boutton();
                        }
                        catch (IllegalArgumentException error){
                            JOptionPane.showMessageDialog(principale, error.getMessage());
                        }

                    }
                    catch (Exception f){
                        f.printStackTrace();
                    }
                }
            }
        });

        enregisterCarteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Récupérer l'emplacement et le nom de fichier sélectionnés
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // Écrire du contenu dans le nouveau fichier
                        BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile + ".prj"));
                        writer.write(nb_ligne + System.lineSeparator());
                        writer.write(nb_colonne + System.lineSeparator());
                        writer.write(valeurs);
                        writer.close();
                    } catch (Exception f) {
                        f.printStackTrace();
                    }
                }
            }
        });
    }

    private void affichage_tableau_in(){
        //Ajoute le tableau
        place_tableau.removeAll();
        graphTableauIN1 = new GraphTableauIN(nb_ligne, nb_colonne);
        place_tableau.add(graphTableauIN1);
        placeChoixTaille.setVisible(true);
        chargerButton.setVisible(true);
        validerButton.setVisible(true);
        // Tant que le tableau n'est pas validé, on ne peut pas calculer
        calculerButton.setVisible(false);
        nouveauButton.setVisible(false);
        nouveauTrajetButton.setVisible(false);
        enregisterCarteButton.setVisible(false);
        revalidate();
    }

    public void afficher_tableau_boutton(){
        validerButton.setVisible(false);
        chargerButton.setVisible(false);
        placeChoixTaille.setVisible(false);
        nouveauTrajetButton.setVisible(false);

        calculerButton.setVisible(true);
        enregisterCarteButton.setVisible(true);
        graphTableauButton1 = new GraphTableauButton(nb_ligne, nb_colonne, valeurs);
        place_tableau.removeAll();
        place_tableau.add(graphTableauButton1);
        //Actualise pour afficher
        revalidate();
    }

    public void afficher_tableau_resultat(){
        calculerButton.setVisible(false);
        enregisterCarteButton.setVisible(false);
        nouveauButton.setVisible(true);
        nouveauTrajetButton.setVisible(true);
        int id = graphTableauButton1.index1 / nb_colonne;
        int jd = graphTableauButton1.index1 % nb_colonne;
        int ia = graphTableauButton1.index2 / nb_colonne;
        int ja = graphTableauButton1.index2 % nb_colonne;
        System.out.println(id + " " +  jd + " " + ia + " " + ja);

        System.out.println("OK");
        String chemin = tableau.Resolution(id + 1, jd + 1, ia + 1, ja + 1);
        System.out.println(chemin);
        graphTableauResultat1 = new GraphTableauResultat(nb_ligne, nb_colonne, valeurs, chemin);
        place_tableau.removeAll();
        place_tableau.add(graphTableauResultat1);
        revalidate();
        System.out.println("LA");
    }


    public static void main(String[] args) {
        Graph myFrame = new Graph();
    }

}
