package yesserTest.otp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RatingSystem extends Application {

    private Text[] stars;

    @Override
    public void start(Stage primaryStage) {
        // Créer une étiquette pour afficher la notation sélectionnée
        Label ratingLabel = new Label("Rating: ");

        // Créer des étoiles pour représenter les options de notation
        stars = new Text[5];
        for (int i = 0; i < 5; i++) {
            Text star = new Text("\u2606"); // étoile vide
            star.setFont(Font.font(30));
            int index = i;
            star.setOnMouseClicked(e -> setRating(index + 1));
            stars[i] = star;
        }

        // Ajouter des étoiles à un conteneur HBox
        HBox ratingBox = new HBox(10);
        ratingBox.getChildren().addAll(stars);
        ratingBox.setAlignment(Pos.CENTER);

        // Créer un conteneur VBox pour organiser les éléments de l'interface utilisateur
        VBox root = new VBox(20, ratingLabel, ratingBox);
        root.setAlignment(Pos.CENTER);

        // Créer la scène
        Scene scene = new Scene(root, 300, 200);

        // Définir la scène et afficher la fenêtre
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rating System");
        primaryStage.show();
    }

    private void setRating(int rating) {
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                stars[i].setText("\u2605"); // étoile remplie
                stars[i].setFill(Color.YELLOW);
            } else {
                stars[i].setText("\u2606"); // étoile vide
                stars[i].setFill(Color.BLACK);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
