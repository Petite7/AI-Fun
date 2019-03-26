package game;

import field.*;
import cell.*;

public class ActionRes {
	public Field afterMove;
	public BlockType afterAction;
	
	public ActionRes(Field theField, BlockType theType) {
		this.afterMove = theField;
		this.afterAction = theType;
	}
}
