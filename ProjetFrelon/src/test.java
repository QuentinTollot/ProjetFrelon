import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class test {
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// Charger la première image de test
		//String imagePath = "ProjetFrelon/data/Trap/hornet01.jpg";
		String imagePath = "ProjetFrelon/data/Trap/hornet01.jpg";

		Mat image = Imgcodecs.imread(imagePath);
		
		/***************************************
		 * 		Partie Pré-traitement          *
		 ***************************************/

		
		
		
		
		/***************************************
		 * 		Partie Edge Detection          *
		 ***************************************/
		
		
		// Creating an empty matrices to store edges, source, destination
		Mat contraste = new Mat(image.rows(), image.cols(), image.type());
		Mat gray = new Mat(image.rows(), image.cols(), image.type());
		Mat edges = new Mat(image.rows(), image.cols(), image.type());
		Mat dst = new Mat(image.rows(), image.cols(), image.type(), new Scalar(0));
		
		// Converting the image to Gray
		Imgproc.cvtColor(image, gray, Imgproc.COLOR_RGB2GRAY);
		
		Imgproc.equalizeHist(gray, contraste);
		
		// Blurring the image
		Imgproc.medianBlur(contraste, edges, 3);
		//Imgproc.blur(gray, edges, new Size(3, 3));
		Imgcodecs.imwrite("ProjetFrelon/data/Results/blur.jpg", edges);
		
		// Detecting the edges
		Imgproc.Canny(edges, edges, 100, 100 * 3);
		
		// Copying the detected edges to the destination matrix
		image.copyTo(dst, edges);
		
		
		
		// Créer l'image modifiée
		Imgcodecs.imwrite("ProjetFrelon/data/Results/detected_wings.jpg", dst);
		Imgcodecs.imwrite("ProjetFrelon/data/Results/gray.jpg", gray);

	}
}