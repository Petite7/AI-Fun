package cell;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Cell {
	private final int playerPicTot = 2;
	private BlockType blockType = BlockType.EMPTY ;
	private int playerID = -1;
	
	public Cell() {}
	public Cell(BlockType type) {this.blockType = type;}
	public Cell(Cell theCell) {
		this.blockType = theCell.getBlockType();
		this.playerID = theCell.getID();
	}
	
	public void setBlockType(BlockType type) {this.blockType = type;}
	public BlockType getBlockType() {return this.blockType;}
	public void setID(int ID) {this.playerID = ID;}
	public int getID() {return this.playerID;}
	
	public void draw(Graphics g, int x, int y, int size) {
		switch(this.blockType) {
			case EMPTY : {
				g.drawRect(x, y, size, size);
				break;
			}
			case STAR : {
				Image img = new ImageIcon("pic/star.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case SUP_STAR : {
				Image img = new ImageIcon("pic/sup.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case PLAYER : {
				Image img = new ImageIcon("pic/p" + (String.valueOf(this.playerID % this.playerPicTot)) + ".jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case MONSTER : {
				Image img = new ImageIcon("pic/mons.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case SUPER_PLAYER : {
				Image img = new ImageIcon("pic/super.jpg").getImage();
				g.drawImage(img, x, y, size, size, null);
				break;
			}
			case WALL : {
				g.drawRect(x, y, size, size);
				g.fillRect(x, y, size, size);
				break;
			}
			default : {
				g.drawRect(x, y, size, size);
				break;
			}
		}
	}
}
