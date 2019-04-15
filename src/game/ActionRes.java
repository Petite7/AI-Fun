package game;

import field.*;
import utils.*;

public class ActionRes {
	public Field afterMove;
	public Pair playerInfected;
	public Result result;
	
	public ActionRes(Field theField, Pair thePlayer, Result theResult) {
		this.afterMove = theField;
		this.playerInfected = thePlayer;
		this.result = theResult;
	}
}
