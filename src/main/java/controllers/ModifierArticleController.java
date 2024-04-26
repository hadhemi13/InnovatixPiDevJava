package controllers;

import Entities.Article;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceArticle;

import javax.imageio.IIOParam;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.cert.PolicyNode;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ModifierArticleController implements Initializable {

    public Button ModifierArtBtn;
    @FXML
    private TextField titreArt;

    @FXML
    private TextArea ContenuArt;

    @FXML
    private ComboBox<String> addCategorie;

    @FXML
    private ImageView imageInput;

    @FXML
    private VBox vboxmod;
    @FXML
    private HBox PieceJInputErrorHbox;

    @FXML
    private HBox titreInputErrorHbox;
    @FXML
    private HBox categorieInputErrorHbox;

    @FXML
    private Text titreInputError;
    @FXML
    private ImageView pieceJArtInput;

    @FXML
    private HBox imageInputErrorHbox;

    @FXML
    private HBox ContenuInputErrorHbox;
    @FXML
    private Text imageInputError;
    private Article article;
    private articleCardAdminController articleCardAdminController;
    private ListArticleAdminController listArticleController;

    private ServiceArticle serviceArticle;
    @FXML
    private Pane content_area;
    public void setListArticleController(ListArticleAdminController listArticleController) {
        this.listArticleController = listArticleController;
    }
    public void setarticleCardAdminController(articleCardAdminController articleCardAdminController) {
        this.articleCardAdminController = articleCardAdminController;
    }
//
//
//    private void initializeArticleFields() {
//        if (article != null) {
//            titreArt.setText(article.getTitre_art());
//            ContenuArt.setText(article.getContenu_art());
//            datePubArt.setValue(article.getDate_pub_art().toLocalDate());
//            addCategorie.setValue(article.getCategorie_art());
//            SpinnerValueFactory<Integer> valueFactory = dureeArt.getValueFactory();
//            if (valueFactory != null) {
//                valueFactory.setValue(article.getDuree_art());
//            }
//            Image image = new Image(article.getImage_art());
//            imageInput.setImage(image);
//        }
//    }


//    private void loadArticleData(int articleId) {
//        // Utilisez le service pour récupérer les détails de l'article à partir de la base de données
//        article = this.article;
//    }
//
//    // Mettre à jour la méthode initializeArticleFields() pour charger les données de l'article
//    private void initializeArticleFields(int articleId) {
//        loadArticleData(articleId); // Charger les données de l'article depuis la base de données
//        // Reste du code inchangé
//    }
//
//    // Mettre à jour la méthode ModifierArt(MouseEvent mouseEvent) pour effectuer la mise à jour de l'article
//    @FXML
//    public void ModifierArt(MouseEvent mouseEvent) {
//        try {
//            // Mettre à jour les valeurs de l'article avec les nouvelles valeurs saisies par l'utilisateur
//            article.setTitre_art(titreArt.getText());
//            article.setContenu_art(ContenuArt.getText());
//            article.setDate_pub_art(LocalDateTime.of(datePubArt.getValue(), LocalTime.MIDNIGHT));
//            article.setCategorie_art(addCategorie.getValue());
//            article.setDuree_art(dureeArt.getValue());
//            article.setImage_art(imageInput.getImage().getUrl());
//
//            // Appeler la méthode de service pour effectuer la mise à jour de l'article dans la base de données
//            serviceArticle.modifier(article);
//
//            // Fermer la fenêtre après la mise à jour
//            Stage stage = (Stage) titreArt.getScene().getWindow();
//            stage.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Gérer l'exception appropriée ici
//        }
//    }
//public void initData(Article article) {
//    this.article = article;
//    initializeArticleFields();
//}
//
//    private void initializeArticleFields() {
//        if (article != null) {
//            titreArt.setText(article.getTitre_art());
//            ContenuArt.setText(article.getContenu_art());
//            datePubArt.setValue(article.getDate_pub_art().toLocalDate());
//            addCategorie.setValue(article.getCategorie_art());
//            SpinnerValueFactory<Integer> valueFactory = dureeArt.getValueFactory();
//            if (valueFactory != null) {
//                valueFactory.setValue(article.getDuree_art());
//            }
//            Image image = new Image(article.getImage_art());
//            imageInput.setImage(image);
//        }
//    }

    public void initData(Article article) {
        this.article = article;
        initializeArticleFields();
//         listLoader;
//        ListArticleAdminController listArticleController = listLoader.getController();
    }

    private void initializeArticleFields() {
        if (article != null) {
            titreArt.setText(article.getTitre_art());
            ContenuArt.setText(article.getContenu_art());
            addCategorie.setValue(article.getCategorie_art());
//            SpinnerValueFactory<Integer> valueFactory = dureeArt.getValueFactory();
//            if (valueFactory != null) {
//                valueFactory.setValue(article.getDuree_art());
//            }
         //    Assurez-vous que l'image de l'article n'est pas vide
            if (article.getImage_art() != null && !article.getImage_art().isEmpty()) {
                Image image = new Image(article.getImage_art());
                imageInput.setImage(image);
            }
        }
    }

//    @FXML
//    void ModifierArt(MouseEvent mouseEvent) {
//        try {
//            boolean champsVides = false;
//
//            // Vérification des champs vides
//            if (ContenuArt.getText().isEmpty()) {
//                ContenuInputErrorHbox.setVisible(true);
//                champsVides = true;
//            } else {
//                ContenuInputErrorHbox.setVisible(false);
//            }
//
//            if (addCategorie.getSelectionModel().isEmpty()) {
//                categorieInputErrorHbox.setVisible(true);
//                champsVides = true;
//            } else {
//                categorieInputErrorHbox.setVisible(false);
//            }
//
//            if (imageInput.getImage() == null) {
//                imageInputErrorHbox.setVisible(true);
//                champsVides = true;
//            } else {
//                imageInputErrorHbox.setVisible(false);
//            }
//
//            if (titreArt.getText().isEmpty()) {
//                titreInputErrorHbox.setVisible(true);
//                champsVides = true;
//            } else {
//                titreInputErrorHbox.setVisible(false);
//            }
//
//            // Vérification du format du titre
//            String titre = titreArt.getText();
//            if (!Character.isUpperCase(titre.charAt(0))) {
//                titreInputErrorHbox.getChildren().setAll(new Text("Le titre doit commencer par une majuscule."));
//                titreInputErrorHbox.setVisible(true);
//                champsVides = true;
//            } else {
//                titreInputErrorHbox.setVisible(false);
//            }
//
//            // Si au moins un champ est vide, afficher les messages d'erreur
//            if (champsVides) {
//                return;
//            }
//            if (article != null) {
//                article.setTitre_art(titreArt.getText());
//                article.setContenu_art(ContenuArt.getText());
//                article.setDate_pub_art(LocalDateTime.now());
//                article.setCategorie_art(addCategorie.getValue());
//                article.setDuree_art(2);
//                // Assurez-vous que l'image de l'article n'est pas vide
//                if (imageInput.getImage() != null) {
//                    article.setImage_art(imageInput.getImage().getUrl());
//                }
//                // Appeler la méthode de service pour effectuer la mise à jour de l'article dans la base de données
//                ServiceArticle serviceArticle = new ServiceArticle();
//                serviceArticle.modifier(article);
////
//////                    listArticleController.refreshArticleList();
////                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListArticleAdmin.fxml"));
////                Pane listArtAdminPane = loader.load();
////
////                // Remplacer le contenu de content_area par le contenu de listArticleAdmin
////
////                vboxmod.getChildren().setAll(listArtAdminPane);
//
//
////                // Charger le fichier FXML de ListArticleAdmin.fxml
////                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ListArticleAdmin.fxml"));
////                Pane listArtAdminPane = loader.load();
////
////                // Récupérer le contrôleur de ListArticleAdminController
////                ListArticleAdminController listArticleController = loader.getController();
////
////                // Charger la liste des articles dans la Pane content_area de ListArticleAdmin.fxml
////                listArticleController.loadArticles();
////
////                // Remplacer le contenu de content_area par le contenu de listArticleAdmin
////                content_area.getChildren().setAll(listArtAdminPane);
////
////                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listArticleAdmin.fxml"));
////                try {
////                    Parent root = loader.load();
////                    // Accéder à la pane content_area depuis le controller de
////                    // OneProductListCard.fxml
////                    Pane content_area = (Pane) ((Node) mouseEvent.getSource()).getScene().lookup("#content_area");
////
////                    // Vider la pane et afficher le contenu de ProductsList.fxml
////                    content_area.getChildren().clear();
////                    content_area.getChildren().add(root);
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//                if (listArticleController != null) {
//                    listArticleController.refreshArticleList(); // Appel de la méthode pour rafraîchir la liste des articles
//                }
//
//                // Fermez la fenêtre de modification d'article
//                Stage stage = (Stage) titreArt.getScene().getWindow();
//                stage.close();
//
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Gérer l'exception appropriée ici
//        }
//    }
@FXML
void ModifierArt(MouseEvent mouseEvent) {
    try {
        // Vos opérations de validation et de modification de l'article ici...

        if (article != null) {
            // Mettre à jour les données de l'article avec les nouvelles valeurs
            article.setTitre_art(titreArt.getText());
            article.setContenu_art(ContenuArt.getText());
            article.setDate_pub_art(LocalDateTime.now());
            article.setCategorie_art(addCategorie.getValue());
            article.setDuree_art(2);
            // Assurez-vous que l'image de l'article n'est pas vide
            if (imageInput.getImage() != null) {
                article.setImage_art(imageInput.getImage().getUrl());
            }

            // Appeler la méthode de service pour effectuer la mise à jour de l'article dans la base de données
            ServiceArticle serviceArticle = new ServiceArticle();
            serviceArticle.modifier(article);


//            if (listArticleController != null) {
//                // Rafraîchir la liste des articles dans le contrôleur de la liste des articles
//                listArticleController.refreshArticleList();
//            }

            // Fermer la fenêtre de modification d'article
            Stage stage = (Stage) titreArt.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listArticleAdmin.fxml"));
            try {
                Parent root = loader.load();

                Pane contentArea = (Pane) ((Node) mouseEvent.getSource()).getScene().lookup("#content_area");

                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }} catch (SQLException e) {
        throw new RuntimeException(e);
    }
//            // Charger le fichier FXML de listearticleadmin.fxml
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/listearticleadmin.fxml"));
//            Parent root = loader.load();
//
//            // Accéder à la pane content_area depuis le controller actuel
//            Pane contentArea = (Pane) titreArt.getScene().lookup("#content_area");
//
//            // Vider la pane et afficher le contenu de listearticleadmin.fxml
//            contentArea.getChildren().clear();
//            contentArea.getChildren().add(root);
//        }
//    } catch (SQLException | IOException e) {
//        e.printStackTrace();
//        // Gérer les exceptions appropriées ici
//    }
}

    @FXML
    void ajouter_image(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );
        File selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);
        }
    }


    @FXML
    void returnbackarticle(MouseEvent event) {
        Stage stage = (Stage) titreArt.getScene().getWindow();
        stage.close();
    }

    //@Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Initialiser la liste des catégories
//        ObservableList<String> categories = FXCollections.observableArrayList(
//                "Développement durable",
//                "Finance",
//                "Banque",
//                "Crédits"
//        );
//        addCategorie.setItems(categories);
//
//        // Initialiser le Spinner de durée
//        SpinnerValueFactory<Integer> valueFactory =
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0); // Valeur initiale, valeur maximale, valeur minimale
//        dureeArt.setValueFactory(valueFactory);
//
//        // Autoriser la saisie directe des valeurs dans le Spinner
//        dureeArt.setEditable(true);
//
//        // Limiter la saisie à des nombres entiers
//        dureeArt.getEditor().setTextFormatter(new TextFormatter<>(c -> {
//            if (c.getControlNewText().matches("\\d*")) {
//                return c;
//            } else {
//                return null;
//            }
//
//        }));


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageInputErrorHbox.setVisible(false);
        categorieInputErrorHbox.setVisible(false);
        ContenuInputErrorHbox.setVisible(false);
        PieceJInputErrorHbox.setVisible(false);
        titreInputErrorHbox.setVisible(false);
        // Initialiser la liste des catégories
        ObservableList<String> categories = FXCollections.observableArrayList(
                "Développement durable",
                "Finance",
                "Banque",
                "Crédits"
        );
        addCategorie.setItems(categories);

//        // Initialiser le Spinner de durée
//        SpinnerValueFactory<Integer> valueFactory =
//                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0); // Valeur initiale, valeur maximale, valeur minimale
//        dureeArt.setValueFactory(valueFactory);
//
//        // Autoriser la saisie directe des valeurs dans le Spinner
//        dureeArt.setEditable(true);
//
//        // Limiter la saisie à des nombres entiers
//        dureeArt.getEditor().setTextFormatter(new TextFormatter<>(c -> {
//            if (c.getControlNewText().matches("\\d*")) {
//                return c;
//            } else {
//                return null;
//            }
//        }));

        // Initialiser les champs de l'article
        initializeArticleFields();
    }


    public void initListArticleController(ListArticleAdminController listArticleController) {
        this.listArticleController = listArticleController;
    }


    // Initialiser les champs de l'article
       // initializeArticleFields();
    }
//
//    public void ModifierArt(MouseEvent mouseEvent) throws SQLException {
//        ServiceArticle serviceArticle = new ServiceArticle() ;
//        if (article != null) {
//            article.setTitre_art(titreArt.getText());
//            article.setContenu_art(ContenuArt.getText());
//            article.setDate_pub_art(LocalDateTime.of(datePubArt.getValue(), LocalDate.now().atStartOfDay().toLocalTime()));
//            article.setCategorie_art(addCategorie.getValue());
//            article.setDuree_art(dureeArt.getValue());
//            article.setImage_art(imageInput.getImage().getUrl());
//            serviceArticle.modifier(article);
//        }
//    }

