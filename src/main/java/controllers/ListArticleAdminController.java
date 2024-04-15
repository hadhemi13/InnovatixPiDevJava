package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ListArticleAdminController {

    @FXML
    private Text AjouterArtBtn;

    @FXML
    private TextField ArticlesclientsfSearchInput;

    @FXML
    private Text VoirComment;

    @FXML
    private ComboBox<?> categoryInput;

    @FXML
    private Pane content_area;

    @FXML
    private HBox getPromotionalItemsBtn;

    @FXML
    private GridPane productsListContainer;

    @FXML
    private GridPane productsListContainer1;

    @FXML
    void SearchByImage(MouseEvent event) {

    }

    @FXML
    void getPromotionalItems(MouseEvent event) {

    }

    @FXML
    void searchProduct(KeyEvent event) {

    }

}
