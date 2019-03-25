package cell;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Cell {
	private BlockType blockType = BlockType.EMPTY ;
	
	
	public Cell() {}
	public Cell(BlockType type) {this.blockType = type;}
	
	public void cellFill(BlockType type) {
		this.blockType = type;
	}
	
	public BlockType cellType() {
		return this.blockType;
	}
	
	public void draw(Graphics g, int x, int y, int size) {
		switch(this.blockType) {
			case EMPTY : {
				g.drawRect(x, y, size, size);
				break;
			}
			case STAR : {
				Image img = new ImageIcon("star.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case SUP_STAR : {
				Image img = new ImageIcon("sup.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case PLAYER1 : {
				Image img = new ImageIcon("p1.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case PLAYER2 : {
				Image img = new ImageIcon("p2.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case MONSTER : {
				Image img = new ImageIcon("mons.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			default : {
				g.drawRect(x, y, size, size);
				break;
			}
		}
	}
}
