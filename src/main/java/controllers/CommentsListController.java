package controllers;

import Entities.Commentaire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.IService;
import services.ServiceCommentaire;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CommentsListController implements Initializable {

    @FXML
    private GridPane CommentsListContainer;

    @FXML
    private Pane content_area;

    @FXML
    private HBox categoriesModel;

    @FXML
    private GridPane categoriesListContainer;

    @FXML
    private TextField evenementsearchInput;

    @FXML
    private Button stockBtn;

    @FXML
    private ComboBox<String> categoryInput;

    @FXML
    private ImageView qrCodeImg;

    @FXML
    private HBox qrCodeImgModel;

    @FXML
    private HBox offreModel;

    @FXML
    private TextField reductionInput;

    @FXML
    private TextField couponInput;

    @FXML
    private Text reductionInputError;

    @FXML
    private Text couponInputError;

    @FXML
    private Text backToReductionBtn;

    @FXML
    private Text addNewCouponBtn;

    @FXML
    private HBox reductionInputErrorHbox;

    @FXML
    private HBox couponInputErrorHbox;

    @FXML
    private VBox couponForm;

    @FXML
    private VBox reductionForm;

    @FXML
    private HBox submitCouponBtn;

    @FXML
    private HBox submitOfferBtn;

    @FXML
    private ComboBox<String> couponCombobox;

    private int categId = -1;

    private int sortValue = -1;
    private int submitOfferTest = 0;
    private int submitCouponTest = 0;

    private static int categoryModelShow = 0;
    private String selectedOption = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qrCodeImgModel.setVisible(false);
        offreModel.setVisible(false);
        reductionInputErrorHbox.setVisible(false);
        couponForm.setVisible(false);
        couponInputErrorHbox.setVisible(false);
        backToReductionBtn.setVisible(false);


        try {
            this.setCommentGridPaneList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        FXMLLoader fxmlLoader1 = new FXMLLoader();
        fxmlLoader1.setLocation(getClass().getResource("/gui/evenementInterfaces/AddCategoryCard.fxml"));
        int CategColumn = 0;
        int CategRow = 2;
        couponCombobox.setOnAction(event -> {
            selectedOption = couponCombobox.getValue();
            System.out.println("Selected option: " + selectedOption);

        });
    }
    private void setCommentGridPaneList() throws SQLException {
        IService commentaireService = new ServiceCommentaire();
        List<Commentaire> commentaires = commentaireService.afficher();
        int column = 0;
        int row = 1;
        try {
            for (Commentaire commentaire : commentaires) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/OneCommentListCard.fxml"));
                HBox oneCommentCard = fxmlLoader.load();
                OneCommentListCardController commentCardController = fxmlLoader.getController();
                commentCardController.setCommentData(commentaire);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                CommentsListContainer.add(oneCommentCard, column++, row);
                GridPane.setMargin(oneCommentCard, new Insets(0, 10, 25, 10));
                oneCommentCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void open_addComment(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/AddComment.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

}
