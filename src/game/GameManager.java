package game;

import field.*;

import java.util.ArrayList;
import java.util.Random;

import cell.*;
import utils.*;

public class GameManager {
	
	private ArrayList<Player> playerGroup = new ArrayList<Player>();
	private ArrayList<Player> monsterGroup = new ArrayList<Player>();
	
	private ArrayList<Pair> playerCoordinate = new ArrayList<Pair>();
	private ArrayList<Pair> monsterCoordinate = new ArrayList<Pair>();
	
	private Field gameField;
	private View gameView;
	
	public GameManager(String mapPath) {
		this.gameField = Maps.Map2Field(mapPath);
		this.gameView = new View(this.gameField);
	}
	
	public GameManager(Field theField) {
		this.gameField = theField;
		this.gameView = new View(this.gameField);
	}
	
	public GameManager(int height, int width) {
		this.gameField = new Field(height, width);
		this.gameView = new View(this.gameField);
	}
	
	private Pair getRandomCoordinate(BlockType fill) {
		Pair p = new Pair();
		Random ram = new Random();
		p.first = ram.nextInt(this.gameField.getHeight());
		p.second = ram.nextInt(this.gameField.getWidth());
		while(this.gameField.get(p.first, p.second).get(0).getBlockType() != BlockType.EMPTY) {
			p.first = ram.nextInt(this.gameField.getHeight());
			p.second = ram.nextInt(this.gameField.getWidth());
		}
		gameField.get(p.first, p.second).get(0).setBlockType(fill);
		return p;
	}
	
	public void gameInit() {
		
		//Position Initialize : Players
		for(Pair p : this.playerCoordinate) 
			p = this.getRandomCoordinate(BlockType.PLAYER);
		//Position Initialize : Monster
		for(Pair p : this.monsterCoordinate)
			p = this.getRandomCoordinate(BlockType.MONSTER);
		
	}
	
}
