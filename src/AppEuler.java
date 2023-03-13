//   temps de calcul
//  0x0 -> immédiat
//  0x4 -> immédiat
//  0x7 -> 1 mn
//  4x3 -> 10 mn
//  4x5 -> + 12h
//  2x2 -> plusieurs jours

package EulerV3;

import java.util.Calendar;

public class AppEuler {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pile <Euler> P = new Pile<Euler>();
		int i,j;
		Euler coupPrecedent;
		Echequier jeu;
		int  choixPrecedent,nouveauChoix, numeroCoup;
		
		Calendar calDebut = Calendar.getInstance();   /// memorise l'heure de debut de programme
        Calendar calFin;
		
		System.out.print("Position Ligne ?=");
		i=0;
		System.out.println("");
		System.out.print("Colonne ?=");
		j=7;
		
		jeu=new Echequier();
		jeu.set(i, j, 1);
		jeu.affiche();
		
		choixPrecedent=0;
		numeroCoup=1;
		
		while (!jeu.fini()){    // le jeu est fini quand l'echiquier est plein
			nouveauChoix=jeu.choixSuivantPossible(i, j, choixPrecedent);
														// cherche un nouveau deplacement
			
			if (nouveauChoix!=-1){ 						// il en existe un dï¿½placement 
				P.Empile(new Euler(i,j,nouveauChoix)); 	//on empile de dï¿½placement
				if (nouveauChoix==1){i=i-2;j=j+1;};  	// on va sur la nouvelle case 
				if (nouveauChoix==2){i=i-1;j=j+2;};
				if (nouveauChoix==3){i=i+1;j=j+2;};
				if (nouveauChoix==4){i=i+2;j=j+1;};
				if (nouveauChoix==5){i=i+2;j=j-1;};
				if (nouveauChoix==6){i=i+1;j=j-2;};
				if (nouveauChoix==7){i=i-1;j=j-2;};
				if (nouveauChoix==8){i=i-2;j=j-1;};
				numeroCoup=numeroCoup+1;						// on a fait un pas de plus
				jeu.set(i, j, numeroCoup);					// on marque l'echequier
				choixPrecedent=0;		
				
			}
			else { 										//depile
				coupPrecedent = P.SommetPile();			// on recupere le coup precedent
				P.Depile();								// on le supprime
				jeu.reset(i,j);                         // on libere l'echiquier
				i=coupPrecedent.posPrecedentX;
				j=coupPrecedent.posPrecedentY;			// on revient sur le case d'avant
				choixPrecedent=coupPrecedent.choixPrecedent; // on se replace dans le choix Precedent
				numeroCoup=numeroCoup-1;						// un coup de moins
			}
			
		}// fin de boucle pricipale

		System.out.println("Fin");
		calFin = Calendar.getInstance();			// heure de sortie de boucle
	    int tempsCalcul;
	    tempsCalcul = (calFin.get(Calendar.HOUR_OF_DAY)*60*60  +  calFin.get(Calendar.MINUTE)*60 + calFin.get(Calendar.SECOND))
	    		 -  (calDebut.get(Calendar.HOUR_OF_DAY)*60*60  +  calDebut.get(Calendar.MINUTE)*60 + calDebut.get(Calendar.SECOND));
	    
	    System.out.println("Calcul en "+tempsCalcul); 
		
	    jeu.affiche();
	}
	
}
