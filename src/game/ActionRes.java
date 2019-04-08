package game;

import field.*;
import cell.*;

public class ActionRes {
	public Field afterMove;
	public BlockType afterAction;
	public Result result;
	
	public ActionRes(Field theField, BlockType theType, Result theResult) {
		this.afterMove = theField;
		this.afterAction = theType;
		this.result = theResult;
	}
}
