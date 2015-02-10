 import java.awt.*;  
 import java.awt.image.BufferedImage;  
 import java.awt.image.DataBufferByte;  
 import javax.swing.*;   
 import org.opencv.core.Mat;   
 import org.opencv.highgui.VideoCapture;
 
 public class Webcam extends JPanel {
	 
      private static final long serialVersionUID = 1L; 
      private static final double VERSION_NUMBER = 1.0;
      
      private static BufferedImage image;

      public Webcam(){  
           super();   
      }  
      
      public static void main(String args[]) {  
 		 System.loadLibrary("opencv_java2410");  
 		 
 		 JFrame frame = new JFrame("JWebcam");
 		 Webcam panel = new Webcam();
 		 
 		 // Initialize JPanel parameters
 		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
 		 frame.setSize(400, 400);
 		 frame.setContentPane(panel);       
 		 frame.setVisible(true);
 		 
 		 Mat currentImage = new Mat();
 		 Filter boost = new Filter(image, 100, 0, 0);
 		 MirrorFilter reflect = new MirrorFilter(image);
 		 EdgeDetectFilter edgeDetect = new EdgeDetectFilter(image, 5);
 		 BWFilter bw = new BWFilter(image);
 		 
 		 // VideoCapture and Mat are classes from OpenCV
 		 VideoCapture capture = new VideoCapture(0);   
 		 if(capture.isOpened()) {
 			 // Infinitely update the images
 			 while(true) {
 				 // VideoCapture returns current Mat
 				 capture.read(currentImage);
 				 if(!currentImage.empty()) {   
 					 frame.setSize(currentImage.width() + 40, currentImage.height() + 60);   
 					 image = panel.matrixToBuffer(currentImage);
 					 boost.setFrame(image);
 					 
 					// TRIPPY
 					boost.setDeltaRed((int) (Math.random() * 255));
 					boost.setDeltaGreen((int) (Math.random() * 255));
 					boost.setDeltaBlue((int) (Math.random() * 255));
 					 
 					 boost.filterAll();
 			    	 image = boost.getFrame();
 			    	 
 					 // Update the panel
 					 panel.repaint();
 				 }  
 				 else {   
 					 System.out.println("Error: no frame captured");
 					 break;
 				 }  
 			 }  
 		 }  
 		 return;  
 	 }  
      /**  
       * Converts/writes a Mat into a BufferedImage.  
       *   
       * @param matrix Mat of type CV_8UC3 or CV_8UC1  
       * @return BufferedImage of type TYPE_3BYTE_BGR or TYPE_BYTE_GRAY  
       */  
      public BufferedImage matrixToBuffer(Mat matBGR){  
           int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels();
           byte[] sourcePixels = new byte[width * height * channels];
           matBGR.get(0, 0, sourcePixels);
           // create new image and get reference to backing data
           image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
           final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
           System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
           //image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
           return image;
      }
      public void paintComponent(Graphics g){
    	  super.paintComponent(g);   
    	  if (image == null) {
    		  return;  
    	  }
    	 
    	  g.drawImage(image, 10, 10, image.getWidth(), image.getHeight(), null);
    	  g.setColor(Color.WHITE);
    	  g.drawString("JWebcam v" + VERSION_NUMBER + " by Ethan Lee", 30, 40);  
      }  
 }  
