package game;

import field.*;
import cell.*;
import utils.*;

public class GameManager {
	
	private final int playerNum = 2;
	private final int monsterNum = 2;
	
	private Player[] playerGroup = new Player[this.playerNum];
	private Player[] monsterGroup = new Player[this.monsterNum];
	
	private Pair[] playerCoordinate = new Pair[this.playerNum];
	private Pair[] monsterCoordinate = new Pair[this.monsterNum];
	
	private Field gameField;
	private View gameView;
	
	public GameManager(String mapPath) {
		this.gameField = Maps.Map2Field(mapPath);
	}
	
	public GameManager(Field theField) {
		this.gameField = theField;
		this.gameView = new View(this.gameField);
	}
	
	public GameManager(int height, int width) {
		this.gameField = new Field(height, width);
	}
	
}
