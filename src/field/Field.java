package field;

import cell.*;

public class Field {
	private int width;
	private int height;
	private Cell[][] field;
	
	public Field(int theWidth, int theHeight) {
		this.width = theWidth;
		this.height = theHeight;
		field = new Cell[this.width][this.height];
	}
	
	public int getWidth() {return this.width;}
	public int getHeight() {return this.height;}
	
	//元素o放到[x,y]坐标处的cell内 返回原先放置的元素
	public Cell place(int x, int y, Cell o) {
		Cell prev = field[x][y];
		field[x][y] = o;
		return prev;
	}
	
	//获取[x,y]处cell内的元素类型
	public Cell get(int x, int y) {
		return field[x][y];
	}
	//是否越界
	public boolean outOfBound(int x, int y) {
		return (x < 0 || x >= height || y < 0 || y >= width);
	}
	
	//清空
	public void clear() {
		for(int i = 0; i < this.height; i++)
			for(int j = 0; j < this.width; j++)
				field[i][j] = null;
		//GC Automatically collect space...
	}
}
