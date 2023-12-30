import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import static org.junit.Assert.assertTrue;

public class TestFrelons {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    @Test
    @DisplayName("TailleFrelon")
    public void testTailleFrelon() {
        // Loop through 11 images
        for (int i = 1; i <= 11; i++) {
            // Charger l'image
            String imagePath = "C:\\Users\\Quentin\\IdeaProjects\\ProjetFrelon\\ProjetFrelon\\data\\Trap\\test" + i + ".jpg";
            Mat originalImage = Imgcodecs.imread(imagePath);

            // réduit l'image de 5%
            int cropPercentage = 5;
            int cropPixels = (int) (originalImage.width() * cropPercentage / 100.0);

            Rect cropRect = new Rect(cropPixels, cropPixels, originalImage.width() - 2 * cropPixels, originalImage.height() - 2 * cropPixels);
            Mat image = new Mat(originalImage, cropRect);

            double longueurRectangle = TraitementImage.traitementimage(image, originalImage, null);

            System.out.println("Longueur du rectangle pour l'image " + i + ": " + longueurRectangle);

            // Voici les assertion afin de valider les images
            assertTrue("Vérification de la taille minimale du rectangle pour l'image " + i, longueurRectangle > 0);
            assertTrue("Vérification de la taille maximale du rectange pour l'image " + i, longueurRectangle < 1500);
            if (i == 1){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1000);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 900);

            }
            else if (i==2){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 800);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 750);
            }
            else if (i==3){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1000);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 950);

            }
            else if (i==4){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1100);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 1000);

            }
            else if (i==5){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 850);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 800);

            }
            else if (i==6){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 500);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 450);

            }
            else if (i==7){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 500);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 450);

            }
            else if (i==8){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1050);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 1000);
            }
            else if (i==9){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 950);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 900);
            }
            else if (i==10){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1250);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 1150);
            }
            else {
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 825);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 775);
            }
        }
    }
}
