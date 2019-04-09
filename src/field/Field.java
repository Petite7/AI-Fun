package field;

import cell.*;

public class Field {
	private int width;
	private int height;
	private Cell[][] field;
	
	public Field(int theHeight, int theWidth) {
		this.height = theHeight;
		this.width = theWidth;
		
		field = new Cell[this.height][this.width];
	}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	public Cell place(int x, int y, Cell o) {
		Cell prev = field[x][y];
		field[x][y] = o;
		return prev;
	}
	
	public Cell get(int x, int y) {
		return field[x][y];
	}
	
	public boolean outOfBound(int x, int y) {
		return (x < 0 || x >= height || y < 0 || y >= width);
	}
	
	public void clear() {
		for(int i = 0; i < this.height; i++)
			for(int j = 0; j < this.width; j++)
				field[i][j] = null;
		//GC Automatically collect space...
	}
}
