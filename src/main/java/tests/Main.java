package tests;

import Entities.Contrat;
import Entities.DemandeStage;
import Entities.Stage;
import services.*;
import utils.MyDatabase;


import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
//
//        List<String> yesser = List.of("yesser");
////
////        for (String i : yesser){
////            System.out.println(i);
////        }
////        System.out.println(yesser);*
       ServiceDemandeStage demandeStage = new ServiceDemandeStage();
       DemandeStage  stage = new DemandeStage("yesser","khaloui","khaluiyesser@gmail.com","hahahaha","yesser.com","Informatique",25114365,"encours",new Date(124,04,06));
        try {
            demandeStage.ajouter(stage);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //        try {
//            demandeStage.afficher();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        ServiceOffreDeStage  offreDeStage = new ServiceOffreDeStage();
//        try {
//            offreDeStage.afficher();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//        Stage stage = new Stage(2,"hello world!");
//        Stage yesser = new Stage(2, "hahaha", new Date(124, 01, 5));
//        ServiceStage serviceStage = new ServiceStage();
        ServiceContrat serviceContrat = new ServiceContrat();
//        Contrat contrat = new Contrat(new Date(124,4,6),6,new Date(124,8,6),"5 mois et quelque");
//        try {
//            serviceContrat.supprimer(7);
////            System.out.println("Modifier");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            serviceContrat.afficher();
//        }catch (SQLException e)
//        {
//            System.out.println(e.getMessage());
//        }

    }


}


