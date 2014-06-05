

public class Bombe  {

	private int x;
	private int y;
	private int mouvement;
	private boolean active;
	
	public Bombe (Vaisseau vaisseau, int mouvement){
		this.x = vaisseau.getX()-30;
		this.y = vaisseau.getY(); 
		this.mouvement = mouvement;
		this.active = false; 
	}
	

	public boolean isActive() {
		return active;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public int getMouvement() {
		return mouvement;
	}


	public void setMouvement(int mouvement) {
		this.mouvement = mouvement;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
	private boolean checkBombe (double x , double y, Vaisseau vaisseau){

		if (Math.abs(x-vaisseau.getX())>30 || Math.abs(y-vaisseau.getY())>30) {
			return true;
		}
		else{
			return false;
		}

	}



	
	public void afficherBombe(){
		StdDraw.picture(this.x, this.y, "Resources/bombe.png");	
}
	public void deplacerBombe(Vaisseau vaisseau){
		this.x = this.x - mouvement;
		StdDraw.picture(this.x, this.y, "Resources/bombe.png");
		
		if(checkBombe (this.x , this.y,vaisseau)){
			vaisseau.setNbVies(vaisseau.getNbVies()-1);
			setActive(false);
		}
	}
	

}


