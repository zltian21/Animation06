import java.util.Random;

public class Model {
	private int picSize;
    private int frameStartSize;
    
	private int xloc = 100;
    private int yloc = 100;
    private final int xIncr = 8;
    private final int yIncr = 2;
    
    boolean initial = true;
	Random r = new Random();
	Direction[] hitNorth = {Direction.SOUTHWEST, Direction.SOUTHEAST, Direction.SOUTH};
    Direction[] hitSouth = {Direction.NORTHWEST, Direction.NORTHEAST, Direction.NORTH};
    Direction[] hitEast = {Direction.NORTHWEST, Direction.SOUTHWEST, Direction.WEST};
    Direction[] hitWest = {Direction.NORTHEAST, Direction.SOUTHEAST, Direction.EAST};
    private Direction direct = Direction.SOUTHEAST;
    
    public Model(int frameStartSize, int picSize) {
    	this.frameStartSize = frameStartSize;
    	this.picSize = picSize;
    }

    
	public int getXloc() {
		return xloc;
	}

	public int getYloc() {
		return yloc;
	}

	public Direction getDirect() {
		return direct;
	}
	
	
public void updateLocationAndDirection() {
		
		if(initial) {
    		xloc+=xIncr;
    		yloc+=yIncr;
    		direct = Direction.SOUTHEAST;
    		initial = false;
    	}
		
		
		if(xloc < (frameStartSize-picSize) && (xloc > 0) && (yloc < (frameStartSize - picSize)) &&  (yloc > 0)) {

			switch(direct) {
			case NORTHWEST: 
				xloc-=xIncr;
				yloc-=yIncr;
				break;
			case SOUTHWEST:
				xloc-=xIncr;
				yloc+=yIncr;
				break;
			case NORTHEAST:
				xloc+=xIncr;
				yloc-=yIncr;
				break;
			case SOUTHEAST:
				xloc+=xIncr;
				yloc+=yIncr;
				break;
			case SOUTH:
				yloc+=(yIncr+2);
				break;
			case NORTH:
				yloc-=(yIncr+2);
				break;
			case WEST:
				xloc-=xIncr;
				break;
			case EAST:
				xloc+=xIncr;
				break;
				
			}
		}else if(xloc >= (frameStartSize-picSize)) {
    		direct = hitEast[r.nextInt(hitEast.length)];
    		switch(direct) {
	    		case NORTHWEST: 
					xloc-=xIncr;
					yloc-=yIncr;
					break;
				case SOUTHWEST:
					xloc-=xIncr;
					yloc+=yIncr;
					break;
				case WEST:
					xloc-=xIncr;
					break;
    		}
    	}else if(xloc <= 0 ) {
    		direct = hitWest[r.nextInt(hitWest.length)];
    		switch(direct) {
				case NORTHEAST:
					xloc+=xIncr;
					yloc-=yIncr;
					break;
				case SOUTHEAST:
					xloc+=xIncr;
					yloc+=yIncr;
					break;
				case EAST:
					xloc+=xIncr;
					break;
    		}
    	}else if(yloc <= 0) {
    		direct = hitNorth[r.nextInt(hitNorth.length)];
    		switch(direct) {
				case SOUTHWEST:
					xloc-=xIncr;
					yloc+=yIncr;
					break;
				case SOUTHEAST:
					xloc+=xIncr;
					yloc+=yIncr;
					break;
				case SOUTH:
					yloc+=(yIncr+2);
					break;
    		}
    	}else if(yloc >= (frameStartSize - picSize)) {
    		direct = hitSouth[r.nextInt(hitSouth.length)];
    		switch(direct) {
    		case NORTHWEST: 
				xloc-=xIncr;
				yloc-=yIncr;
				break;
			case NORTHEAST:
				xloc+=xIncr;
				yloc-=yIncr;
				break;
			case NORTH:
				yloc-=(yIncr+2);
				break;
    		}
    	}

	}
    
    
    
}
