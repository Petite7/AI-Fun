package game;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import field.*;
import cell.*;
import utils.*;

public class GameManager {
	
	private ArrayList<Player> playerGroup = new ArrayList<Player>();
	private ArrayList<Player> monsterGroup = new ArrayList<Player>();
	
	private ArrayList<Pair> playerCoordinate = new ArrayList<Pair>();
	private ArrayList<Pair> monsterCoordinate = new ArrayList<Pair>();
	
	private final int playerPicTot = 2;
	private int playerPicNum = 0;
	
	private long currentRound;
	private int playersAlive;
	
	private Field gameField;
	private View gameView;
	private JFrame gameFrame;
	
	public GameManager(String mapPath, int numOfMonster) {
		this.gameField = Maps.Map2Field(mapPath);
		this.gameInit(numOfMonster);
		this.show();
	}
	
	public GameManager(Field theField, int numOfMonster) {
		this.gameField = theField;
		this.gameInit(numOfMonster);
		this.show();
	}
	
	public GameManager(int height, int width, int numOfMonster) {
		this.gameField = new Field(height, width);
		this.gameInit(numOfMonster);
		this.show();
	}
	
	private Pair getRandomCoordinate() {
		Pair p = new Pair();
		Random ram = new Random();
		p.first = ram.nextInt(this.gameField.getHeight());
		p.second = ram.nextInt(this.gameField.getWidth());
		while(this.gameField.get(p.first, p.second).get(0).getBlockType() != BlockType.EMPTY) {
			p.first = ram.nextInt(this.gameField.getHeight());
			p.second = ram.nextInt(this.gameField.getWidth());
		}
		return p;
	}
	
	private void gameInit(int numOfMonster) {
		
		File[] users = Files.getFiles("usr", ".jar");
		
		//Initiate playerGroup by file name
		for(File now : users) {
			this.playerGroup.add(new Player(now.getName().substring(0, now.getName().indexOf('.'))));
			this.playerCoordinate.add(new Pair());
		}
		
		//Initiate monsterGroup by input
		for(int i = 0; i < numOfMonster; i++) {
			this.monsterGroup.add(new Player("Monster " + (i + '0')));
			this.monsterCoordinate.add(new Pair());
		}
		
		//Position Initialize : Players
		for(Pair p : this.playerCoordinate) {
			p = this.getRandomCoordinate();
			this.gameField.get(p.first, p.second).get(0).setBlockType(BlockType.PLAYER);
			
			//Set Player Pictures
			String picPath = "pic" + (((this.playerPicNum ++) % this.playerPicTot) + '0') + ".jpg";
			this.gameField.get(p.first, p.second).get(0).setPath(picPath);
		}
		
		//Position Initialize : Monster
		for(Pair p : this.monsterCoordinate) {
			p = this.getRandomCoordinate();
			this.gameField.get(p.first, p.second).get(0).setBlockType(BlockType.MONSTER);
		}

		this.currentRound = 0;
		this.playersAlive = this.playerGroup.size();
		
	}
	
	private void show() {
		this.gameView = new View(this.gameField);
		this.gameFrame = new JFrame();
		this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.gameFrame.setResizable(false);
		this.gameFrame.setTitle("Welcome");
		this.gameFrame.add(this.gameView);
		this.gameFrame.pack();
		this.gameFrame.setVisible(true);
	}
	
	private boolean gameOver() {
		return (
				(this.playersAlive < 0.5*this.playerGroup.size()) 
				|| (this.currentRound > (this.gameField.getHeight() * this.gameField.getWidth()))
				);
	}
	
	private void nextRound() {
		for(Player now : this.playerGroup) {
			
		}
	}
	
	public void gameContinue() {
		
	}
	
}
