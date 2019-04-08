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
		
		BlockType moveType = nowField.get(x, y).cellType();
		BlockType destType = nowField.get(nowX, nowY).cellType();
		
		//Current Character Move Result Set
		nowField.place(x, y, new Cell(BlockType.EMPTY));
		
		if(!nowField.outOfBound(nowX, nowY)) {
			switch(destType) {
				case STAR : {
					nowField.place(nowX, nowY, new Cell(moveType));
					return new ActionRes(nowField, moveType, Result.SCORE_NORMAL);
				}
				case SUP_STAR : {
					if(moveType == BlockType.MONSTER) {
						nowField.place(nowX, nowY, new Cell(moveType));
						return new ActionRes(nowField, moveType, Result.SCORE_SUPER);
					} else {
						nowField.place(nowX, nowY, new Cell(BlockType.SUPER_PLAYER));
						return new ActionRes(nowField, BlockType.SUPER_PLAYER, Result.SCORE_SUPER);
					}
						
				}
				case WALL : {
					return new ActionRes(nowField, BlockType.EMPTY, Result.DEAD_HITWALL);
				}
				case MONSTER : {
					if(moveType == BlockType.SUPER_PLAYER) {
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
						return new ActionRes(nowField,BlockType.MONSTER, Result.DEAD_PLAYER);
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
	
}
