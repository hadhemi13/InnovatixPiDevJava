//package controllers;
//
//import java.awt.Dimension;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import javax.imageio.ImageIO;
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.embed.swing.SwingFXUtils;
//import javafx.geometry.Bounds;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.SnapshotParameters;
//import javafx.scene.control.Button;
//import javafx.scene.image.WritableImage;
//import javafx.scene.layout.Pane;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import javafx.stage.Window;
//
//
///**
// *
// * @author lenovo
// */
//public class CaptureEcran {
//
//
//    private static Object SwingFXUtils;
//
//    public static void capturer(Node node) {
//        // Récupération de la scène contenant le nœud à capturer
//        Scene scene = node.getScene();
//
//        // Création d'un SnapshotParameters pour capturer seulement le contenu de l'AnchorPane
//        SnapshotParameters parameters = new SnapshotParameters();
//        parameters.setFill(javafx.scene.paint.Color.TRANSPARENT);
//
//        // Capture d'écran du contenu de l'AnchorPane uniquement
//        WritableImage image = node.snapshot(parameters, null);
//
//        // Enregistrement de la capture
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Enregistrer la capture d'écran");
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Fichiers PNG", "*.png"),
//                new FileChooser.ExtensionFilter("Tous les fichiers", "*.*")
//        );
//        File fichier = fileChooser.showSaveDialog(new Stage());
//        if (fichier != null) {
//            try {
//                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fichier);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//}









