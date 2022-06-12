package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Game extends JFrame implements KeyListener {	
	
	private JPanel mainPanel;	
	public Plateau board;
	public JLabel score;
	public Serpent snake;
	public Pomme   apple;
		
	private int longeur;
	private int largeur;
	
	public boolean actionJoueur;
	
	static final int HAUT 	= 1;
	static final int DROITE = 2;
	static final int BAS 	= 3;
	static final int GAUCHE = 4;
	
	public Game() {
		this.longeur = 15;
		this.largeur = 15;
		
		this.initComponent();
		this.initWindow();		
		addListener();
		launch_game();			
		while(check_collision() == false) {	
			this.refresh();
			this.attendreAction(100);
			if(this.actionJoueur == false) {
				this.snake.avancerSerpent(0);
			}
		}
		close_game();				
	}
	
	public void initWindow() {
		new JFrame();
		setSize(new Dimension(1000, 1000));	
		setTitle("Projet Snake");
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2,1));		
		mainPanel.add(this.board);
		mainPanel.add(this.score);
		add(mainPanel);
	}
	
	public void initComponent() {		
		this.board  = new Plateau(this.longeur,this.largeur);
		this.score = new JLabel("Score", SwingConstants.CENTER);
		this.score.setFont(new Font("Serif", Font.PLAIN, 30));
		this.snake  = new Serpent();
		this.addNewPomme();
	}
	
	public void attendreAction(int t) {
		try 
		{
			Thread.sleep(t);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}	
	}
	
	public void addNewPomme() {
		this.apple = new Pomme(randCoordonnee());
		while(check_collision_pomme() == true) {
			this.apple = new Pomme(randCoordonnee());
		}		
	}
	
	public void refresh() {
		this.actionJoueur = false;
		this.score.setText("Score : "+String.valueOf(this.snake.getLongueur()-2));
		this.board.clear();
		this.board.show_serpent(this.snake);
		this.board.show_pomme(this.apple);
	}	
	
	public void launch_game() {
		setVisible(true);	
	}
	
	public void close_game() {
		setVisible(false);	
	}
	
	// Gestion coordonnée aléatoire	
	public Coordonnee randCoordonnee() {
		Coordonnee coord = new Coordonnee();
		coord.set_x(rand(0, this.longeur));
		coord.set_y(rand(0, this.largeur));
		return coord;
	}
	
	public int rand(int a, int b) {
	   Random random = new Random();
	   int nb;
	   nb = a + random.nextInt(b-a);
	   return nb;
	}
	
	// Gestion collision
	public boolean check_collision() {
		boolean rep = false;
		
		if(check_collision_pomme() == true) {
			System.out.println("Manger Pomme");
			this.snake.grossir();
			this.addNewPomme();
		}
		
		if(check_collision_border() == true) {
			rep = true;
		}
		
		if(check_collision_serpent() == true) {
			rep = true;
		}			
		
		return rep;
	}
	
	public boolean check_collision_border() {
		boolean rep = false;
		
		int x = this.snake.getNoeud(0).get_x();
		if(x < 0 || x >= this.longeur) {
			System.out.println("COLLISION !");
			rep = true;
		}

		int y = this.snake.getNoeud(0).get_y();		
		if(y < 0 || y >= this.largeur) {
			System.out.println("COLLISION BORD !");
			rep = true;
		}		
		
		return rep;
	}
	
	public boolean check_collision_serpent() {		
		Coordonnee coord_tete = this.snake.getNoeud(0);
		int longueur = this.snake.getLongueur();	
		for (int i=1; i < longueur; i++) {
			if(this.snake.getNoeud(i).est_egale(coord_tete)) {
				System.out.println("COLLISION TETE !");
				return true;
			}
		}				
		return false;
	}
	
	public boolean check_collision_pomme() {	
		int longueur = this.snake.getLongueur();	
		Coordonnee coord_pomme = this.apple.get_coodonnee();
					
			if(this.snake.getNoeud(0).est_egale(coord_pomme)) {
				System.out.println("COLLISION POMME !");
				return true;
			
		}	
		return false;
	}	
	
	// gestion des évènements
	public void addListener() {
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) { 
			requestFocus();	
			}
		});
	}
	
	public void keyPressed(KeyEvent evt) {
		System.out.println("Le joueur appuie sur une touche");
		if(this.actionJoueur == false) {
			switch(evt.getKeyCode()) {
				case KeyEvent.VK_UP:
					System.out.println("touche UP appuyé");
					this.snake.avancerSerpent(HAUT);
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("touche RIGHT appuyé");
					this.snake.avancerSerpent(DROITE);
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("touche DOWN appuyé");
					this.snake.avancerSerpent(BAS);
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("touche LEFT appuyé");
					this.snake.avancerSerpent(GAUCHE);
					break;
				default:
					System.out.println("mauvaise touche appuyé");
					break;
			}		
			this.actionJoueur = true;
		}
		else {
			System.out.println("Action déja utilisée");
		}
	}
	
	public void keyReleased(KeyEvent evt) {} 
	
	public void keyTyped(KeyEvent evt) {}	
}
