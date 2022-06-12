package snake;

public class Pomme {
	// Définition des attributs
	private Coordonnee coor;
	
	// Définition des constructeurs
	public Pomme() {
		this.coor = new Coordonnee();	
	}
	
	public Pomme(int x, int y) {
		this.coor = new Coordonnee(x,y);
	}
	
	public Pomme(Coordonnee c) {
		this.coor = c;
	}
	
	// Définition des getters et setters
	public Coordonnee get_coodonnee() {
		return this.coor;
	}
	
	public int get_x() {
		return this.coor.get_x();
	}
	
	public int get_y() {
		return this.coor.get_y();
	}
}
