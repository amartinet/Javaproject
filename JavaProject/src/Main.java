import java.awt.Color;
import java.awt.event.KeyEvent;

public class Main {

	public static final Color BOOK_BLUE = new Color(51, 171, 249);
	// public static boolean gameRunning = true;
	public static boolean Joueur1;
	public static boolean Joueur2;
	public static boolean Joueur3;
	public static boolean oiseau1;
	public static boolean oiseau2;
	public static boolean oiseau3;

	public static boolean jouer(Vaisseau vaisseau) {

		if (120 < StdDraw.mouseX() && 190 > StdDraw.mouseX()
				&& 60 < StdDraw.mouseY() && 140 > StdDraw.mouseY()) {
			if (StdDraw.mousePressed()) {
				vaisseau.changerUrl("Resources/J1.png");
				return true;
			}
		}

		if (220 < StdDraw.mouseX() && 280 > StdDraw.mouseX()
				&& 50 < StdDraw.mouseY() && 110 > StdDraw.mouseY()) {
			if (StdDraw.mousePressed()) {
				vaisseau.changerUrl("Resources/J3.png");
				return true;
			}

		}
		if (300 < StdDraw.mouseX() && 360 > StdDraw.mouseX()
				&& 40 < StdDraw.mouseY() && 100 > StdDraw.mouseY()) {
			if (StdDraw.mousePressed()) {
				vaisseau.changerUrl("Resources/J2.png");
				return true;
			}

		}
		if (380 < StdDraw.mouseX() && 450 > StdDraw.mouseX()
				&& 60 < StdDraw.mouseY() && 140 > StdDraw.mouseY()) {
			if (StdDraw.mousePressed()) {
				vaisseau.changerUrl("Resources/J4.png");
				return true;

			}
		}
		return false;
	}

	public static boolean fin(Vaisseau vaisseau) {

		StdDraw.text(350, 200, vaisseau.getNom() + " a perdu !");
		StdDraw.text(350, 160, "RECOMMENCER");
		StdDraw.text(350, 120, "MENU");
		return false;
	}

	public static void main(String[] args) throws InterruptedException {

		Fenetre maFenetre = new Fenetre();
		maFenetre.afficherFenetre();
		Tunnel monTunnel = new Tunnel();

		Boolean gameRunning = true;
		Vaisseau J1 = new Vaisseau("Joueur 1", 0, 220, 0, 0, 1000, 18, 0,
				"Resources/J1.png",10);
		Vaisseau J2 = new Vaisseau("Joueur 2", 0, 160, 0, 0, 1000, 18, 0,
				"Resources/J2.png",10);
		Vaisseau J3 = new Vaisseau("Joueur 3", 0, 100, 0, 0, 1000, 18, 0,
				"Resources/J3.png",10);
		//Bombe bombe1 = null;

		while ((!Joueur1) && (!Joueur2) && (!Joueur3)) {
			StdDraw.picture(400, 200, "Resources/Fond.jpg");
			if (250 < StdDraw.mouseX() && 350 > StdDraw.mouseX()
					&& 350 < StdDraw.mouseY() && 400 > StdDraw.mouseY()) {
				if (StdDraw.mousePressed()) {
					Joueur1 = true;
				}
			}

			if (570 < StdDraw.mouseX() && 750 > StdDraw.mouseX()
					&& 150 < StdDraw.mouseY() && 200 > StdDraw.mouseY()) {
				if (StdDraw.mousePressed()) {
					Joueur2 = true;
				}
			}

			if (200 < StdDraw.mouseX() && 390 > StdDraw.mouseX()
					&& 50 < StdDraw.mouseY() && 100 > StdDraw.mouseY()) {
				if (StdDraw.mousePressed()) {
					Joueur3 = true;
				}
			}
		}
		StdDraw.clear();



		while ((!oiseau1)) {
			if (Joueur1 == true || Joueur2 == true || Joueur3 == true) {
				StdDraw.picture(400, 200, "Resources/perso1.png");
				oiseau1 = jouer(J1);

			}
		}
		StdDraw.clear();

		while ((!oiseau2 && (Joueur2 == true || Joueur3 == true))) {
			if ((Joueur2 == true || Joueur3 == true) && oiseau1 == true) {
				StdDraw.picture(400, 200, "Resources/perso2.png");
				oiseau2 = jouer(J2);

			}

		}
		StdDraw.clear();

		while ((!oiseau3 && Joueur3 == true)) {
			if (Joueur3 == true && oiseau2 == true) {
				StdDraw.picture(400, 200, "Resources/perso3.png");
				oiseau3 = jouer(J3);
			}
		}
		
		boolean isBombe1 = false;
		boolean isBombe2 = false;
		boolean isBombe3 = false;
		
		while (gameRunning) {

			StdDraw.clear(BOOK_BLUE);
			

			if (Joueur1 == true || Joueur2 == true || Joueur3) {
				J1.updateOnKey(KeyEvent.VK_UP, KeyEvent.VK_DOWN,
						KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, maFenetre,
						monTunnel, J2, J3);
				J1.score();
				StdDraw.picture(J1.getX(), J1.getY(), J1.getImageUrl());
				StdDraw.text(100, 10, J1.getNom() + " [score: " + J1.getScore()
						+ "  vies: " + J1.getNbVies() + "]");
				if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
					isBombe1 = true;
					boolean isOk = false;
					for (Bombe bombe : J1.getListeBombe()){
						if(bombe.isActive()==false && isOk==false){
							bombe.setActive(true);
							bombe.setX(J1.getX()-30);
							bombe.setY(J1.getY());
							bombe.afficherBombe();
							isOk = true;
						}
					}
				}
				
				if(isBombe1)
				{
					for (Bombe bombe : J1.getListeBombe()){
						if(bombe.isActive()==true){
							bombe.deplacerBombe(J1);
							bombe.afficherBombe();
							if(bombe.getX()==0){
								bombe.setActive(false);
								
							}
						}
					}
				}

			}

			if (Joueur2 == true || Joueur3) {
				J2.updateOnKey(KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_D,
						KeyEvent.VK_Q, maFenetre, monTunnel, J1, J3);
				J2.score();
				StdDraw.picture(J2.getX(), J2.getY(), J2.getImageUrl());
				StdDraw.text(380, 10, J2.getNom() + " [score: " + J2.getScore()
						+ "  vies: " + J2.getNbVies() + "]");
				if (StdDraw.isKeyPressed(KeyEvent.VK_F)) {
					isBombe2 = true;
					boolean isOk = false;
					for (Bombe bombe : J2.getListeBombe()){
						if(bombe.isActive()==false && isOk==false){
							bombe.setActive(true);
							bombe.setX(J2.getX());
							bombe.setY(J2.getY());
							bombe.afficherBombe();
							isOk = true;
						}
					}
				}
				
				if(isBombe2)
				{
					for (Bombe bombe : J2.getListeBombe()){
						if(bombe.isActive()==true){
							bombe.deplacerBombe(J2);
							bombe.afficherBombe();
							if(bombe.getX()==0){
								bombe.setActive(false);
							}
						}
					}
				}

			}

			if (Joueur3 == true) {
				J3.updateOnKey(KeyEvent.VK_U, KeyEvent.VK_J, KeyEvent.VK_K,
						KeyEvent.VK_H, maFenetre, monTunnel, J1, J2);
				J3.score();
				StdDraw.picture(J3.getX(), J3.getY(), J3.getImageUrl());
				StdDraw.text(650, 10, J3.getNom() + " [score: " + J3.getScore()
						+ "  vies: " + J3.getNbVies() + "]");
				if (StdDraw.isKeyPressed(KeyEvent.VK_L)) {
					isBombe3 = true;
					boolean isOk = false;
					for (Bombe bombe : J3.getListeBombe()){
						if(bombe.isActive()==false && isOk==false){
							bombe.setActive(true);
							bombe.setX(J3.getX()-30);
							bombe.setY(J3.getY());
							bombe.afficherBombe();
							isOk = true;
						}
					}
				}
				
				if(isBombe3)
				{
					for (Bombe bombe : J3.getListeBombe()){
						if(bombe.isActive()==true){
							bombe.deplacerBombe(J3);
							bombe.afficherBombe();
							if(bombe.getX()==0){
								bombe.setActive(false);
							}
						}
					}
				}

			}

			monTunnel.print();
			monTunnel.move();

			if (J1.getNbVies() <= 0) {
				gameRunning = fin(J1);
			}

			if (J2.getNbVies() <= 0) {
				gameRunning = fin(J2);
			}

			if (J3.getNbVies() <= 0) {
				gameRunning = fin(J3);
			}

			Thread.sleep(200);
			StdDraw.show(1000 / 60);

		}

	}
}
