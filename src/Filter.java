import java.awt.image.*;

public class Filter {
	
	private int deltaRed, deltaGreen, deltaBlue;
	public BufferedImage frame;
	
	public Filter(BufferedImage firstFrame) {
		deltaRed = 0;
		deltaBlue = 0;
		deltaGreen = 0;
		frame = firstFrame;
	}
	
	public Filter(BufferedImage img, int red, int green, int blue) {
		frame = img;
		deltaRed = red;
		deltaGreen = green;
		deltaBlue = blue;
	}
	
	public void setFrame(BufferedImage nextFrame) {
		frame = nextFrame;
	}
	
	public void setDeltaRed(int r) {
		deltaRed = r;
		//System.out.println(deltaRed);
	}
	
	public void setDeltaGreen(int g) {
		deltaGreen = g;
	}
	
	public void setDeltaBlue(int b) {
		deltaBlue = b;
	}
	
	public BufferedImage getFrame() {
		return frame;
	}
	
	public boolean filterAll() {
		//long time = System.nanoTime();
		boolean done = false;
		for (int row = 0; row < frame.getHeight(); row++) { 
		     for (int col = 0; col < frame.getWidth(); col++) {
		    	 //filterRed(col, row);
		    	 //filterBlue(col, row);
		    	 //filterGreen(col, row);
		    	 int color = frame.getRGB(col, row);
		    	 updatePicture(col, row, getRedFromRGB(color) + deltaRed, getGreenFromRGB(color) 
		    			 + deltaGreen, getBlueFromRGB(color) + deltaBlue);
		    	// updatePicture(col, row, getRedFromRGB(color) + deltaRed, 0, 0);
		    	 //System.out.println(frame.getRGB(col, row));
		     }
		}
		done = true;
		//System.out.println("Elapsed: " + (0.0000000001 * (System.nanoTime() - time)));
		return done;
	}
	
	public int getRedFromRGB(int rgb) {
		return (rgb >> 16) & 0xff;
	}
	
	public int getGreenFromRGB(int rgb) {
		return (rgb >> 8) & 0xff;
	}
	
	public int getBlueFromRGB(int rgb) {
		return (rgb >> 0) & 0xff;
	}
	
	public void filterRed(int x, int y) {
		int rgb = frame.getRGB(x, y);
		int redVal = getRedFromRGB(rgb) + deltaRed;
		updatePicture(x, y, redVal, getGreenFromRGB(rgb), getBlueFromRGB(rgb));
	}
	
	public void filterGreen(int x, int y) {
		int rgb = frame.getRGB(x, y);
		int greenVal = getGreenFromRGB(rgb) + deltaGreen;
		updatePicture(x, y, getRedFromRGB(rgb), greenVal, getBlueFromRGB(rgb));
	}
	
	public void filterBlue(int x, int y) {
		int rgb = frame.getRGB(x, y);
		int blueVal = getBlueFromRGB(rgb) + deltaBlue;
		updatePicture(x, y, getRedFromRGB(rgb), getGreenFromRGB(rgb), blueVal);
	}
	
	public void updatePicture(int x, int y, int red, int green, int blue) {
	    // create a 32 bit int with alpha, red, green blue from left to right
	    int value = (255 << 24) | (red << 16) | (green << 8) | (blue << 0);
	    //System.out.println("Red: " + red + "\tGreen: "+ green + "\tBlue: " + blue);
	    // update the picture with the int value
	    frame.setRGB(x, y, value);
	}
	
}
