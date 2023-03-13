import java.util.Vector;

// <T> signifie que l'on peut stocker n'importe quel type de donnée
public class Pile <T>{
    private Vector <T> table;

    // Initialisation
    public Pile(){
        table = new Vector<T>();
    }

    // Ajoute un élement sur le dessus de la pile
    public void Emplier (T objet){
        table.add(table.size(), objet);
    }

    // Retourne le dernier élement
    public T getDernierElement(){
        return table.elementAt(table.size() - 1);
    }

    //Supprime le dernier élement
    public void depiler(){
        table.remove(table.size() - 1);
    }

    // Retourne le nombre d'élements
    int getTaille(){
        return table.size();
    }
}
