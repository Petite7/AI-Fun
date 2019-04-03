package game;

public class Player{
	private String pName;
	private int pScore;
	private PStatus pstatus;
	private int locx;
	private int locy;
	
	public Player(String theName) {
		this.pName = theName;
		this.pScore = 0;
		this.pstatus = PStatus.LIVE;
		this.locx=0;
		this.locy=0;
	}
	
	public void statusSet(PStatus now) {
		this.pstatus = now;
	}
	
	public void scoreSet(int change) {
		this.pScore = this.pScore + change;
	}
	
	public void setloc(int x,int y) {
		this.locx=x;
		this.locy=y;
	}
	
	public int locx() {
		return this.locx;
	}
	public int locy() {
		return this.locy;
	}
	public String getName() {return this.pName;}
	public int getScore() {return this.pScore;}
	public PStatus getStatus() {return this.pstatus;}
	
}
