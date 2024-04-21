package tests;

import Entities.Cheque;
import Entities.Virement;
import services.*;
import utils.MyDatabase;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Créer une instance de ServiceVirement
        ServiceVirement serviceVirement = new ServiceVirement();

        try {
            // Appeler la méthode afficher pour récupérer la liste des virements
            List<Virement> virements = serviceVirement.afficher();

            // Afficher les virements récupérés
            for (Virement virement : virements) {
                System.out.println(virement);
            }
        } catch (SQLException e) {
            // Gérer les éventuelles exceptions
            e.printStackTrace();
        }
    }
}
//    public static void main(String[] args) {
//        // Créer une instance de ServiceVirement
//        ServiceVirement serviceVirement = new ServiceVirement();
//
//        try {
//            // Appeler la méthode supprimer avec l'ID du virement à supprimer
//            int idVirementASupprimer = 14; // Remplacez 1 par l'ID du virement que vous souhaitez supprimer
//            serviceVirement.supprimer(idVirementASupprimer);
//        } catch (SQLException e) {
//            // Gérer les éventuelles exceptions
//            e.printStackTrace();
//        }
//    }


//    public static void main(String[] args) {
//        // Créer une instance de ServiceVirement
//        ServiceVirement serviceVirement = new ServiceVirement();
//
//        try {
//            // Récupérer un virement existant à modifier (vous pouvez ajuster l'ID selon votre base de données)
//            Virement virementAModifier = serviceVirement.afficher().get(0);
//
//            // Modifier les attributs du virement
//            virementAModifier.setNomet_prenom("Shayma ouerhani");
//            virementAModifier.setType_virement("Personne");
//            virementAModifier.setTransferez_a("leila ajroudi");
//            virementAModifier.setNum_beneficiare(123456);
//            virementAModifier.setMontant("9000");
//            virementAModifier.setCin(123331);
//            virementAModifier.setRib(22333233);
//            virementAModifier.setEmail("shayma@gmail.com");
//            virementAModifier.setDecision_v("Encours");
//            virementAModifier.setPhoto_cin_v("Nouvelle Photo");
//            virementAModifier.setPhone_number("00000000");
//
//            // Appeler la méthode modifier de ServiceVirement
//            serviceVirement.modifier(virementAModifier);
//        } catch (SQLException e) {
//            // Gérer les éventuelles exceptions
//            e.printStackTrace();
//        }
//    }
//}
//    public static void main(String[] args) {
//        // Créez un nouvel objet Virement avec les valeurs que vous souhaitez tester
//        Virement virement = new Virement("Personne", "123331", "22333233", "shayma ouerhani", 123456, "shayma ouerhani", "photo_cin_v", "Encours");
//
//        // Créez une instance de votre service de virement
//        ServiceVirement serviceVirement = new ServiceVirement();
//
//        try {
//            // Appelez la méthode d'ajout appropriée dans votre service de virement
//            serviceVirement.ajouterV(virement);
//        } catch (SQLException e) {
//            // Gérez toute exception SQL qui pourrait se produire lors de l'ajout
//            e.printStackTrace();
//        }
//    }



