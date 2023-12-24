import java.util.Calendar;

public class FrelonDetecteur {
	static String estimerCasteFrelon(double longueur_reelle) {
    	// Obtenir le mois actuel
        int moisActuel = Calendar.getInstance().get(Calendar.MONTH) + 1; 
        
        // Déterminer la saison en fonction du mois actuel
        String saison;
        if (moisActuel >= 3 && moisActuel <= 5) {
            saison = "printemps";
        } else if (moisActuel >= 6 && moisActuel <= 8) {
            saison = "été";
        } else if (moisActuel >= 9 && moisActuel <=12) {
        	saison = "automne";
        }       
        else {
            saison = "autre";
        }
        // Printemps
    	if (saison.equalsIgnoreCase("printemps")) {
            if (longueur_reelle >= 25 && longueur_reelle <= 35) {
                return "Frelon asiatique reine au printemps";
            } else if (longueur_reelle >= 14 && longueur_reelle <= 18) {
                return "Frelon asiatique ouvrière au printemps";
            }
        }
        // Été
        else if (saison.equalsIgnoreCase("été")) {
            if (longueur_reelle >= 35 && longueur_reelle <= 40) {
                return "Frelon asiatique reine en été";
            } else if (longueur_reelle >= 25 && longueur_reelle <= 33) {
                return "Frelon asiatique ouvrière en été";
            }
        }
    	// Automne
        else if (saison.equalsIgnoreCase("automne")) {
        	if (longueur_reelle >=25 && longueur_reelle <= 33) {
        		return "Frelon asiatique ouvrière en automne";
        	}
        	else if (longueur_reelle > 33 && longueur_reelle <43) {
        		return "Frelon asiatique reine en automne";
        	}
        }
    	// Hiver
        else {
        	if (longueur_reelle >=25 && longueur_reelle <= 40) {
        		return "Frelon asiatique reine en hiver";
        	}
        }
        return "Taille inconnue";
    }

}
