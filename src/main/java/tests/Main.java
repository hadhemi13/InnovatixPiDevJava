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
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
        OffreDeStage a = serviceOffreDeStage.afficheUne(1);
        System.out.println(a.getPostePropose());
    }




    }





