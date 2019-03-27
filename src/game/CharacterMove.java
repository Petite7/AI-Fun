package game;

import field.*;
import cell.*;

public class CharacterMove {
	
	public static int scoreStar = 10;
	public static int hitWall = -400;
	public static int gotKilled = -1;
	public static int killMonster=200;
	public static int killplayer=100;
	public static int  outofbound=-10;
	
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
		nowField.place(x, y, new Cell(BlockType.EMPTY));
		
		boolean out=nowField.outOfBound(nowX, nowY);
		if(!out) {
			switch(destType) {
			case STAR : {
				nowField.place(nowX, nowY, new Cell(moveType));
				return new ActionRes(nowField, moveType, scoreStar);
			}
			case SUP_STAR : {
				if(moveType == BlockType.MONSTER) {
					nowField.place(nowX, nowY, new Cell(BlockType.MONSTER));
					return new ActionRes(nowField, moveType, scoreStar * 5);
				}	
				else {
					nowField.place(nowX, nowY, new Cell(BlockType.SUPER_PLAYER));
					return new ActionRes(nowField, BlockType.SUPER_PLAYER, scoreStar * 5);
				}
					
			}
			case WALL : {
				return new ActionRes(nowField, BlockType.EMPTY, hitWall);
			}
			case MONSTER : {
				if(moveType == BlockType.SUPER_PLAYER) {
					nowField.place(nowX, nowY, new Cell(BlockType.SUPER_PLAYER));
					return new ActionRes(nowField,BlockType.EMPTY,gotKilled);
				}
				else if(moveType == BlockType.MONSTER) {
					return new ActionRes(nowField,moveType,0);
				}
				else {
					return new ActionRes(nowField,BlockType.EMPTY, gotKilled);
				}
					
			}
			case PLAYER1 : {
				if(moveType==BlockType.MONSTER) {
					nowField.place(nowX, nowY, new Cell(BlockType.MONSTER));
					return new ActionRes(nowField,BlockType.EMPTY,killplayer);
				}
				else{
					nowField.place(nowX, nowY, new Cell(moveType));
					return new ActionRes(nowField,moveType,0);
				}
					
			}
			case PLAYER2:{
				if(moveType==BlockType.MONSTER) {
					nowField.place(nowX, nowY, new Cell(BlockType.MONSTER));
					return new ActionRes(nowField,BlockType.EMPTY,killplayer);
				}
				else {
					nowField.place(nowX, nowY, new Cell(moveType));
					return new ActionRes(nowField, moveType, 0);
				}
					
			}
			case SUPER_PLAYER:{
				if(moveType==BlockType.MONSTER) {
					nowField.place(nowX, nowY, new Cell(BlockType.SUPER_PLAYER));
					return new ActionRes(nowField,BlockType.EMPTY, killMonster);
				}
				else {
					nowField.place(nowX, nowY, new Cell(moveType));
					return new ActionRes(nowField, moveType, 0);
				}	
			}
			default : {
				nowField.place(nowX, nowY, new Cell(moveType));
				return new ActionRes(nowField, moveType, 0);
			}
			}
		}else {
			return new ActionRes(nowField, BlockType.EMPTY, outofbound);
		}
	}
	
}
