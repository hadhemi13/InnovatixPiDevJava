package controllers;

import Entities.Evenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.IService;
import services.ServiceEvenement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EvenementsListController implements Initializable {

    @FXML
    private GridPane evenementsListContainer;

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

    private int sortValue = -1;
    private int submitOfferTest = 0;
    private int submitCouponTest = 0;

    private static int categoryModelShow = 0;
    private String selectedOption = null;

    public static int getCategoryModelShow() {
        return categoryModelShow;
    }

    public static void setCategoryModelShow(int categoryModelShow) {
        EvenementsListController.categoryModelShow = categoryModelShow;
    }

    @FXML
    void searchEvenement(KeyEvent event) throws IOException, SQLException {
        Evenement.setSearchValue(((TextField) event.getSource()).getText());

        categId = -1;

        GridPane evenementsListContainer = (GridPane) content_area.lookup("#evenementsListContainer");
        evenementsListContainer.getChildren().clear();

        this.setEvenementGridPaneList();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qrCodeImgModel.setVisible(false);
        offreModel.setVisible(false);
        reductionInputErrorHbox.setVisible(false);
        couponForm.setVisible(false);
        couponInputErrorHbox.setVisible(false);
        backToReductionBtn.setVisible(false);
        if (EvenementsListController.getCategoryModelShow() == 0) {
            categoriesModel.setVisible(false);
        } else if (EvenementsListController.getCategoryModelShow() == 1) {
            categoriesModel.setVisible(true);
        }

        try {
            this.setEvenementGridPaneList();
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
        EvenementsListController.setCategoryModelShow(0);
    }

    private void setEvenementGridPaneList() throws SQLException {
        IService evenementService = new ServiceEvenement();

        List<Evenement> evenements = evenementService.afficher();


        evenements = ServiceEvenement.searchEvenement(Evenement.getSearchValue());

        int column = 0;
        int row = 1;
        try {
            for (Evenement evenement : evenements) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/OneEvenementListCard.fxml"));
                HBox oneEvenementCard = fxmlLoader.load();
                OneEvenementListCardController evenementCardController = fxmlLoader.getController();
                evenementCardController.setEvenementData(evenement);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                evenementsListContainer.add(oneEvenementCard, column++, row);
                GridPane.setMargin(oneEvenementCard, new Insets(0, 10, 25, 10));
                oneEvenementCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    @FXML
//    void sort__ByStock(MouseEvent event) {
//        Collecte.setSearchValue(null);
//        if (!stockBtn.getStyleClass().contains("sort__stockBtn-active")) {
//            stockBtn.getStyleClass().add("sort__stockBtn-active");
//            // Button stock = (Button) content_area.lookup("#stockBtn");
//            // stock.getStyleClass().add("sort__stockBtn-active");
//            sortValue = 1;
//        } else {
//            stockBtn.getStyleClass().remove("sort__stockBtn-active");
//            sortValue = -1;
//        }
//
//        // Parent fxml = FXMLLoader.load(getClass().getResource("EvenementsList.fxml"));
//        GridPane evenementsListContainer = (GridPane) content_area.lookup("#evenementsListContainer");
//        evenementsListContainer.getChildren().clear();
//        this.setEvenementGridPaneList();
//
//    }

    @FXML
    void close_QrCodeModel(MouseEvent event) {
        qrCodeImgModel.setVisible(false);
    }

    @FXML
    void close_offerModel(MouseEvent event) {
        offreModel.setVisible(false);
    }

    @FXML
    void reductionTypedInput(KeyEvent event) {
        String reductionText = reductionInput.getText();
        if (!reductionText.matches("-?\\d+(\\.\\d+)?")) {
            reductionInputError.setText("reduction should be a positive number");
            reductionInputErrorHbox.setVisible(true);
            submitOfferTest = 0;
        } else {
            double reduction = Double.parseDouble(reductionText);
            if (reduction < 0) {
                reductionInputError.setText("reduction cannot be negative");
                reductionInputErrorHbox.setVisible(true);
                submitOfferTest = 0;
            } else {
                reductionInputErrorHbox.setVisible(false);
                submitOfferTest = 1;
            }
        }

        double reduction = Double.parseDouble(reductionText);
        if (reduction > 100) {
            reductionInputError.setText("reduction should be less then 100");
            reductionInputErrorHbox.setVisible(true);
            submitOfferTest = 0;
        }
    }

    @FXML
    void addNewCoupon(MouseEvent event) throws IOException {
        reductionForm.setVisible(false);
        submitOfferBtn.setVisible(false);
        couponForm.setVisible(true);
        submitCouponBtn.setVisible(true);
        addNewCouponBtn.setVisible(false);
        backToReductionBtn.setVisible(true);
    }

    @FXML
    void couponTypedInput(KeyEvent event) {
        String couponText = couponInput.getText();
        if (!couponText.matches("-?\\d+(\\.\\d+)?")) {
            couponInputError.setText("coupon should be a positive number");
            couponInputErrorHbox.setVisible(true);
            submitCouponTest = 0;
        } else {
            double coupon = Double.parseDouble(couponText);
            if (coupon < 0) {
                couponInputError.setText("coupon cannot be negative");
                couponInputErrorHbox.setVisible(true);
                submitCouponTest = 0;
            } else {
                couponInputErrorHbox.setVisible(false);
                submitCouponTest = 1;
            }
        }

    }

    @FXML
    void back_toReduction(MouseEvent event) {
        reductionForm.setVisible(true);
        submitOfferBtn.setVisible(true);
        couponForm.setVisible(false);
        submitCouponBtn.setVisible(false);
        addNewCouponBtn.setVisible(true);
        backToReductionBtn.setVisible(false);

    }

//    @FXML
//    void excelBtn(MouseEvent event) throws IOException {
//        // Créer un nouveau classeur
//        Workbook workbook = new XSSFWorkbook();
//
//        // Créer une nouvelle feuille de calcul
//        Sheet sheet = workbook.createSheet("Evenements");
//
//        // Récupérer la liste des evenements
//        IEvenementService evenementService = new EvenementService();
//        List<Collecte> evenementList = evenementService.getAllEvenements();
//
//        // Créer la première ligne pour les en-têtes des colonnes
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("ID");
//        headerRow.createCell(1).setCellValue("Nom");
//        headerRow.createCell(2).setCellValue("Quantité");
//        headerRow.createCell(3).setCellValue("Prix");
//        headerRow.createCell(4).setCellValue("Points");
//        headerRow.createCell(5).setCellValue("Catégorie");
//
//        // Remplir les données des evenements
//        int rowNum = 1;
//        for (Collecte evenement : evenementList) {
//            Row row = sheet.createRow(rowNum++);
//            row.createCell(0).setCellValue(evenement.getId());
//            row.createCell(1).setCellValue(evenement.getNom_evenement());
//            row.createCell(2).setCellValue(evenement.getQuantite());
//            row.createCell(3).setCellValue(evenement.getPrix_evenement());
//            row.createCell(4).setCellValue(evenement.getPrix_point_evenement());
//            row.createCell(5).setCellValue(evenementService.getCategory(evenement.getCategorie_evenement_id()));
//        }
//
//        // Définir la largeur de chaque cellule en fonction de son contenu
//        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
//            sheet.autoSizeColumn(i);
//        }
//
//        // Ouvrir une boîte de dialogue de sélection de fichier
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Enregistrer le fichier Excel");
//        fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers Excel", "*.xlsx"));
//        File selectedFile = fileChooser.showSaveDialog(null);
//
//        if (selectedFile != null) {
//            // Enregistrer le fichier dans l'emplacement choisi par l'utilisateur
//            try (FileOutputStream outputStream = new FileOutputStream(selectedFile)) {
//                workbook.write(outputStream);
//            }
//        }
//    }
}
