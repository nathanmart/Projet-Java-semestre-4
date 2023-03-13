package EulerV3;

public class Echequier {

/// ATTRIBUTS	
	
	private int [][] plateau;
	private int nbCaseOccupe;
	
/// CONSTRUTEURS	
	Echequier(){
		int i,j;
		plateau = new int [12][12] ;		
		nbCaseOccupe=0;
		
		for (i=2;i<10;i++){
			for (j=2; j<10;j++){
				plateau [i][j] =0;
			}
		}
		
		for (j=0;j<12;j++){
			plateau [0][j] =-1;
			plateau [1][j] =-1;
			plateau [10][j] =-1;
			plateau [11][j] =-1;};
		for (i=0;i<12;i++){
			plateau [i][0] =-1;
			plateau [i][1] =-1;
			plateau [i][10] =-1;
			plateau [i][11] =-1;}
		
		// affichage à la construction
		
		for (i=0;i<12;i++){
			for (j=0; j<12;j++){
				if (plateau [i][j]==0)
				System.out.print(" "+plateau [i][j]+" ");
				else System.out.print(plateau [i][j]+" ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	
//// METHODES	
	
	
	void set(int i, int j, int val){
		plateau[i+2][j+2] = val;
		nbCaseOccupe=nbCaseOccupe+1;
	}
	
	void reset (int i, int j){
		plateau[i+2][j+2] = 0;
		nbCaseOccupe=nbCaseOccupe-1;
	}
	
	boolean fini(){
		return nbCaseOccupe==64;
	};
	
//////////////////////////////////////////////////////////////////	
	int choixSuivantPossible(int ii, int jj, int choixPrecedent){
		int i,j;
		i=ii+2; j=jj+2;
		// i=4 j=8
	
	if (choixPrecedent==0){	if (plateau [i-2][j+1] ==0) return 1;
							if (plateau [i-1][j+2] ==0) return 2;
							if (plateau [i+1][j+2] ==0) return 3;
							if (plateau [i+2][j+1] ==0) return 4;
							if (plateau [i+2][j-1] ==0) return 5;
							if (plateau [i+1][j-2] ==0) return 6;
							if (plateau [i-1][j-2] ==0) return 7;
							if (plateau [i-2][j-1] ==0) return 8;
					}
	if (choixPrecedent==1) {//if (plateau [i-2][j+1] ==0) return 1;
							if (plateau [i-1][j+2] ==0) return 2;
							if (plateau [i+1][j+2] ==0) return 3;
							if (plateau [i+2][j+1] ==0) return 4;
							if (plateau [i+2][j-1] ==0) return 5;
							if (plateau [i+1][j-2] ==0) return 6;
							if (plateau [i-1][j-2] ==0) return 7;
							if (plateau [i-2][j-1] ==0) return 8;
					}
	if (choixPrecedent==2){//if (plateau [i-2][j+1] ==0) return 1;
						  //if (plateau [i-1][j+2] ==0) return 2;
							if (plateau [i+1][j+2] ==0) return 3;
							if (plateau [i+2][j+1] ==0) return 4;
							if (plateau [i+2][j-1] ==0) return 5;
							if (plateau [i+1][j-2] ==0) return 6;
							if (plateau [i-1][j-2] ==0) return 7;
							if (plateau [i-2][j-1] ==0) return 8;
					}
	if (choixPrecedent==3) {//if (plateau [i-2][j+1] ==0) return 1;
						  //if (plateau [i-1][j+2] ==0) return 2;
						  //if (plateau [i+1][j+2] ==0) return 3;
							if (plateau [i+2][j+1] ==0) return 4;
							if (plateau [i+2][j-1] ==0) return 5;
							if (plateau [i+1][j-2] ==0) return 6;
							if (plateau [i-1][j-2] ==0) return 7;
							if (plateau [i-2][j-1] ==0) return 8;
				}
	if (choixPrecedent==4){//if (plateau [i-2][j+1] ==0) return 1;
						  //if (plateau [i-1][j+2] ==0) return 2;
						  //if (plateau [i+1][j+2] ==0) return 3;
						  //if (plateau [i+2][j+1] ==0) return 4;
							if (plateau [i+2][j-1] ==0) return 5;
							if (plateau [i+1][j-2] ==0) return 6;
							if (plateau [i-1][j-2] ==0) return 7;
							if (plateau [i-2][j-1] ==0) return 8;
				}
	if (choixPrecedent==5) {//if (plateau [i-2][j+1] ==0) return 1;
						  //if (plateau [i-1][j+2] ==0) return 2;
						  //if (plateau [i+1][j+2] ==0) return 3;
						  //if (plateau [i+2][j+1] ==0) return 4;
						  //if (plateau [i+2][j-1] ==0) return 5;
							if (plateau [i+1][j-2] ==0) return 6;
							if (plateau [i-1][j-2] ==0) return 7;
							if (plateau [i-2][j-1] ==0) return 8;
				}
	if (choixPrecedent==6){//if (plateau [i-2][j+1] ==0) return 1;
						  //if (plateau [i-1][j+2] ==0) return 2;
						  //if (plateau [i+1][j+2] ==0) return 3;
						  //if (plateau [i+2][j+1] ==0) return 4;
						  //if (plateau [i+2][j-1] ==0) return 5;
						  //if (plateau [i+1][j-2] ==0) return 6;
							if (plateau [i-1][j-2] ==0) return 7;
							if (plateau [i-2][j-1] ==0) return 8;
				}
	if (choixPrecedent==7){//if (plateau [i-2][j+1] ==0) return 1;
						  //if (plateau [i-1][j+2] ==0) return 2;
						  //if (plateau [i+1][j+2] ==0) return 3;
						  //if (plateau [i+2][j+1] ==0) return 4;
						  //if (plateau [i+2][j-1] ==0) return 5;
						  //if (plateau [i+1][j-2] ==0) return 6;
						  //if (plateau [i-1][j-2] ==0) return 7;
							if (plateau [i-2][j-1] ==0) return 8;
			}
	if (choixPrecedent==8) return -1;	 
	
	return -1;
	}
/////////////////////////////////////////////////////////////////	
	void affiche(){
		int i,j;
		for (i=2;i<10;i++){
			for (j=2; j<10;j++){
				if (plateau [i][j]<10)
				System.out.print(" "+plateau [i][j]+" ");
				else System.out.print(plateau [i][j]+" ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

}
