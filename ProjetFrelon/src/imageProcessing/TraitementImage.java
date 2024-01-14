package imageProcessing;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * Cette classe gère le traitement de l'image du frelon.
 * Elle applique sur l'image :
 * _des masques de couleur
 * _de la détection de contours
 */

public class TraitementImage {

    public static Rect traitementimage(Mat image) {

        // Convertir l'image en espace colorimétrique HSV
        Mat hsvImage = new Mat();
        Imgproc.cvtColor(image, hsvImage, Imgproc.COLOR_BGR2HSV);

        // Définir les plages de couleur pour le jaune et le noir
        Scalar yellowLowerBound = new Scalar(20, 100, 100);
        Scalar yellowUpperBound = new Scalar(30, 255, 255);
        Scalar blackLowerBound = new Scalar(0, 0, 0);
        Scalar blackUpperBound = new Scalar(180, 255, 30);

        // Créer des masques pour le jaune et le noir
        Mat yellowMask = new Mat();
        Mat blackMask = new Mat();
        Core.inRange(hsvImage, yellowLowerBound, yellowUpperBound, yellowMask);
        Core.inRange(hsvImage, blackLowerBound, blackUpperBound, blackMask);

        // Fusionner les masques
        Mat colorMask = new Mat();
        Core.bitwise_or(yellowMask, blackMask, colorMask);

        // Appliquer le masque fusionné à l'image originale pour obtenir les régions d'intérêt
        Mat roiImage = new Mat();
        image.copyTo(roiImage, colorMask);

        // Appliquer la détection des contours sur l'image avec les régions d'intérêt
        Mat edges = new Mat();
        Imgproc.Canny(roiImage, edges, 50, 150);

        // Recherche des contours dans l'image
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        // Trouver les coordonnées du rectangle englobant
        Rect bigRect = LengthProcess.boundingRectOfAllContours(contours);
        return bigRect;
    }
}
