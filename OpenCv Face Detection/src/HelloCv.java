

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class HelloCv {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//String path ="videos/dog.mp4";
		VideoCapture cap = new VideoCapture(0);
		Mat img = new Mat();
		
		
		String xmlFile = "xml/lbpcascade_frontalface_improved.xml";
		CascadeClassifier cc = new CascadeClassifier(xmlFile);
		while(true)
		{
			cap.read(img);
			MatOfRect faceDetection = new MatOfRect();
			cc.detectMultiScale(img, faceDetection);
			System.out.println(String.format("Detected faces: %d", faceDetection.toArray().length));
			
			for(Rect rect: faceDetection.toArray())
			{
				Imgproc.rectangle(img, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height) , new Scalar(0, 0, 255), 3);
			}	
			HighGui.imshow("Test", img);
			HighGui.waitKey(1);
		}		
	}
}