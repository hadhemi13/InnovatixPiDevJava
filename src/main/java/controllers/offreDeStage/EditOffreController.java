package controllers.offreDeStage;

import Entities.DemandeStage;
import Entities.OffreDeStage;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.ServiceOffreDeStage;

public class EditOffreController implements Initializable {
    @FXML
    private DatePicker date;

    @FXML
    private TextField LettreDemInput;

    @FXML
    private TextField EmailDemInput;

    @FXML
    private TextField cv;

    @FXML
    private Button updateOffre;

    @FXML
    private Button upload;

    @FXML
    private TextField PrenomDemInput;

    @FXML
    private TextField NumeroDemInput;

    @FXML
    private TextField NomDemInput;
    public void initData(int a) throws SQLException {
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
//         List <OffreDeStage> stage = serviceOffreDeStage.afficherId();
        OffreDeStage stage = serviceOffreDeStage.afficheUne(a);
         System.out.println(stage);
//         NomDemInput.setText();


    }
    public static void setProjectUpdateData(OffreDeStage project) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void upload(ActionEvent actionEvent) {
    }

    public void updateOffre(ActionEvent actionEvent) {

    }
}
