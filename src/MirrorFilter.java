import java.awt.image.*;

public class MirrorFilter extends Filter {
	public MirrorFilter(BufferedImage firstFrame) {
		super(firstFrame);
	}
	
	public boolean filterAll() {
		for (int row = 0; row < frame.getHeight(); row++) { 
		     for (int col = 0; col < frame.getWidth() / 2; col++) {
		    	 int color = frame.getRGB(col, row);
		    	 updatePicture(frame.getWidth() - 1 - col, row, getRedFromRGB(color), 
		    			 getGreenFromRGB(color), getBlueFromRGB(color));
		     }
		}
		return true;
	}
}
