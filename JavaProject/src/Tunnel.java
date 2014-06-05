public class Tunnel {

	private final int RANDOM_FACTOR = 30;
	private final int NB_NUAGES = 11;
	private int[][] couloirHaut;
	private int[][] couloirBas;


	public int getNB_NUAGES() {
		return NB_NUAGES;
	}

	public int[][] getCouloirHaut() {
		return couloirHaut;
	}

	public int[][] getCouloirBas() {
		return couloirBas;
	}

	public Tunnel(){

		couloirHaut = new int[NB_NUAGES][2];
		couloirBas = new int[NB_NUAGES][2];

		for(int i=0 ; i<NB_NUAGES ; i++){

			couloirHaut[i][0]= 80*i; //abscisse ie nuage du haut
			couloirHaut[i][1]= 400; //ordonnÈe ie nuage du haut

			couloirBas[i][0]= 80*i;
			couloirBas[i][1]= 0;

		}
	}

	public void print() {

		String url1 = "Resources/nuage1.png";
		String url2 = "Resources/nuage2.png";

		for(int i = 0 ; i < NB_NUAGES ; i++){
			StdDraw.picture(couloirHaut[i][0],couloirHaut[i][1],url1); // quelle est la nature de StdDraw.picture (arguments) ?
			StdDraw.picture(couloirBas[i][0],couloirBas[i][1],url2);//  
		}
	}


	public void move() {
		
		for(int i = 0 ; i < NB_NUAGES-1 ; i++){
			couloirHaut[i][1] = couloirHaut[i+1][1];
			couloirBas[i][1] = couloirBas[i+1][1];
		}


		//HAUT
		int tmpOrdonneeHaut =0;
		do{
			if(Math.random() > 0.5){ // Quelle est la nature de Math.random ?
				tmpOrdonneeHaut = couloirHaut[NB_NUAGES-2][1] + (int) (3 + Math.random()*RANDOM_FACTOR);
			}
			else{
				tmpOrdonneeHaut = couloirHaut[NB_NUAGES-2][1] - (int) (3 + Math.random()*RANDOM_FACTOR);
			}
		} while (!(tmpOrdonneeHaut>150)||!(tmpOrdonneeHaut<380)); //vÈrif zone verte. Sinon demande nouvelle valeur.

		

		//BAS
		int tmpOrdonneeBas = 0;
		do{
			if(Math.random() > 0.5){
				tmpOrdonneeBas = couloirBas[NB_NUAGES-2][1] + (int) (3 + Math.random()*RANDOM_FACTOR);
			}
			else{
				tmpOrdonneeBas = couloirBas[NB_NUAGES-2][1] - (int) (3 + Math.random()*RANDOM_FACTOR);
			}
		}while (!(tmpOrdonneeBas>20)||!(tmpOrdonneeBas<250)); //vÈrif zone grise. Sinon demande nouvelle valeur.


		if ((tmpOrdonneeHaut-tmpOrdonneeBas)>150){ //verif Ècart minimum entre haut et bas
			couloirHaut[NB_NUAGES-1][1] = tmpOrdonneeHaut;
			couloirBas[NB_NUAGES-1][1] = tmpOrdonneeBas;
		}

	}
}
