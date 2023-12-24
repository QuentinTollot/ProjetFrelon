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
		// String imagePath = "ProjetFrelon/data/Trap/hornet01.jpg";
		String imagePath = "ProjetFrelon/data/Trap/hornet01.jpg";

		Mat image = Imgcodecs.imread(imagePath);

		/***************************************
		 * Partie Pré-traitement *
		 ***************************************/

		// Creating an empty matrices to store edges, source, destination
		Mat contraste = new Mat(image.rows(), image.cols(), image.type());
		Mat gray = new Mat(image.rows(), image.cols(), image.type());
		Mat blur = new Mat(image.rows(), image.cols(), image.type());

		// Converting the image to Gray
		Imgproc.cvtColor(image, gray, Imgproc.COLOR_RGB2GRAY);
		Imgcodecs.imwrite("ProjetFrelon/data/Results/gray.jpg", gray);

		// Améliorer le contraste
		Imgproc.equalizeHist(gray, contraste);
		Imgcodecs.imwrite("ProjetFrelon/data/Results/equalize.jpg", contraste);

		// Blurring the image
		Imgproc.medianBlur(contraste, blur, 13);
		// Imgproc.blur(gray, edges, new Size(3, 3));
		Imgcodecs.imwrite("ProjetFrelon/data/Results/blur.jpg", blur);
		

		/***************************************
		 * Partie Edge Detection *
		 ***************************************/

		Mat dst = new Mat(image.rows(), image.cols(), image.type(), new Scalar(0));

		// Detecting the edges
		Imgproc.Canny(blur, dst, 100, 300);

		// Créer l'image modifiée
		Imgcodecs.imwrite("ProjetFrelon/data/Results/detected_wings.jpg", dst);

	}
}