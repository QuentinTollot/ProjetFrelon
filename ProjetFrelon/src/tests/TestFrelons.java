package tests;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.opencv.core.Core;
import results.CasteFrelonFemelle;

import static org.junit.Assert.assertEquals;

public class TestFrelons {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    @DisplayName("CasteFrelon")
    public void testCasteFrelon() {
        // Créer une instance de results.CasteFrelonFemelle
        CasteFrelonFemelle casteFrelonFemelle = new CasteFrelonFemelle();

        // Loop through a set of images
        for (int i = 1; i <= 11; i++) {
            // Chemin de l'image
            String cheminImage = "ProjetFrelon\\data\\Trap\\test" + i + ".jpg";

            // Définir l'échelle et la rotation pour chaque image
            int scale = 100; // définir l'échelle appropriée
            boolean rotation = true; // définir true ou false pour la rotation

            // Appeler la méthode results.CasteFrelonFemelle
            String casteResultat = casteFrelonFemelle.CasteFrelonFemelle(cheminImage, scale, rotation);

            // Vérifier que la caste est correctement identifiée
            String casteAttendue = "Ouvrière"; // définir la caste attendue pour chaque image
            assertEquals("Vérification de la caste pour l'image " + i, casteAttendue, casteResultat);
        }
    }
}
