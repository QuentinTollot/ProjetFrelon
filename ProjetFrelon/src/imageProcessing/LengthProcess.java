package imageProcessing;
import java.util.List;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class LengthProcess {
	//Méthode pour le grand rectangel 
    static Rect boundingRectOfAllContours(List<MatOfPoint> contours) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for (MatOfPoint contour : contours) {
            Rect rect = Imgproc.boundingRect(contour);
            minX = Math.min(minX, rect.x);
            minY = Math.min(minY, rect.y);
            maxX = Math.max(maxX, rect.x + rect.width);
            maxY = Math.max(maxY, rect.y + rect.height);
        }

        return new Rect(minX, minY, maxX - minX, maxY - minY);
    }
    

}
