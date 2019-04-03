package game;

import field.*;
import java.util.Random;
import javax.swing.JFrame;
import cell.*;

public class GameManager {
	private Player[] players= {new Player("player1"),new Player("player2"),new Player("monster")};
	private Field gameField=new Field(5,5);
	protected View view=new View();
	private JFrame frame=new JFrame();
	
	public GameManager() {
	}
	
	public void Initial() {
		this.view.initial(gameField);
		//初始化 角色位置
		Random ran=new Random();
		int[] h={0,0,0};
		int[] w={0,0,0};
		for (int i=0;i<3;i++) {
			w[i]=ran.nextInt(gameField.getWidth());
	        h[i]=ran.nextInt(gameField.getHeight());
		}
		
		gameField.setCell(w[1], h[1], new Cell(BlockType.PLAYER1));
		if(h[1]!=0) {
			gameField.setCell(w[1], h[1]-1, new Cell(BlockType.EMPTY));
		}else {
			gameField.setCell(w[1], h[1]+1, new Cell(BlockType.EMPTY));
		}
	
		 gameField.setCell(w[2], h[2], new Cell(BlockType.PLAYER2));
		 if(h[2]!=0) {
			gameField.setCell(w[2], h[2]-1, new Cell(BlockType.EMPTY));
		}else {
			gameField.setCell(w[2], h[2]+1, new Cell(BlockType.EMPTY));
		}
		 
		while(gameField.get(w[0],h[0]).equals(BlockType.PLAYER1)||gameField.get(w[0],h[0]).equals(BlockType.PLAYER1)) {
			w[0]=ran.nextInt(gameField.getWidth());
			h[0]=ran.nextInt(gameField.getHeight());
		}
		gameField.setCell(w[0], h[0], new Cell(BlockType.MONSTER));
	}

	//游戏回合进行 
	public void gameloop() {
		ActionRes[] actions=new ActionRes[3];
		boolean over=false;
		int overflag=0;
		//三个角色都存活的时候执行循环
		while(!over) {
			for(int i=0;i<3;i++) {
				if(!players[i].getStatus().equals(PStatus.DEAD)) {
					//角色移动后修改地图、角色得分和角色状态
					actions[i]=CharacterMove.action(gameField, players[i].locx(),players[i].locy(), Action.MOVE_DOWN);
					 gameField=actions[i].afterMove;
					 players[i].scoreSet(actions[i].gainScore);
					 view.repaint();
					 view.validate();
					 if(actions[i].afterAction.equals(BlockType.EMPTY)) {
							players[i].statusSet(PStatus.DEAD);
							overflag++;
						}
				}
			}
			if(overflag==3) {
				over=true;
			}
		}
	}
	
	
	public static void main(String arg[]) {
		GameManager gm=new GameManager();
		gm.Initial();
		gm.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gm.frame.setResizable(false);
		gm.frame.setTitle("Interesting Game");
		gm.frame.setVisible(true);
		gm.frame.add(gm.view);
		gm.frame.pack();
	    gm.gameloop();
	}
}
