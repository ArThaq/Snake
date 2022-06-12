package snake;

import java.util.Random;

public class Coordonnee {
	
	// D�finitions des attributs
	private int x;
	private int y;
	
	// D�finitions des constructeurs
	public Coordonnee () {
		this.x = 0;
		this.y = 0;		
	}
	
	public Coordonnee (int a, int b) {
		this.x = a;
		this.y = b;		
	}
	
	public Coordonnee (Coordonnee c) {
		this.x = c.get_x();
		this.y = c.get_y();		
	}

	// D�finitions des getters et setters
	public int get_x() {
		return this.x;		
	}
	
	public void set_x(int a) {
		this.x = a;		
	}
	
	public int get_y() {
		return this.y;
	}
	
	public void set_y(int b) {
		this.y = b;		
	}
	
	// D�finitions des m�thodes
	public boolean est_egale(Coordonnee c2) {
		if (this.x == c2.get_x() && this.y == c2.get_y()) {
			return true;
		} else {
			return false;
		}
	}

}



