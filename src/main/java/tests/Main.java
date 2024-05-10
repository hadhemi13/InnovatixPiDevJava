package tests;

import services.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException, GeneralSecurityException, IOException, MessagingException {
//        Cheque cheque = new Cheque("hsh",22,12,"kahak",122121,"yshs",new Date(125,04,06),"dsshs","hsh",1,1);
//        System.out.println(cheque.getUser_id());
//        ServiceCheque serviceCheque = new ServiceCheque();
//       // serviceCheque.ajouterS(cheque);
//       Cheque a = serviceCheque.getById(2234606);
//        System.out.println("this : " + a);
//        ServiceVirement serviceVirement =new ServiceVirement();
//        VirementItemsAdmin virementItemsAdmin = new VirementItemsAdmin();
//        virementItemsAdmin.sendSMS(String.valueOf(2));
//        System.out.println("passe");
//            YouSignService youSignService = new YouSignService();
//        youSignService.initiateSignatureRequest("Nom de la demande", "khaluiyesser@gmail.com");
        //ShaymaService shaymaService = new ShaymaService();
       // shaymaService.sendMail("hello world","hello world" );
        //System.out.println(serviceVirement.getById(1));
    }
}
        // Créer une instance de ServiceVirement
//        ServiceVirement serviceVirement = new ServiceVirement();
//
//        try {
//            // Appeler la méthode afficher pour récupérer la liste des virements
//            List<Virement> virements = serviceVirement.afficher();
//
//            // Afficher les virements récupérés
//            for (Virement virement : virements) {
//                System.out.println(virement);
//            }
//        } catch (SQLException e) {
//            // Gérer les éventuelles exceptions
//            e.printStackTrace();
//        }
//    }
//}
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



