package game;

public class Player{
	private String pName;
	private int pScore;
	private PStatus pstatus;
	
	private int superCountDown;
	
	public Player(String theName) {
		this.pName = theName;
		this.pScore = 0;
		this.pstatus = PStatus.LIVE;
		this.superCountDown = 0;
	}
	
	public void statusSet(PStatus now) {
		this.pstatus = now;
		if(now == PStatus.SUPER)
			this.superCountDown = 20;
	}
	
	public void countDown() {
		if(this.superCountDown > 0)
			this.superCountDown = this.superCountDown - 1;
		else if(this.superCountDown == 0)
			this.statusSet(PStatus.LIVE);
	}
	
	public void scoreSet(int change) {
		this.pScore = this.pScore + change;
	}
	
	public String getName() {return this.pName;}
	public int getScore() {return this.pScore;}
	public PStatus getStatus() {return this.pstatus;}
	
}
