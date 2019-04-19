package field;

import java.util.ArrayList;

import cell.*;

public class Field {
	private int width;
	private int height;
	private ArrayList<Cell>[][] field;
	
	@SuppressWarnings("unchecked")
	public Field(int theHeight, int theWidth) {
		this.height = theHeight;
		this.width = theWidth;
		
		field = new ArrayList[this.height][this.width];
		
		for(int i = 0; i < this.height; i++)
			for(int j = 0; j < this.width; j++) {
				field[i][j] = new ArrayList<Cell>();
				field[i][j].add(new Cell(BlockType.EMPTY));
			}
	}
	
	public Field(Field theField) {
		this.width = theField.getWidth();
		this.height = theField.getHeight();
		this.field = theField.get();
	}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	public ArrayList<Cell>[][] get() {
		return this.field;
	}
	
	public void place(int x, int y, Cell o) {
		field[x][y].add(o);
	}
	
	public Cell replace(int x, int y, Cell o) {
		Cell prev = null;
		if(field[x][y].size() > 0)
			prev = field[x][y].get(0);
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
				field[i][j].clear();
	}
}
