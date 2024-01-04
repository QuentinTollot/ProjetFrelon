
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import imageProcessing.TraitementImage;
import results.CasteDetector;
import results.RealLenght;
public class Main {

    public static void main(String[] args) {
    	
    	 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	 // We can change the parameter to change the scale
    	 RealLenght convertor = new RealLenght(20);
    	 
         // Loop through 11 images
         for (int i = 1; i <= 11; i++) {
             // Charger l'image
             String imagePath = "ProjetFrelon\\data\\Trap\\test" + i + ".jpg";
             Mat originalImage = Imgcodecs.imread(imagePath);

             // réduire l'image de 5%
             int cropPercentage = 5;
             int cropPixels = (int) (originalImage.width() * cropPercentage / 100.0);

             Rect cropRect = new Rect(cropPixels, cropPixels, originalImage.width() - 2 * cropPixels, originalImage.height() - 2 * cropPixels);
             Mat image = new Mat(originalImage, cropRect);
             
             // Get the results
             double pixelLenght = TraitementImage.traitementimage(image, originalImage);
             double realLenght = convertor.getRealLenght(pixelLenght);
             String caste = CasteDetector.estimerCasteFrelon(realLenght);
             
             // Print the results
             System.out.println("Frelon " + i + " : ");
             System.out.println("taille du frelon en pixels : " + pixelLenght);
             System.out.println("taille du frelon en mm : " + realLenght);
             System.out.println("caste : " + caste + "\n");
         }
    }   
}
