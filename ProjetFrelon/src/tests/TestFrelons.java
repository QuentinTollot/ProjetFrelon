package tests;

import imageProcessing.TraitementImage;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import results.CasteFrelonFemelle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static results.CasteDetector.determinerSaison;

/**
 * Cette classe gère les tests pour le projet
 * Elle en effectue deux :
 * _un test pour la caste
 * _un test pour la longueur des frelons
 */
public class TestFrelons {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    @DisplayName("CasteFrelon")
    public void testCasteFrelon() {
        // Créer une instance de results.CasteFrelonFemelle
        CasteFrelonFemelle casteFrelonFemelle = new CasteFrelonFemelle();

        // Vérifie les 11 images
        for (int i = 1; i <= 11; i++) {
            // Chemin de l'image
            String cheminImage = "ProjetFrelon\\data\\Trap\\test" + i + ".jpg";

            // on définit l'échelle et la rotation pour chaque image (indépendant en fonction de l'image)
            int scale = 100;

            boolean rotation = true;
            if (i == 1 || i == 3 || i == 6 || i == 7 || i == 8 || i == 9) {
                rotation = false;
            }
            String casteResultat = casteFrelonFemelle.CasteFrelonFemelle(cheminImage, scale, rotation);

            // on doit obtenir le même résultats (sauf la photo test2 où on ne connaît pas la caste)
            String saison = determinerSaison();
            String casteAttendue = "";
            if (saison == "hiver"){
                casteAttendue = "Non déterminée"; // Caste pour l'hiver
            }
            else{
                casteAttendue = "Ouvrière"; //caste le reste de l'année
            }
            assertEquals("Vérification de la caste pour l'image " + i, casteAttendue, casteResultat);
        }

    }

    @Test
    @DisplayName("TaillePixelFrelon")
    public void testPixelFrelon() {
        // Vérifie les 11 images
        for (int i = 1; i <= 11; i++) {
            // Charger l'image
            String imagePath = "ProjetFrelon\\data\\Trap\\test" + i + ".jpg";
            Mat originalImage = Imgcodecs.imread(imagePath);

            // réduit l'image de 5%
            int cropPercentage = 5;
            int cropPixels = (int) (originalImage.width() * cropPercentage / 100.0);

            Rect cropRect = new Rect(cropPixels, cropPixels, originalImage.width() - 2 * cropPixels, originalImage.height() - 2 * cropPixels);
            Mat image = new Mat(originalImage, cropRect);

            Rect RectangleEnglobant = TraitementImage.traitementimage(image);
            double longueurRectangle = 0;
            //si l'image a été retournée
            if (i == 1 || i == 3 || i == 6 || i == 7 || i == 8 || i == 9) {
                longueurRectangle = RectangleEnglobant.height;
            } else {
                longueurRectangle = RectangleEnglobant.width;
            }


            //si la longueur est supérieure à 2000 pixels, on zoom de 20%
            if (longueurRectangle>1500){
                // réduit l'image de 10%
                cropPercentage = 20;
                cropPixels = (int) (originalImage.width() * cropPercentage / 100.0);

                cropRect = new Rect(cropPixels, cropPixels, originalImage.width() - 2 * cropPixels, originalImage.height() - 2 * cropPixels);
                image = new Mat(originalImage, cropRect);

                RectangleEnglobant = TraitementImage.traitementimage(image);
                //si l'image a été retournée
                if (i == 1 || i == 3 || i == 6 || i == 7 || i == 8 || i == 9) {
                    longueurRectangle = RectangleEnglobant.height;
                } else {
                    longueurRectangle = RectangleEnglobant.width;
                }
            }



            System.out.println("Longueur du rectangle pour l'image " + i + ": " + longueurRectangle);

            // Voici les assertion afin de valider les images
            assertTrue("Vérification de la taille minimale du rectangle pour l'image " + i, longueurRectangle > 0);
            assertTrue("Vérification de la taille maximale du rectange pour l'image " + i, longueurRectangle < 1500);
            if (i == 1){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1250);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 1200);

            }
            else if (i==2){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 800);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 750);
            }
            else if (i==3){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1300);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 1250);

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
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 700);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 600);

            }
            else if (i==7){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 700);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 600);

            }
            else if (i==8){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1500);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 1450);
            }
            else if (i==9){
                assertTrue("Vérification de la taille maximale du frelon selon le rectange pour l'image " + i, longueurRectangle < 1450);
                assertTrue("Vérification de la taille minimale du frelon selon le rectange pour l'image " + i, longueurRectangle > 1400);
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
