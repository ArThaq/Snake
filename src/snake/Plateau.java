package snake;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Plateau extends JPanel {
	
	// Définition des attributs
	private JPanel mainframe;
	private JPanel cases_panel;
	private JButton[][] tab_cases;
	
	private int longueurPlateau;
	private int largeurPlateau;
	
	// Définition des constructeurs
	public Plateau(int x, int y) {
		this.longueurPlateau = x;
		this.largeurPlateau = y;

		this.initPlateau();
		this.createCasePanel();
		this.createTabCase();		
	}
	
	// Création des widgets	
	public void initPlateau() {
		new JPanel();
		setSize(new Dimension(300, 300));
		setBackground(Color.BLACK);
		setLayout(new GridLayout(1,1));
	}
	
	public void createCasePanel() {
		this.cases_panel = new JPanel();
		this.cases_panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		this.cases_panel.setBackground(Color.GREEN);
		this.cases_panel.setOpaque(false);
		this.cases_panel.setMinimumSize(new Dimension(300, 300));
		this.cases_panel.setLayout(new GridLayout(largeurPlateau, longueurPlateau));	
		add(this.cases_panel);
	}
	
	public void createMainframe() {
		this.mainframe = new JPanel();
		this.mainframe.setBackground(Color.BLACK);	
		this.mainframe.setOpaque(false);
		//this.mainframe.setBorder(new LineBorder(Color.BLACK, 5));
		this.mainframe.setSize(300,300);		
	}
	
	public void createTabCase() {
		this.tab_cases = new JButton[this.longueurPlateau][this.largeurPlateau];
		for(int j=0; j < this.largeurPlateau; j++) {
			for(int i=0; i < this.longueurPlateau; i++) {
				this.tab_cases[i][j] = createCase();	
				this.cases_panel.add(tab_cases[i][j]);			
			}
		}		
	}
	
	public JButton createCase() {
		JButton new_case = new JButton();
		
		// Gestion couleur
		new_case.setBackground(Color.BLACK);
		new_case.setOpaque(true);
		
		// Gestion bordure
		Border border = new LineBorder(Color.BLACK, 1);
		new_case.setBorder(border);
		
		new_case.setEnabled(false); // bouton non clickable
		
		return new_case;
	}
	
	// Définition des getters et setters	

	
	// Définition des méthodes
	public void clear() {
		for(int i=0; i < this.longueurPlateau; i++) {
			for(int j=0; j < this.largeurPlateau; j++) {
				this.tab_cases[i][j].setBackground(Color.DARK_GRAY);	
			}
		}		
	}
	
	public void show_serpent(Serpent s) {
		int longueur = s.getLongueur();
		
		Coordonnee coor_tete_serpent = s.getNoeud(0);
		this.tab_cases[coor_tete_serpent.get_x()][coor_tete_serpent.get_y()].setBackground(new Color(0,153,0));	
		for(int i=1; i < longueur; i++) {
			coor_tete_serpent = s.getNoeud(i);
			this.tab_cases[coor_tete_serpent.get_x()][coor_tete_serpent.get_y()].setBackground(new Color(0,103,0));			
		}
	
	}
	
	public void show_pomme(Pomme p) {
		Coordonnee coor_pomme = p.get_coodonnee();
		this.tab_cases[coor_pomme.get_x()][coor_pomme.get_y()].setBackground(Color.RED);
	}	
}
