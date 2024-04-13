package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class ListeArticlesClientsController {

    @FXML
    private TextField ArticlesclientsfSearchInput;

    @FXML
    private ComboBox<?> categoryInput;

    @FXML
    private Pane content_area;

    @FXML
    private ComboBox<?> dateclientsfInput;

    @FXML
    private HBox getPromotionalItemsBtn;

    @FXML
    private ComboBox<?> notesfclientsInput;

    @FXML
    private GridPane productsListContainer;

    @FXML
    private GridPane productsListContainer1;

    @FXML
    private ComboBox<?> titreclientsfInput;

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
