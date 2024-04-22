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
    private TextField motsCles;

    @FXML
    private Button upload;

    @FXML
    private TextField titre;

    @FXML
    private TextField domaine;

    @FXML
    private TextField description;

    @FXML
    private TextField language;

    @FXML
    private TextField type;

    @FXML
    private TextField experience;

    @FXML
    private TextField niveau;

    @FXML
    private TextField pfeBook;

    @FXML
    private Button updateOffre;

    @FXML
    private TextField exigence;

    @FXML
    private TextField poste;
    public void initData(int a) throws SQLException {
        ServiceOffreDeStage serviceOffreDeStage = new ServiceOffreDeStage();
//         List <OffreDeStage> stage = serviceOffreDeStage.afficherId();
        OffreDeStage stage = serviceOffreDeStage.afficheUne(a);
//         System.out.println(stage);

        description.setText(stage.getDescription());
        titre.setText(stage.getTitle());
        exigence.setText(stage.getExigenceOffre());
        StringBuilder stringBuilder = new StringBuilder();
//        forea (String i : stage.getLanguage())

//        language.setText(stage.getLanguage().toString());
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
