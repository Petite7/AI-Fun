package game;

import field.*;
import utils.Pair;
import cell.*;

public class CharacterMove {
	
	public static ActionRes action(Field nowField, int x, int y, int pid, Action action) {
		int nowX = x;
		int nowY = y;
		
		//Get Current Move Index
		int mover = 0;
		while(mover < nowField.get(x, y).size() && nowField.get(x, y).get(mover).getID() != pid)
			mover ++;
		
		//Current Character Location Move
		switch(action) {
			case MOVE_LEFT : 
				nowY --;
				break;
			case MOVE_RIGHT : 
				nowY ++;
				break;
			case MOVE_UP :
				nowX --;
				break;
			case MOVE_DOWN :
				nowX ++;
				break;
			default : break;
		}
		
		
		
		if(!nowField.outOfBound(nowX, nowY)) {
			BlockType moveType = nowField.get(x, y).get(mover).getBlockType();
			BlockType destType = nowField.get(nowX, nowY).get(0).getBlockType();
			
			Cell origin = nowField.get(x, y).get(mover);
			
			//Current Character Move Result Set
			if(nowField.get(x, y).size() > 1)
				nowField.get(x, y).remove(mover);
			else
				nowField.replace(x, y, new Cell(BlockType.EMPTY));
			
			switch(destType) {
				case STAR : {
					nowField.replace(nowX, nowY, origin);
					return new ActionRes(nowField, null, Result.SCORE_NORMAL);
				}
				case SUP_STAR : {
					if(moveType == BlockType.MONSTER) {
						nowField.replace(nowX, nowY, origin);
						return new ActionRes(nowField, null, Result.SCORE_SUPER);
					} else {
						origin.setBlockType(BlockType.SUPER_PLAYER);
						nowField.replace(nowX, nowY, origin);
						return new ActionRes(nowField, null, Result.SCORE_SUPER);
					}
						
				}
				case WALL : {
					return new ActionRes(nowField, null, Result.DEAD_HITWALL);
				}
				case MONSTER : {
					if(moveType == BlockType.SUPER_PLAYER) {
						nowField.replace(nowX, nowY, origin);
						return new ActionRes(nowField, new Pair(nowX, nowY), Result.SCORE_KILLM);
					} else if(moveType == BlockType.MONSTER) {
						nowField.place(nowX, nowY, origin);
						return new ActionRes(nowField, null, Result.NONE);
					} else {
						return new ActionRes(nowField, new Pair(nowX, nowY), Result.DEAD_MONSTER);
					}
						
				}
				case PLAYER : {
					if(moveType == BlockType.MONSTER) {
						nowField.replace(nowX, nowY, origin);
						return new ActionRes(nowField, new Pair(nowX, nowY), Result.SCORE_KILLP);
					} else if(moveType == BlockType.SUPER_PLAYER) {
						nowField.replace(nowX, nowY, origin);
						return new ActionRes(nowField, new Pair(nowX, nowY), Result.SCORE_KILLP);
					} else {
						nowField.place(nowX, nowY, origin);
						return new ActionRes(nowField, null, Result.NONE);
					}
						
				}
				case SUPER_PLAYER:{
					if(moveType == BlockType.MONSTER) {
						return new ActionRes(nowField, new Pair(nowX, nowY), Result.DEAD_PLAYER);
					} else if(moveType == BlockType.PLAYER) {
						return new ActionRes(nowField, new Pair(nowX, nowY), Result.DEAD_PLAYER);
					} else {
						nowField.place(nowX, nowY, origin);
						return new ActionRes(nowField, null, Result.NONE);
					}
				}
				default : {
					nowField.replace(nowX, nowY, origin);
					return new ActionRes(nowField, null, Result.NONE);
				}
			}
		} else {
			return new ActionRes(nowField, null, Result.DEAD_OUT);
		}
	}
	
//	public static void  main(String[] args) {
//		Field field=new Field(2,1);
//		Cell[] movers= {new Cell(BlockType.PLAYER1),new Cell(BlockType.PLAYER2),
//						new Cell(BlockType.SUPER_PLAYER),new Cell(BlockType.MONSTER)};
//		
//		Cell[] things= {new Cell(BlockType.STAR),new Cell(BlockType.SUP_STAR),
//						new Cell(BlockType.WALL),new Cell(BlockType.EMPTY)};
//		//测试角色遇到物品
//		for (int i=0;i<4;i++) {
//			for(int j=0;j<4;j++) {
//				field.place(0, 0, movers[i]);
//				field.place(1, 0, things[j]);
//				ActionRes aRes=CharacterMove.action(field, 0, 0, Action.MOVE_DOWN);
//				System.out.print("当"+movers[i].cellType()+"遇到"+things[j].cellType()+": ");
//				//依次返回移动后类型、得分、field情况
//				System.out.println(aRes.afterAction.toString()+" "+aRes.result.toString()+"  "+
//								   aRes.afterMove.get(0, 0).cellType()+" "+aRes.afterMove.get(1, 0).cellType());
//			}
//			System.out.println();
//		}
//		//角色遇到角色
//		for(int i=0;i<4;i++) {
//			for(int j=0;j<4;j++) {
//				if(j!=i) {
//					field.place(0, 0, movers[i]);
//					field.place(1, 0, movers[j]);
//					ActionRes aRes=CharacterMove.action(field, 0, 0, Action.MOVE_DOWN);
//					System.out.print("当"+movers[i].cellType()+"遇到"+movers[j].cellType()+": ");
//					System.out.println(aRes.afterAction.toString()+" "+aRes.result.toString()+"  "+
//									   aRes.afterMove.get(0, 0).cellType()+" "+aRes.afterMove.get(1, 0).cellType());
//				}
//			}
//			System.out.println();
//		}
//		//越界测试
//		for(int i=0;i<4;i++) {
//			field.place(0, 0, movers[i]);
//			ActionRes aRes=CharacterMove.action(field, 0, 0, Action.MOVE_LEFT);
//			System.out.println(aRes.afterAction.toString()+" "+aRes.result.toString()+"  "+
//							   aRes.afterMove.get(0, 0).cellType()+" "+aRes.afterMove.get(1, 0).cellType());
//		}
//
//	}
	
}
