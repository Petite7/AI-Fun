package cell;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Cell {
	private BlockType blockType = BlockType.EMPTY ;
	private String playerPath = null;
	
	public Cell() {}
	public Cell(BlockType type) {this.blockType = type;}
	public Cell(Cell theCell) {
		this.blockType = theCell.blockType;
		this.playerPath = theCell.playerPath;
	}
	public Cell(String path) {
		this.blockType = BlockType.PLAYER;
		this.playerPath = path;
	}
	
	public void setBlockType(BlockType type) {
		this.blockType = type;
	}
	
	public BlockType getBlockType() {
		return this.blockType;
	}
	
	public void setPath(String path) {
		this.playerPath = path;
	}
	
	public String getPath() {
		return this.playerPath;
	}
	
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
				Image img = new ImageIcon("pic/" + this.playerPath).getImage();
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
