package controllers;
import Entities.Commentaire;
import Entities.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import services.IService;
import services.ServiceCommentaire;
import services.ServiceProjet;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OneCommentListCardController {
    @FXML
    private ImageView img;
    @FXML
    private Text Contenu;

    @FXML
    private Text likef;

    @FXML
    private Text dislikef;
    @FXML
    private Text Date;
    @FXML
    private Text Nomuser;
    @FXML
    private Text Img;
    int like ;
    int dislike;
    private static int projetIdToUpdate = 0;
    private static int projetIdToShow = 0;
    private static int updateProjectModelShow = 0;
    @FXML
    private HBox addlike;
    @FXML
    private HBox priceHbox;
    @FXML
    private HBox ItemShowBtn;
    public void setCommentData(Commentaire comment) {
        IService commentService = new ServiceCommentaire();
        Contenu.setText(comment.getContenu());
        likef.setText(String.valueOf(comment.getLikes()));
        dislikef.setText(String.valueOf(comment.getDislikes()));
        Image image = new Image(
                getClass().getResource("/assets/ProductUploads/" + comment.getImg()).toExternalForm());
        img.setImage(image);
        Nomuser.setText(String.valueOf(comment.getNomuser()));
        ItemShowBtn.setOnMouseClicked(event -> {
            System.out.println("project Name: " + comment.getContenu());
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CommentsList.fxml"));
            try {
                commentService.modifier(comment);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                Parent root = loader.load();
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    void addlike(MouseEvent event) throws SQLException {
        this.like=like + 1;
        System.out.println(like+"like");
    }
    @FXML
    void adddislike(MouseEvent event) throws SQLException {
        this.dislike=dislike + 1;
        System.out.println(dislike+"like");
    }
}
