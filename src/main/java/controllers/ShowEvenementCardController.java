package controllers;

import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import services.IService;
import services.ServiceEvenement;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import utils.TrayNotificationAlert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;


public class ShowEvenementCardController implements Initializable {

    @FXML
    private GridPane evenementsListContainerfront;

    @FXML
    private Pane content_area;

    @FXML
    private HBox categoriesModel;

    @FXML
    private TextField productSearchInput;
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

    private int sortValue = -1; // 1: sort by stock *** 0: filter by category *** 2: filter by category and sort
    // by stock
    private int submitOfferTest = 0;
    private int submitCouponTest = 0;

    private static int categoryModelShow = 0;
    private String selectedOption = null;

    public static int getCategoryModelShow() {
        return categoryModelShow;
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qrCodeImgModel.setVisible(false);
        offreModel.setVisible(false);
        reductionInputErrorHbox.setVisible(false);
        couponForm.setVisible(false);
        couponInputErrorHbox.setVisible(false);
        backToReductionBtn.setVisible(false);

    }

    @FXML
    void searchEvenement() throws IOException, SQLException {
        this.setEvenementGridPaneList();
    }

    @FXML
    private void open_addEvenement(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/FXML/AddEvenement.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);

    }

    @FXML
    void open_CategoriesModel(MouseEvent event) {
        categoriesModel.setVisible(true);
    }

    @FXML
    void close_CategoriesModel(MouseEvent event) {
        categoriesModel.setVisible(false);
        EvenementsListControllerfront.setCategoryModelShow(0);
    }

    private void setEvenementGridPaneList() throws SQLException {
        IService evenementService = new ServiceEvenement();
        Evenement e1 = new Evenement();

        e1 = evenementService.getOneEvenement(Evenement.getIdEvenement());

        Evenement evenement = (Evenement) evenementService.afficher1(Evenement.getIdEvenement());
        int column = 0;
        int row = 1;
        try {
                 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/OneEvenementListCardfront.fxml"));
                HBox oneEvenementCard = fxmlLoader.load();
                OneEvenementListCardControllerfront evenementCardController = fxmlLoader.getController();
                evenementCardController.setEvenementData(evenement);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                evenementsListContainerfront.add(oneEvenementCard, column++, row);
                GridPane.setMargin(oneEvenementCard, new Insets(0, 10, 25, 10));
                oneEvenementCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void close_QrCodeModel(MouseEvent event) {
        qrCodeImgModel.setVisible(false);
    }

    @FXML
    void close_offerModel(MouseEvent event) {
        offreModel.setVisible(false);
    }

}
