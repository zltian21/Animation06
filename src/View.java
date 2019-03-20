import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class View extends JFrame{
	final int frameCount = 10;
    BufferedImage[] pics;
    
    private int xloc;
    private int yloc;
    private Direction direct;
    
    final int picSize = 165;
    final int frameStartSize = 800;
    final int drawDelay = 30;
    
    HashMap<Direction,BufferedImage[]> picsFile = new HashMap<>();
    
    DrawPanel drawPanel = new DrawPanel();
    Action drawAction;
    
    
    
    public int getFrameStartSize() {
		return frameStartSize;
	}
    
	public int getPicSize() {
		return picSize;
	}



	public View() {
    	
    	HashMap<Direction,BufferedImage> orcMoves = createImage();
    	for(Direction key: orcMoves.keySet()) {
    		BufferedImage img = orcMoves.get(key); //read image
    		pics = new BufferedImage[10];
    		for(int i = 0; i < frameCount; i++)
    			pics[i] = img.getSubimage(picSize*i, 0, picSize, picSize);
    		
    		picsFile.put(key, pics);	
    	}
    	
    	drawAction = new AbstractAction(){
    		public void actionPerformed(ActionEvent e){
    			//drawPanel.update(x, y, direction);
    			drawPanel.repaint();
    		}
    	};
    	add(drawPanel);
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(frameStartSize, frameStartSize);
    	setVisible(true);
    	pack();
    }
    
    @SuppressWarnings("serial")
    private class DrawPanel extends JPanel {
    	int picNum = 0;
    	protected void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		g.setColor(Color.gray);
	    	picNum = (picNum + 1) % frameCount;
	    	g.drawImage(picsFile.get(direct)[picNum], xloc, yloc, Color.gray, this);
    	}
    	
    	public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
    	
    	
    }
    
    public void update(int x, int y, Direction direction) {
    	xloc = x;
    	yloc = y;
    	direct = direction;
    }

    private HashMap<Direction,BufferedImage> createImage(){
    	BufferedImage bufferedImage;
    	HashMap<Direction,BufferedImage> orcMoves = new HashMap<>();
    	
    	try {
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
    		orcMoves.put(Direction.SOUTHEAST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_east.png"));
    		orcMoves.put(Direction.EAST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_north.png"));
    		orcMoves.put(Direction.NORTH, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northeast.png"));
    		orcMoves.put(Direction.NORTHEAST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northwest.png"));
    		orcMoves.put(Direction.NORTHWEST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_south.png"));
    		orcMoves.put(Direction.SOUTH, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southwest.png"));
    		orcMoves.put(Direction.SOUTHWEST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_west.png"));
    		orcMoves.put(Direction.WEST, bufferedImage);
    		
    		return orcMoves;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}	
    	return null;
    }
}
