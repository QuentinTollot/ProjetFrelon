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

	static void traitementimage(Mat image, Mat originalImage, JPanel imagePanel) { {
        // Convertir l'image en niveaux de gris
        Mat grayImage = new Mat();
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Appliquer un filtre pour améliorer la détection des contours
        Imgproc.GaussianBlur(grayImage, grayImage, new Size(5, 5), 0);

        // Appliquer la détection des contours
        Mat edges = new Mat();
        Imgproc.Canny(grayImage, edges, 50, 150);

        // Recherche des contours dans l'image
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);


        // Filtrer les contours pour trouver ceux qui pourraient représenter un insecte jaune/noir
        List<MatOfPoint> filteredContours = new ArrayList<>();
        int minRectangleArea = 13; // Longueur minimum du rectangle

        for (MatOfPoint contour : contours) {
            MatOfPoint2f approxCurve = new MatOfPoint2f();
            MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
            double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            int sides = (int) approxCurve.total();
            if (sides >= 3 && sides <= 6) { // Autorise les contours entre 3 et 6
                double contourArea = Imgproc.contourArea(contour);
                if (contourArea >= minRectangleArea) {
                    filteredContours.add(contour);
                }
            }
        }

        // Dessiner les contours filtrés sur l'image originale
        Mat resultImage = image.clone();
        Imgproc.drawContours(resultImage, filteredContours, -1, new Scalar(0, 255, 0), 2);

        // Dessiner les rectangles
        for (MatOfPoint contour : filteredContours) {
            Rect rect = Imgproc.boundingRect(contour);
            Imgproc.rectangle(resultImage, new org.opencv.core.Point(rect.x, rect.y), new org.opencv.core.Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0), 2);
        }
        
        // Trouver les coordonnées du rectangle englobant
        Rect bigRect = LengthProcess.boundingRectOfAllContours(filteredContours);

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
