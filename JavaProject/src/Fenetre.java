public class Fenetre {

	private int x_min;
	private int x_max;
	private int y_min;
	private int y_max;    

	public Fenetre(){
		x_min = 0;
		x_max = 800;
		y_min = 0;
		y_max = 400;
	}

	public int getX_min() {
		return x_min;
	}

	public int getX_max() {
		return x_max;
	}

	public int getY_min() {
		return y_min;
	}

	public int getY_max() {
		return y_max;
	}

	public void afficherFenetre(){
		
		StdDraw.setCanvasSize(x_max, y_max); // taille de l'ecran
		StdDraw.setXscale(x_min, x_max); // echelle des X en pixel
		StdDraw.setYscale(y_min,  y_max); // echelle des Y en pixel


	}

}