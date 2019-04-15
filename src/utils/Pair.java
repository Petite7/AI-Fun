package utils;

public class Pair {
	public int first;
	public int second;
	
	public Pair() {
		
	}
	
	public Pair(Pair thePair) {
		this.first = thePair.first;
		this.second = thePair.second;
	}
	
	public Pair(int theFirst, int theSecond) {
		this.first = theFirst;
		this.second = theSecond;
	}
	
	public boolean equals(int theFirst, int theSecond) {
		return (this.first == theFirst) && (this.second == theSecond);
	}
}
