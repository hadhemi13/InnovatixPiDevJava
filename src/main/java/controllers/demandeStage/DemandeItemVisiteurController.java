package controllers.demandeStage;


import Entities.DemandeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DemandeItemVisiteurController {

    @FXML
    private Text date;

    @FXML
    private ImageView imageStaut;

    @FXML
    private Hyperlink cv;

    @FXML
    private Text numero;

    @FXML
    private Label Approuver;

    @FXML
    private Text domaine;

    @FXML
    private Text Lettre;

    @FXML
    private Label refuser;

    @FXML
    private Text Nom;

    @FXML
    private Text prenom;

    @FXML
    private Text email;
    DemandeStage stage ;

    @FXML
    void showCv(ActionEvent event) {
        Path destination = Paths.get("C:\\Users\\Yesser\\PI\\InnovatixYesser\\public\\uploads_directory", stage.getCv());
        try {
            File file = destination.toFile();
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("File not found: " + destination);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initData(DemandeStage demandeStage){
        this.stage = demandeStage;
        Nom.setText(demandeStage.getNom());
        prenom.setText(demandeStage.getPrenom());
        email.setText(demandeStage.getEmail());
        numero.setText(String.valueOf(demandeStage.getNumeroTelephone()));
        Lettre.setText(demandeStage.getLettremotivation());
        domaine.setText(demandeStage.getDomaine());
        cv.setText(demandeStage.getCv());
//        String img = null;
//        Path destination = Paths.get(System.getProperty("user.dir"), "src", "main","resources","img" ,img);

        if (demandeStage.getEtat() == "encours"){

        }else if (demandeStage.getEtat() == "accepter"){

        }else {

        }


    }


}