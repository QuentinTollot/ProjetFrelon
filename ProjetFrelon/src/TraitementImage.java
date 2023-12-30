import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class TraitementImage {

    static void traitementimage(Mat image, Mat originalImage, JPanel imagePanel) {
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

        // Fusionner les masques (si nécessaire)
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

        // Dessiner les contours filtrés sur l'image originale
        Mat resultImage = image.clone();
        Imgproc.drawContours(resultImage, contours, -1, new Scalar(0, 255, 0), 2);

        // Dessiner les rectangles
        for (MatOfPoint contour : contours) {
            Rect rect = Imgproc.boundingRect(contour);
            Imgproc.rectangle(resultImage, new org.opencv.core.Point(rect.x, rect.y),
                    new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(255, 0, 0), 2);
        }

        // Trouver les coordonnées du rectangle englobant
        Rect bigRect = LengthProcess.boundingRectOfAllContours(contours);

        // Vérifier si la longueur du rectangle dépasse 1500 pixels
        double longueurRectangle = bigRect.width;

        if (longueurRectangle > 1500) {
            // réduit l'image de 10%
            int cropPercentage = 20;
            int cropPixels = (int) (originalImage.width() * cropPercentage / 100.0);

            Rect cropRect = new Rect(cropPixels, cropPixels, originalImage.width() - 2 * cropPixels, originalImage.height() - 2 * cropPixels);
            image = new Mat(originalImage, cropRect);

            traitementimage(image, originalImage, imagePanel);
        } else {
            // Dessiner le grand rectangle sans zoom
            Imgproc.rectangle(resultImage, new org.opencv.core.Point(bigRect.x, bigRect.y),
                    new org.opencv.core.Point(bigRect.x + bigRect.width, bigRect.y + bigRect.height),
                    new Scalar(0, 0, 255), 2);

            // Afficher la longueur du frelon en pixels
            System.out.println("Longueur du frelon en pixels : " + longueurRectangle);

            //traduire les pixel en mm pour l'échelle
            double longueur_reelle = longueurRectangle/1;

            //estimation de la caste
            String estimationTaille = FrelonDetecteur.estimerCasteFrelon(longueurRectangle);
            System.out.println("Estimation de la taille du frelon : " + estimationTaille);

            // Redimensionner l'image
            int newWidth = 800;  // Choisir la largeur souhaitée
            int newHeight = (int) ((double) newWidth / resultImage.width() * resultImage.height());
            Imgproc.resize(resultImage, resultImage, new Size(newWidth, newHeight));

            // Afficher l'image résultante
            ImagePrinter.displayImage(matToBufferedImage(resultImage));
        }
    }

    // Méthode pour convertir un objet Mat en BufferedImage
    public static BufferedImage matToBufferedImage(Mat mat) {
        int dataSize = mat.cols() * mat.rows() * (int)mat.elemSize();
        byte[] data = new byte[dataSize];
        mat.get(0, 0, data);

        int type;
        if (mat.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);

        return image;
    }
    
}
