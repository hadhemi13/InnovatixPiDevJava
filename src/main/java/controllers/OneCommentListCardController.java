package controllers;
import Entities.Commentaire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import services.IService;
import services.ServiceCommentaire;
public class OneCommentListCardController {
    @FXML
    private ImageView img;
    @FXML
    private Text Contenu;
    @FXML
    private Text Date;
    @FXML
    private Text Nomuser;
    @FXML
    private Text Img;

    @FXML
    private HBox addlike;
    @FXML
    private HBox priceHbox;

    public void setCommentData(Commentaire comment) {
        IService commentService = new ServiceCommentaire();
        Contenu.setText(comment.getContenu());
        Image image = new Image(
                getClass().getResource("/assets/ProductUploads/" + comment.getImg()).toExternalForm());
        img.setImage(image);
        Nomuser.setText(String.valueOf(comment.getNomuser()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CommentsList.fxml"));
    }
}
