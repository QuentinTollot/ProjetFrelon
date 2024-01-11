package results;

import imageProcessing.TraitementImage;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import results.CasteDetector;
import results.RealLenght;

import static imageProcessing.TraitementImage.traitementimage;

public class CasteFrelonFemelle {
    public String CasteFrelonFemelle(String CheminImage, int Scale, boolean rotation) {
        //On charge l'image
        Mat originalImage = Imgcodecs.imread(CheminImage);

        // réduire l'image de 5%
        int cropPercentage = 5;
        int cropPixels = (int) (originalImage.width() * cropPercentage / 100.0);
        Rect cropRect = new Rect(cropPixels, cropPixels, originalImage.width() - 2 * cropPixels, originalImage.height() - 2 * cropPixels);
        Mat image = new Mat(originalImage, cropRect);

        TraitementImage traitementImage = new TraitementImage();
        Rect bigRect = traitementImage.traitementimage(image, originalImage);

        // Récupère la longueur du rectangle correspondant à celle du frelon
        double longueurRectangle = 0;
        //si l'image a été retournée
        if (!rotation) {
            longueurRectangle = bigRect.height;
        } else {
            longueurRectangle = bigRect.width;
        }

        if (longueurRectangle > 2500) {
            // réduit l'image de 10%
            cropPercentage = 20;
            cropPixels = (int) (originalImage.width() * cropPercentage / 100.0);

            cropRect = new Rect(cropPixels, cropPixels, originalImage.width() - 2 * cropPixels, originalImage.height() - 2 * cropPixels);
            image = new Mat(originalImage, cropRect);

            traitementimage(image, originalImage);
        } else {
            RealLenght convertor = new RealLenght(Scale);
            double realLength = convertor.getRealLenght(longueurRectangle);
            String caste = CasteDetector.estimerCasteFrelon(realLength);
            return caste;
        }
        return ("Le programme a une erreur");
    }
}
