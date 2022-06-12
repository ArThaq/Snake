package snake;

public class Pomme {
	// D�finition des attributs
	private Coordonnee coor;
	
	// D�finition des constructeurs
	public Pomme() {
		this.coor = new Coordonnee();	
	}
	
	public Pomme(int x, int y) {
		this.coor = new Coordonnee(x,y);
	}
	
	public Pomme(Coordonnee c) {
		this.coor = c;
	}
	
	// D�finition des getters et setters
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
