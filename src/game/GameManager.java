package game;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private HashMap<String, Class<?>> playerModules = new HashMap<String, Class<?>>();
	
	public final int normalScore = 100;
	
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
	
	@Deprecated	
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
		
		//Load Player Modules
		for(Player now : this.playerGroup) {
			Loaders.load("usr/" + now.getName() + ".jar");
			try {
				this.playerModules.put(now.getName(), Class.forName(now.getName().toLowerCase() + "." + now.getName()));
			} catch (ClassNotFoundException e) {
				System.err.println("Failure when loading player module, module name : [" + now.getName() + "]");
				e.printStackTrace();
			}
		}
		
		//Initiate monsterGroup by input
		for(int i = 0; i < numOfMonster; i++) {
			this.monsterGroup.add(new Player("Monster " + (i + '0')));
			this.monsterCoordinate.add(new Pair());
		}
		
		//Position Initialize : Players
		for(int i = 0; i < this.playerCoordinate.size(); i++) {
			Pair p = this.getRandomCoordinate();
			this.playerCoordinate.get(i).first = p.first;
			this.playerCoordinate.get(i).second = p.second;
			this.gameField.get(p.first, p.second).get(0).setBlockType(BlockType.PLAYER);
			
			//Set Player ID
			this.gameField.get(p.first, p.second).get(0).setID(i);
		}
		
		//Position Initialize : Monster
		for(Pair p : this.monsterCoordinate) {
			Pair ram = this.getRandomCoordinate();
			p.first = ram.first;
			p.second = ram.second;
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
		
		for(int i = 0; i < this.playerGroup.size(); i++) {
			
			Player now = this.playerGroup.get(i);
			
			if(now.getStatus() == PStatus.DEAD) {
				//TODO : Maybe Other Things To Do
				continue;
			}
			
			if(now.getStatus() == PStatus.SUPER)
				now.countDown();
			
			int x = this.playerCoordinate.get(i).first;
			int y = this.playerCoordinate.get(i).second;
			String name = now.getName();
			
			ActionRes thisMoveRes = null;
			
			//Reflect Player Action From Module Class
			try{
				thisMoveRes = CharacterMove.action(
					this.gameField, 
					x,
					y,
					i,
					(Action)this.playerModules.get(name).getDeclaredMethod("nextAction", Field.class, int.class, int.class, ArrayList.class)
						.invoke(null, this.gameField, x, y, this.playerGroup)
					);
			}catch(Exception e) {
				System.err.println("Failure when getting player action, player name : [" + name + "]");
				e.printStackTrace();
			}
			
			Pair infected = thisMoveRes.playerInfected;
			
			//TODO : GM Information Broadcast(Kill, Killed, Super etc.)
			
			/*
			 * Player Move Result Settlement Rules...
			 * 
			 * SCORE_NORMAL : When player get a start, they get 100 score
			 * SCORE_SUPER : When player get a super star, they get 300 score, and become super player for 20 rounds
			 * SCORE_KILLP : When one player(or monster) kill another player, they get half of the total score of the player
			 * SCORE_KILLM : Same as above ↑
			 * DEAD_HITWALL : Stupid Player hit wall
			 * DEAD_PLAYER : Current Player(or monster) being killed by the player at their destination, those players get half of the score
			 * DEAD_MONSTER : Same as above ↑
			 * DEAD_OUT ： Stupid Player get out of map
			 * 
			 * */
			
			switch(thisMoveRes.result) {
				case SCORE_NORMAL:{
					now.scoreSet(this.normalScore);
					
					break;
				}
				case SCORE_SUPER:{
					now.scoreSet(this.normalScore * 3);
					now.statusSet(PStatus.SUPER);
					
					break;
				}
				case SCORE_KILLP:{
					if(infected == null) {
						//TODO : Game Settlement Error Exception
					}
					
					for(int k = 0; k < this.playerCoordinate.size(); k++) {
						if(this.playerCoordinate.get(k).equals(infected.first, infected.second)) {
							this.playerGroup.get(k).statusSet(PStatus.DEAD);
							this.playersAlive = this.playersAlive - 1;
							now.scoreSet(this.playerGroup.get(k).getScore() / 2);
						}
					}
					
					break;
				}
				case SCORE_KILLM:{
					for(int k = 0; k < this.monsterCoordinate.size(); k++) {
						if(this.monsterCoordinate.get(k).equals(infected.first, infected.second)) {
							this.monsterGroup.get(k).statusSet(PStatus.DEAD);
							now.scoreSet(this.monsterGroup.get(k).getScore() / 2);
						}
					}
					
					break;
				}
				case DEAD_HITWALL:{
					now.statusSet(PStatus.DEAD);
					this.playersAlive = this.playersAlive - 1;
					
					break;
				}
				case DEAD_PLAYER:{
					now.statusSet(PStatus.DEAD);
					this.playersAlive = this.playersAlive - 1;
					
					if(infected == null) {
						//TODO : Game Settlement Error Exception
					}
					
					for(int k = 0; k < this.playerCoordinate.size(); k++) {
						if(this.playerCoordinate.get(k).equals(infected.first, infected.second)) {
							this.playerGroup.get(k).scoreSet(now.getScore() / 2);
						}
					}
					
					break;
				}
				case DEAD_MONSTER:{
					now.statusSet(PStatus.DEAD);
					this.playersAlive = this.playersAlive - 1;
					
					if(infected == null) {
						//TODO : Game Settlement Error Exception
					}
					
					for(int k = 0; k < this.monsterCoordinate.size(); k++) {
						if(this.monsterCoordinate.get(k).equals(infected.first, infected.second)) {
							this.monsterGroup.get(k).scoreSet(now.getScore() / 2);
						}
					}
					
					break;
				}
				case DEAD_OUT:{
					now.statusSet(PStatus.DEAD);
					this.playersAlive = this.playersAlive - 1;
					
					break;
				}
				default:{
					break;
				}
				
			}
			
			//TODO : monster move
			
			this.gameField = thisMoveRes.afterMove;
			
		}
	}
	
	public void gameContinue() {
		if(!this.gameOver()) {
			this.nextRound();
			this.gameView.repaint();
		}
	}
	
	
	public static void main(String[] args) {
		GameManager gm = new GameManager("maps/map1", 3);
	}
	
}
