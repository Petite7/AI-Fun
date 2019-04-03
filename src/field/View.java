package field;

import cell.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.naming.InitialContext;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {
	private static final long serialVersionUID = 23333333L;
	private static final int GRID_SIZE =30;
	private Field field;
	
	
	
	public void initial(Field theField) {
		this.field = theField;
		Random rand=new Random();
		int[][] num=new int[field.getWidth()][field.getHeight()];
		for ( int row = 0; row<field.getWidth(); row++ ) {
			for ( int col = 0; col<field.getHeight(); col++ ) {
				num[row][col]=rand.nextInt(4);
				switch (num[row][col]) {
				case 1:
					field.place(row, col, new Cell(BlockType.STAR));
					break;
				case 2:
					field.place(row, col, new Cell(BlockType.EMPTY));
					break;
				case 3:
					field.place(row, col, new Cell(BlockType.SUP_STAR));
					break;
				case 0:
					field.place(row, col, new Cell(BlockType.WALL));
				default:
					break;
				}
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int i = 0; i < field.getHeight(); i++) {
			for(int j = 0; j < field.getWidth(); j++) {
				Cell now = field.get(i, j);
				if(now != null) 
					now.draw(g, i*GRID_SIZE, j*GRID_SIZE, GRID_SIZE);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(field.getWidth()*GRID_SIZE+1, field.getHeight()*GRID_SIZE+1);
	}
	
}
