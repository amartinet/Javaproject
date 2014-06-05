import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;



public class Vaisseau {

	private final String nom;

	private int x;
	private int y;

	private int inertie_x;
	private int inertie_y;
	private int nbVies;
	private int speed;
	private int score;
	private String imageUrl;
	private List<Bombe> listeBombe;

	//private Bombe bombe;
	//public String color;

	public Vaisseau(String nom, int x, int y, int inertie_x, int inertie_y, int vies, int speed, int score, String url, int nombreBombes) {
		this.nom = nom;
		this.x = x;
		this.y = y;
		this.inertie_x = inertie_x;
		this.inertie_y = inertie_y;
		this.nbVies = vies;
		this.speed = speed;
		this.score = score;
		this.imageUrl = url;
		this.listeBombe = new ArrayList<Bombe>();
		for(int i=0; i<nombreBombes; i++) {
			listeBombe.add(new Bombe(this,10));
		}

	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setInertie_x(int inertie_x) {
		this.inertie_x = inertie_x;
	}

	public void setInertie_y(int inertie_y) {
		this.inertie_y = inertie_y;
	}

	public void setNbVies(int nbVies) {
		this.nbVies = nbVies;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Bombe> getListeBombe() {
		return listeBombe;
	}

	public void setListeBombe(List<Bombe> listeBombe) {
		this.listeBombe = listeBombe;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public String getNom() {
		return nom;
	}

	public int getInertie_x(){
		return inertie_x;
	}

	public int getInertie_y(){
		return inertie_y;
	}

	public int getSpeed(){
		return speed;
	}

	public int getScore(){
		return score;
	}

	public int getNbVies() {
		return nbVies;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	
	public void changerUrl(String url){
		this.imageUrl = url; 
	}
	

	private static boolean between(int i, int a, int b) {
		return i <= b && i >= a ;
	}

	// public static List<Vaisseau> listeVaisseau = new ArrayList<Vaisseau>();


	public int score(){
		this.score = this.score + this.x/50;
		return this.score;
	}
	
	

	
	/*public void bombe(){
		if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
			StdDraw.picture(getX()-30,getY(), "Resources/bombe.png");
		}		
	}
	
	public void poserBombe(){
		if  (bombe()){
			StdDraw.picture(getX(),getY(), "Resources/bombe.png");
		}
		
	}*/
	
	private boolean checkFenetre(int tmpX, int tmpY, Fenetre maFenetre){
		if (between(tmpX, maFenetre.getX_min(), maFenetre.getX_max()) 
				&& (between(tmpY, maFenetre.getY_min(), maFenetre.getY_max()))){
			return true;
		}

		else{

			return false;	
		}
	}


	private double getDistance ( double tmpX, double tmpY, int x2, int y2){

		double resultat = 0;
		resultat = Math.sqrt(Math.pow((x2 - tmpX),2) + Math.pow((y2-tmpY), 2));
		return resultat;
	}


	private boolean checkVaisseau (double tmpX, double tmpY, Vaisseau autreVaisseau, Vaisseau autreVaisseau2){

		if ((Math.abs(tmpX-autreVaisseau.getX())>43 || Math.abs(tmpY-autreVaisseau.getY())>43) && (Math.abs(tmpX-autreVaisseau2.getX())>43 || Math.abs(tmpY-autreVaisseau2.getY())>43) ){
			return true;
		}
		else{
			return false;
		}

	}
	
	
	private int[] checkTunnel (double tmpX, double tmpY, Tunnel monTunnel){

		int[][] couloirHaut = monTunnel.getCouloirHaut();
		int[][] couloirBas = monTunnel.getCouloirBas();



		for (int i=0 ; i < monTunnel.getNB_NUAGES() ; i++){
			if (getDistance(tmpX, tmpY, couloirHaut[i][0], couloirHaut[i][1])<50){
				int[] resultat = {0, 0, couloirHaut[i][1]};
				return resultat;
			}


			if (getDistance(tmpX, tmpY, couloirBas[i][0], couloirBas[i][1])<50){
				int[] resultat = {0, 1, couloirBas[i][1]};
				return resultat;
			}
		}

		int[] resultat = {1, 0, 0};
		return resultat;

	}


	public void updateOnKey(int up, int down, int right, int left, Fenetre maFenetre, Tunnel monTunnel, Vaisseau autreVaisseau, Vaisseau autreVaisseau2) {


		int tmpX = x; //valeur actuelle
		int tmpY = y;



		if(StdDraw.isKeyPressed(up)){
			tmpY = y + speed;
		}

		if(StdDraw.isKeyPressed(down)){
			tmpY = y - speed;
		}


		if (StdDraw.isKeyPressed(right)){
			tmpX = x + speed;
		}

		if (StdDraw.isKeyPressed(left)){
			tmpX = x - speed;
		}



		inertie_x = inertie_x + (tmpX - x) / 4 ;
		inertie_y = inertie_y + (tmpY - y) / 4;

		tmpX = inertie_x + tmpX;
		tmpY = inertie_y + tmpY;

		inertie_x = (int) (inertie_x / 1.2);
		inertie_y = (int) (inertie_y / 1.2);



		if (checkFenetre(tmpX, tmpY, maFenetre)){ //vÈrif vaisseau dans la fenÍtre




			int[] collision = checkTunnel(tmpX, tmpY, monTunnel); // ne comprend pas ‡ quoi correspond ce code

			if(collision[0] == 0)
			{
				if(collision[1] == 0)
				{
					x = tmpX;
					y = collision[2] - 48;
				}
				else
				{
					x = tmpX;
					y = collision[2] + 48;
				}

				nbVies--;
			}
			else if(checkVaisseau(tmpX, tmpY, autreVaisseau, autreVaisseau2))
			{
				x = tmpX;
				y = tmpY;


			}
			
		}







	}
}


