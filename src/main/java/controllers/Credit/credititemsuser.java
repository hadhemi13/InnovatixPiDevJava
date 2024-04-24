package controllers.Credit;

import Entities.Credit;
import Entities.RDV;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceCheque;
import utils.MyDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class credititemsuser {

    @FXML
    private HBox accepteritem;

    @FXML
    private Text dateitem;

    @FXML
    private Text dureeitem;

    @FXML
    private Text mensualiteuserrole;

    @FXML
    private Text montantuseritem;

    @FXML
    private Text productName;

    @FXML
    private HBox rdvcredit;

    @FXML
    private HBox refuseritem;

    @FXML
    private Text tauxitem;

    @FXML
    private Text userItemStateText;

    @FXML
    private Text useriditem;

    @FXML
    private Text useritemidclient;



    @FXML
    void getrdv(MouseEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/calandar.fxml"));
        Parent root = null;
        Connection con = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        con = MyDatabase.getInstance().getConnection();

        String idvalue = useriditem.getText();

        CalendarController cal=loader.getController();
cal.initData(Integer.parseInt(idvalue));
cal.afficherRDVById(Integer.parseInt(idvalue));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void UpdateCheque(MouseEvent event) {

    }
    public void initData(Credit i) {
        ServiceCheque serviceCheque = new ServiceCheque();
        
        useriditem.setText(String.valueOf(i.getId()));
       useritemidclient.setText(String.valueOf(i.getId_client()));
        montantuseritem.setText(String.valueOf(i.getMontant()));
        mensualiteuserrole.setText(String.valueOf(i.getMensualite()));
        dateitem.setText(String.valueOf(i.getDateDebut()));
        dureeitem.setText(String.valueOf(i.getDuree()));
       tauxitem.setText(String.valueOf(i.getTaux()));


    }

}