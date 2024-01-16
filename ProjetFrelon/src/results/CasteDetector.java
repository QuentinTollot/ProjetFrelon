package results;

import java.util.Calendar;

/**
 * cette classe permet deux chsoes :
 * _ de donner la saison actuelle
 * _ de donner la longueur des frelons ouvrières et reines en fonction de la saison pour
 * déterminer la caste
 */
public class CasteDetector {
    public static String estimerCasteFrelon(double longueur_reelle) {

        // Déterminer la saison en fonction du mois actuel
        String saison = determinerSaison();

        // Appele la sous-fonction correspondante à la saison actuelles
        if (saison.equalsIgnoreCase("printemps")) {
            return estimerCastePrintemps(longueur_reelle);
        } else if (saison.equalsIgnoreCase("été")) {
            return estimerCasteEte(longueur_reelle);
        } else if (saison.equalsIgnoreCase("automne")) {
            return estimerCasteAutomne(longueur_reelle);
        } else {
            return estimerCasteHiver(longueur_reelle);
        }
    }
    //fonction qui détermine la saison actuelle
    public static String determinerSaison(){
        // Obtenir le mois actuel
        int moisActuel = Calendar.getInstance().get(Calendar.MONTH) + 1;

        if (moisActuel >= 3 && moisActuel <= 5) {
            return "printemps";
        } else if (moisActuel >= 6 && moisActuel <= 8) {
            return "été";
        } else if (moisActuel >= 9 && moisActuel <= 12) {
            return "automne";
        } else {
            return "hiver";
        }
    }
    //Fonctions qui en fonction de la période de l'année détreminent la caste
    private static String estimerCastePrintemps(double longueur_reelle) {
        if (longueur_reelle >= 25 && longueur_reelle <= 35) {
            return "Reine";
        } else if (longueur_reelle >= 14 && longueur_reelle <= 20) {
            return "Ouvrière";
        }
        return "Non déterminée";
    }

    private static String estimerCasteEte(double longueur_reelle) {
        if (longueur_reelle >= 35 && longueur_reelle <= 40) {
            return "Reine";
        } else if (longueur_reelle >= 20 && longueur_reelle <= 33) {
            return "Ouvrière";
        }
        return "Non déterminée";
    }

    private static String estimerCasteAutomne(double longueur_reelle) {
        if (longueur_reelle >= 20 && longueur_reelle <= 33) {
            return "Ouvrière";
        } else if (longueur_reelle > 33 && longueur_reelle < 43) {
            return "Reine";
        }
        return "Non déterminée";
    }

    private static String estimerCasteHiver(double longueur_reelle) {
        if (longueur_reelle >= 25 && longueur_reelle <= 40) {
            return "Reine";
        }
        return "Non déterminée";
    }
}
