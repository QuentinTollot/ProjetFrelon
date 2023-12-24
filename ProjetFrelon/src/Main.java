
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
    	
    	 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

         // Creation du panel
         JPanel imagePanel = new JPanel();
         imagePanel.setLayout(new FlowLayout());

         // Loop through 11 images
         for (int i = 1; i <= 11; i++) {
             // Charger l'image
             String imagePath = "C:\\Users\\Quentin\\git\\ProjetFrelon\\ProjetFrelon\\data\\Trap\\test" + i + ".jpg";
             Mat originalImage = Imgcodecs.imread(imagePath);

             // réduit l'image de 10%
             int cropPercentage = 10;
             int cropPixels = (int) (originalImage.width() * cropPercentage / 100.0);

             Rect cropRect = new Rect(cropPixels, cropPixels, originalImage.width() - 2 * cropPixels, originalImage.height() - 2 * cropPixels);
             Mat image = new Mat(originalImage, cropRect);
             
             TraitementImage.traitementimage(image, originalImage, imagePanel);
         }
    }   
}
