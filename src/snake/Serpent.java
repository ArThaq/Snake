package snake;

import java.util.ArrayList;

public class Serpent {
	
	// Définition des attributs
	private int longueur;
	private int direction = DROITE;
	private Coordonnee old_queue; 
	private ArrayList<Coordonnee> body; 
	
	static final int HAUT 	= 1;
	static final int DROITE = 2;
	static final int BAS 	= 3;
	static final int GAUCHE = 4;
	
	// Définition des constructeurs
	public Serpent() {
		Coordonnee c1 = new Coordonnee(0,0);
		Coordonnee c2 = new Coordonnee(0,1);
		this.body = new ArrayList<>();
		this.addNoeud(c1);
		this.addNoeud(c2);
	}
	 
	public Serpent(int a, int b) {
		Coordonnee c1 = new Coordonnee(0,0);
		Coordonnee c2 = new Coordonnee(0,1);
		this.body = new ArrayList<>();
		this.addNoeud(c1);
		this.addNoeud(c2);
	}
	
	public Serpent(Coordonnee c) {
		Coordonnee c1 = new Coordonnee(0,0);
		Coordonnee c2 = new Coordonnee(0,1);
		this.body = new ArrayList<>();
		this.addNoeud(c1);
		this.addNoeud(c2);
	}

	// Définition des getters et setters
	public int getLongueur() {
		return this.longueur;
	}
	
	public int getDirection() {
		return this.direction;
	}	
	
	public void setDirection(int d) {
		this.direction = d;
	}
	
	public Coordonnee getOldQueue() {
		return this.old_queue;
	}	
	
	public void setOldQueue(Coordonnee c) {
		this.old_queue = c;
	}	
	
	public Coordonnee getNoeud(int i) {
		return this.body.get(i);
	}
	
	public void setNoeud(int i, Coordonnee c) {
		this.body.set(i, c);
	}	
	
	public void addNoeud(Coordonnee c) {
		this.body.add(c);
		this.longueur = this.body.size();
	}	

	// Définition des méthodes
	public void avancerSerpent(int direction) {
		System.out.println("Le serpent avance");	

		if (direction == 0) {
			direction = this.getDirection(); 
		}
		int longueur = this.getLongueur();	
		
		Coordonnee coor_old_next_noeud = new Coordonnee();
		Coordonnee coor_old_queue = new Coordonnee();
		Coordonnee coor_old_tete = new Coordonnee();
		Coordonnee coord_new_tete = new Coordonnee();
		
		int new_x = 0;
		int new_y = 0;
		
		if (direction == HAUT || direction == DROITE || direction == BAS || direction == GAUCHE ) {
			coor_old_queue = this.getNoeud(longueur-1);				
			coor_old_tete = this.getNoeud(0);

			switch(direction) {
			case HAUT: 
				System.out.println("Le serpent avance en haut");
				this.setDirection(HAUT);
				new_x = coor_old_tete.get_x() + 0;
				new_y = coor_old_tete.get_y() - 1;
				coord_new_tete = new Coordonnee(new_x, new_y);				
				break;			
			case DROITE: 
				System.out.println("Le serpent tourne à droite");
				this.setDirection(DROITE);
				new_x = coor_old_tete.get_x() + 1;
				new_y = coor_old_tete.get_y() + 0;
				coord_new_tete = new Coordonnee(new_x, new_y);				
				break;			
			case BAS: 
				System.out.println("Le serpent avanca en bas");
				this.setDirection(BAS);
				new_x = coor_old_tete.get_x() + 0;
				new_y = coor_old_tete.get_y() + 1;
				coord_new_tete = new Coordonnee(new_x, new_y);				
				break;				
			case GAUCHE: 
				System.out.println("Le serpent tourne à gauche");
				this.setDirection(GAUCHE);
				new_x = coor_old_tete.get_x() - 1;
				new_y = coor_old_tete.get_y() + 0;
				coord_new_tete = new Coordonnee(new_x, new_y);				
				break;		
			default:
				System.out.println("Erreur: le serpent n'avance pas");
				break;
			}		
			
			this.setOldQueue(coor_old_queue);
			for(int i=longueur-1; i > 0; i--) {
				coor_old_next_noeud = this.getNoeud(i-1);
				this.setNoeud(i, coor_old_next_noeud);					
			}	
			this.setNoeud(0, coord_new_tete);	
		}	
	}
	
	public void grossir() {
		int longueur = this.getLongueur();			
		Coordonnee coor_old_queue = this.getOldQueue();
		this.addNoeud(coor_old_queue);	
	}
}
