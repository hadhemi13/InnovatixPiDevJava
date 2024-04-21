package tests;

import Entities.Contrat;
import Entities.DemandeStage;
import Entities.OffreDeStage;
import Entities.Stage;
import services.*;
import utils.MyDatabase;


import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
//
//        List<String> yesser = List.of("yesser");
////
////        for (String i : yesser){
////            System.out.println(i);
////        }
////        System.out.println(yesser);*

//        ServiceDemandeStage demandeStage = new ServiceDemandeStage();
////        AnalyseurCv analyseurCv = new AnalyseurCv();
////        int score = analyseurCv.analyseCV();
//        DemandeStage stage = new DemandeStage("yesser", "khaloui", "khaluiyesser@gmail.com", "hahahaha", "Resume-65e5ed6c6fc54.pdf", "Informatique", 25114365, "encours", new Date(124, 04, 06));
//        try {
//            demandeStage.ajouterParOffre(stage,2);
//            System.out.println("ajoutééééé");
//        } catch (SQLException e) {
//            System.out.println("sql");
//        } catch (IOException e) {
//            System.out.println("io");
//        } catch (InterruptedException e) {
//            System.out.println("python");
//        }
//        try {
//            demandeStage.ajouter(stage);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
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
//        ServiceContrat serviceContrat = new ServiceContrat();
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
////        }
//        LocalDate dateActuelle = LocalDate.now();
//        System.out.println(dateActuelle);
//        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
//        List<OffreDeStage> demandeStages = serviceOffreDeStage.afficher();
//        System.out.println(demandeStages);
        ServiceDemandeStage serviceDemandeStage = new ServiceDemandeStage();
        DemandeStage stage = new DemandeStage("yesser", "khaloui", "khaluiyesser@gmail.com", "hahahaha", "56f16ce8-cb5c-49a1-9784-02b47a63d389.pdf", "Informatique", 25114365, "encours", new Date(124, 04, 06));

//        AnalyseurCv analyseurCv = new AnalyseurCv();
        serviceDemandeStage.ajouterParOffre(stage,2);
        //int score =  analyseurCv.analyseCV();
        //OffreDeStage A = serviceOffreDeStage.afficheUne(1);
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
       // System.out.println( serviceOffreDeStage.afficherId());
//        List<OffreDeStage> list = serviceOffreDeStage.afficherId();

//        list.stream().forEach(System.out::println);
       //ystem.out.println(A);
      //  DemandeStage demandeStage = new DemandeStage("yesser","khaloui","khaluiyesser@gmail.com","je suis passionnée","C:/Users/Yesser/Desktop/Resume.pdf","Informatique","encours",25114365,analyseurCv.analyseCV("C:/Users/Yesser/Desktop/Resume.pdf",A.getMotsCles()),A.getId(),new Date(124,04,06));

//        OffreDeStage A = serviceOffreDeStage.afficheUne(1);
    }




    }





