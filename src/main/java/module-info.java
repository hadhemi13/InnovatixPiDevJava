 module workshoppidev.JavaFX {
                requires javafx.controls;
                requires javafx.fxml;
                requires java.sql;
                requires java.desktop;
                requires javafx.graphics;
                requires javafx.base;
                requires java.net.http;
                opens tests to javafx.fxml;
                opens controllers to javafx.fxml;
                opens Entities to javafx.base;
                opens services to javafx.base;
                opens utils to javafx.base;
                exports tests;
                exports controllers;


                }