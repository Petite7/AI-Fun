package game;

import field.*;
import cell.*;

public class CharacterMove {
	
	public static ActionRes action(Field theField, int x, int y, Action action) {
		int nowX = x;
		int nowY = y;
		Field nowField = theField;
		
		//Current Character Location Move
		switch(action) {
			case MOVE_LEFT : {
				nowY --;
				break;
			}
			case MOVE_RIGHT : {
				nowY ++;
				break;
			}
			case MOVE_UP : {
				nowX --;
				break;
			}
			case MOVE_DOWN : {
				nowX ++;
				break;
			}
			default : break;
		}
		
		
		
		if(!nowField.outOfBound(nowX, nowY)) {
			BlockType moveType = nowField.get(x, y).cellType();
			BlockType destType = nowField.get(nowX, nowY).cellType();
			
			//Current Character Move Result Set
			nowField.place(x, y, new Cell(BlockType.EMPTY));
			switch(destType) {
				case STAR : {
					nowField.place(nowX, nowY, new Cell(moveType));
					return new ActionRes(nowField, moveType, Result.SCORE_NORMAL);
				}
				case SUP_STAR : {
					if(moveType.equals(BlockType.MONSTER)) {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, BlockType.MONSTER, Result.SCORE_SUPER);
					} else {
						nowField.place(nowX, nowY, new Cell(BlockType.SUPER_PLAYER));
						return new ActionRes(nowField, BlockType.SUPER_PLAYER, Result.SCORE_SUPER);
					}
						
				}
				case WALL : {
					return new ActionRes(nowField, BlockType.EMPTY, Result.DEAD_HITWALL);
				}
				case MONSTER : {
					if(moveType.equals(BlockType.SUPER_PLAYER)) {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.SCORE_KILLM);
					} else if(moveType == BlockType.MONSTER) {
						return new ActionRes(nowField, moveType, Result.NONE);
					} else {
						return new ActionRes(nowField, BlockType.EMPTY, Result.DEAD_MONSTER);
					}
						
				}
				case PLAYER1 : {
					if(moveType == BlockType.MONSTER) {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.SCORE_KILLP);
					} else if(moveType == BlockType.SUPER_PLAYER) {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.SCORE_KILLP);
					} else {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.NONE);
					}
						
				}
				case PLAYER2:{
					if(moveType == BlockType.MONSTER) {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.SCORE_KILLP);
					} else if(moveType == BlockType.SUPER_PLAYER) {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.SCORE_KILLP);
					} else {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.NONE);
					}
				}
				case SUPER_PLAYER:{
					if(moveType==BlockType.MONSTER) {
						return new ActionRes(nowField,BlockType.EMPTY, Result.DEAD_PLAYER);
					} else if(moveType == BlockType.PLAYER1 || moveType == BlockType.PLAYER2) {
						return new ActionRes(nowField,BlockType.EMPTY, Result.DEAD_PLAYER);
					} else {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.NONE);
					}
				}
				default : {
					nowField.place(nowX, nowY, new Cell(moveType));
					return new ActionRes(nowField, moveType, Result.NONE);
				}
			}
		} else {
			return new ActionRes(nowField, BlockType.EMPTY, Result.DEAD_OUT);
		}
	}
	public static void  main(String[] args) {
		Field field=new Field(2,1);
		Cell[] movers= {new Cell(BlockType.PLAYER1),new Cell(BlockType.PLAYER2),
						new Cell(BlockType.SUPER_PLAYER),new Cell(BlockType.MONSTER)};
		
		Cell[] things= {new Cell(BlockType.STAR),new Cell(BlockType.SUP_STAR),
						new Cell(BlockType.WALL),new Cell(BlockType.EMPTY)};
		//测试角色遇到物品
		for (int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				field.place(0, 0, movers[i]);
				field.place(1, 0, things[j]);
				ActionRes aRes=CharacterMove.action(field, 0, 0, Action.MOVE_DOWN);
				System.out.print("当"+movers[i].cellType()+"遇到"+things[j].cellType()+": ");
				//依次返回移动后类型、得分、field情况
				System.out.println(aRes.afterAction.toString()+" "+aRes.result.toString()+"  "+
								   aRes.afterMove.get(0, 0).cellType()+" "+aRes.afterMove.get(1, 0).cellType());
			}
			System.out.println();
		}
		//角色遇到角色
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(j!=i) {
					field.place(0, 0, movers[i]);
					field.place(1, 0, movers[j]);
					ActionRes aRes=CharacterMove.action(field, 0, 0, Action.MOVE_DOWN);
					System.out.print("当"+movers[i].cellType()+"遇到"+movers[j].cellType()+": ");
					System.out.println(aRes.afterAction.toString()+" "+aRes.result.toString()+"  "+
									   aRes.afterMove.get(0, 0).cellType()+" "+aRes.afterMove.get(1, 0).cellType());
				}
			}
			System.out.println();
		}
		//越界测试
		for(int i=0;i<4;i++) {
			field.place(0, 0, movers[i]);
			ActionRes aRes=CharacterMove.action(field, 0, 0, Action.MOVE_LEFT);
			System.out.println(aRes.afterAction.toString()+" "+aRes.result.toString()+"  "+
							   aRes.afterMove.get(0, 0).cellType()+" "+aRes.afterMove.get(1, 0).cellType());
		}

	}
}
