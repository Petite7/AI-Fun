package game;

import field.*;
import cell.*;

public class CharacterMove {
	
	public static int scoreStar = 10;
	public static int hitWall = -400;
	public static int gotKilled = -1;
	
	public static ActionRes action(Field theField, int x, int y, Action action) {
		//TODO : Multiple actions ...
		
		int nowX = x;
		int nowY = y;
		Field nowField = theField;
		
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
		
		switch(destType) {
			case STAR : {
				return new ActionRes(nowField, moveType, scoreStar);
			}
			case SUP_STAR : {
				if(moveType == BlockType.MONSTER)
					return new ActionRes(nowField, moveType, scoreStar * 5);
				else 
					return new ActionRes(nowField, BlockType.SUPER_PLAYER, scoreStar * 5);
			}
			case WALL : {
				return new ActionRes(nowField, BlockType.EMPTY, hitWall);
			}
			case MONSTER : {
				return new ActionRes(nowField, BlockType.EMPTY, gotKilled);
			}
			case PLAYER1 : {
				//TODO : do it tomorrow
			}
			default : {
				return new ActionRes(nowField, moveType, 0);
			}
		}

	}
	
}
