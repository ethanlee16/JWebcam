import java.awt.image.*;
public class BWFilter extends Filter {
	
	public BWFilter(BufferedImage firstFrame) {
		super(firstFrame);
	}
	
	public boolean filterAll() {
		for (int row = 0; row < frame.getHeight() - 1; row++) { 
		     for (int col = 0; col < frame.getWidth() - 1; col++) {
		    	 int color = frame.getRGB(col, row);
		    	 int neutral = neutralizeColor(color);
		    	 updatePicture(col, row,
		    			neutral, neutral, neutral);
		     }
		}
		return true;
	}
	
	private int neutralizeColor(int rgb) {
		return (getRedFromRGB(rgb) + getGreenFromRGB(rgb) + getBlueFromRGB(rgb)) / 3;
	}
	
}
