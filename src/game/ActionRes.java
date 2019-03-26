package game;

import field.*;
import cell.*;

public class ActionRes {
	public Field afterMove;
	public BlockType afterAction;
	public int gainScore;
	
	public ActionRes(Field theField, BlockType theType, int theScore) {
		this.afterMove = theField;
		this.afterAction = theType;
		this.gainScore = theScore;
	}
}
