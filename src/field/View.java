package field;

import cell.*;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class View extends JPanel {
	private static final long serialVersionUID = 23333333L;
	private static final int GRID_SIZE = 16;
	private Field field;
	
	public View(Field theField) {
		this.field = theField;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for(int i = 0; i < field.getHeight(); i++) {
			for(int j = 0; j < field.getWidth(); j++) {
				int top = field.get(i, j).size();
				Cell now = field.get(i, j).get(top - 1);
				if(now != null) 
					now.draw(g, i*GRID_SIZE, j*GRID_SIZE, GRID_SIZE);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(field.getHeight()*GRID_SIZE+1, field.getWidth()*GRID_SIZE+1);
	}
	
//	public static void main(String[] args) {
//		Field field = new Field(30,12);
//		for ( int row = 0; row<field.getHeight(); row++ ) {
//			for ( int col = 0; col<field.getWidth(); col++ ) {
//				field.place(row, col, new Cell(BlockType.SUP_STAR));
//			}
//		}
//		View view = new View(field);
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setResizable(false);
//		frame.setTitle("Cells");
//		frame.add(view);
//		frame.pack();
//		frame.setVisible(true);
//	}
}
