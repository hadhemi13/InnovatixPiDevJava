package controllers.Credit;

import Entities.Credit;
import Entities.RDV;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class updaterdvcontroller {

    @FXML
    private Button btnsave;

    @FXML
    private HBox chartContainer;

    @FXML
    private Pane content_area;

    @FXML
    private DatePicker datedebutlabel;

    @FXML
    private TextField employename;

    @FXML
    private TextField heurelabel;

    @FXML
    private TextField idclientlabel;

    @FXML
    private ChoiceBox<?> idcreditchoise;

    @FXML
    private TextField methodelabel;

    @FXML
    private HBox navBarLogout;

    @FXML
    private Text navFullname;

    @FXML
    private HBox sideBarLogout;

    @FXML
    void saverdv(ActionEvent event) {
        Connection con=null;
        PreparedStatement st=null;
        ResultSet resultSet =null;
        int id;
    }
    public void initData(RDV rdv) {
        int id = rdv.getId();

        idclientlabel.setText(String.valueOf(rdv.getIdclient()));
        heurelabel.setText(String.valueOf(rdv.getHeure()));
        Date dateDebut =rdv.getDaterdv();

        datedebutlabel.setValue(LocalDate.parse(dateDebut.toString()));
        methodelabel.setText(rdv.getMethode());
        employename.setText(rdv.getEmployename());


    }

}