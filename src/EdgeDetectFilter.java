import java.awt.image.*;

public class EdgeDetectFilter extends Filter {
	
	private int threshold;
	
	public EdgeDetectFilter(BufferedImage firstFrame) {
		super(firstFrame);
		threshold = 0;
	}
	
	public EdgeDetectFilter(BufferedImage firstFrame, int threshold) {
		super(firstFrame);
		this.threshold = threshold;
	}
	
	public boolean filterAll() {
		for (int row = 0; row < frame.getHeight() - 1; row++) { 
		     for (int col = 0; col < frame.getWidth() - 1; col++) {
		    	 int color = frame.getRGB(col, row);
		    	 int bottomColor = frame.getRGB(col, row + 1);
		    	 int rightColor = frame.getRGB(col + 1, row);
		    	 
		    	 if(getColorDistance(color, bottomColor) > threshold
		    			&& getColorDistance(color, rightColor) > threshold) {
		    		 updatePicture(col, row, 0, 0, 0);
		    	 }
		    	 else {
		    		 updatePicture(col, row, 255, 255, 255);
		    	 }
		     }
		}
		return true;
	}
	
	private double getColorDistance(int origRGB, int compRGB) {
		double redDist = getRedFromRGB(origRGB) - getRedFromRGB(compRGB);
		double greenDist = getGreenFromRGB(origRGB) - getGreenFromRGB(compRGB);
		double blueDist = getBlueFromRGB(origRGB) - getBlueFromRGB(compRGB);
		return Math.sqrt(redDist * redDist + greenDist * greenDist + blueDist * blueDist);
	}
}
