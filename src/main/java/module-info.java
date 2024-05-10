module workshoppidev.JavaFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;
    requires javafx.base;
    requires java.net.http;
    requires javafx.web;
    requires itextpdf;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires javafx.swing;
    requires com.fasterxml.jackson.databind;
    requires qrgen;
//     requires voicerss.tts;
    opens tests to javafx.fxml;
    opens controllers to javafx.fxml;
//    opens Entities to javafx.base;
    opens services to javafx.base;
    opens utils to javafx.base;
    exports tests;
    exports controllers;

//     opens controllers.actualites to javafx.fxml;
    exports controllers.article;
    opens controllers.article to javafx.fxml;
    exports controllers.reclamation;
    opens controllers.reclamation to javafx.fxml;
    exports controllers.commentaireArticle;
    opens controllers.commentaireArticle to javafx.fxml;
    exports controllers.reponse;
    opens controllers.reponse to javafx.fxml;
    opens Entities.actualites to javafx.base;


}