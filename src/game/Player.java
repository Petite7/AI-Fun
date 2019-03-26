package game;

public class Player{
	private String pName;
	private int pScore;
	private PStatus pstatus;
	
	public Player(String theName) {
		this.pName = theName;
		this.pScore = 0;
		this.pstatus = PStatus.LIVE;
	}
	
	public void statusSet(PStatus now) {
		this.pstatus = now;
	}
	
	public void scoreSet(int change) {
		this.pScore = this.pScore + change;
	}
	
	public String getName() {return this.pName;}
	public int getScore() {return this.pScore;}
	public PStatus getStatus() {return this.pstatus;}
	
}
