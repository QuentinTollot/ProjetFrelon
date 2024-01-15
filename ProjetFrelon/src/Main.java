import org.opencv.core.Core;
import results.CasteFrelonFemelle;

/**
 * Classe main exécutant un exemple
 */

public class Main {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CasteFrelonFemelle casteFrelonFemelle = new CasteFrelonFemelle();

        //Exemple :
        String cheminImage = "ProjetFrelon\\\\data\\\\Trap\\\\test1.jpg";
        int scale = 100;
        boolean rotation = true;

        String result = casteFrelonFemelle.CasteFrelonFemelle(cheminImage, scale, rotation);

        System.out.println("Résultat : " + result);
    }
}
