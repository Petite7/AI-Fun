package game;

public class GameManager {
	
	private enum PStatus{
		LIVE, DEAD, SUPER;
	}
	
	private class Player{
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
	
	private Player player1;
	private Player player2;
	private Player monster;
	
	
}
