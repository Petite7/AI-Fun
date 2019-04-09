package field;

import java.util.ArrayList;

import cell.*;

public class Field {
	private int width;
	private int height;
	private ArrayList<Cell>[][] field;
	
	public Field(int theHeight, int theWidth) {
		this.height = theHeight;
		this.width = theWidth;
		
		for(int i = 0; i < this.height; i++)
			for(int j = 0; j < this.width; j++)
				field[i][j] = new ArrayList<Cell>();
	}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	public void place(int x, int y, Cell o) {
		field[x][y].add(o);
	}
	
	public Cell replace(int x, int y, Cell o) {
		Cell prev = field[x][y].get(0);
		field[x][y].clear();
		field[x][y].add(o);
		return prev;
	}
	
	public ArrayList<Cell> get(int x, int y) {
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
