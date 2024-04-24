package controllers.Compte;

import Entities.Compte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.ServiceCompte;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AjouterCompte implements Initializable {

    @FXML
    private Text ConfEmailInputError;

    @FXML
    private HBox ConfEmailInputErrorHbox;

    @FXML
    private Button ConfirmBtnCompte;

    @FXML
    private Text DateInputError;

    @FXML
    private HBox DateInputErrorHbox;

    @FXML
    private DatePicker DateNaiss;

    @FXML
    private Text DateNaissnputError;

    @FXML
    private HBox DateNaissnputErrorHbox;

    @FXML
    private DatePicker DatedelCin;

    @FXML
    private TextField Email;

    @FXML
    private Text EmailInputError;

    @FXML
    private HBox EmailInputErrorHbox;

    @FXML
    private TextField MontantDepot;

    @FXML
    private Text MontantInputError;

    @FXML
    private HBox MontantInputErrorHbox;

    @FXML
    private Text NatioInputError;

    @FXML
    private HBox NatioInputErrorHbox;

    @FXML
    private TextField Nationalite;

    @FXML
    private TextField Nom;

    @FXML
    private Text NomInputError;

    @FXML
    private HBox NomInputErrorHbox;

    @FXML
    private TextField NumCin;

    @FXML
    private Text NumCinInputError;

    @FXML
    private HBox NumCinInputErrorHbox;

    @FXML
    private TextField NumTel;

    @FXML
    private Text NumTelInputError;

    @FXML
    private HBox NumTelInputErrorHbox;

    @FXML
    private TextField Prenom;

    @FXML
    private Text PrenomnputError;

    @FXML
    private HBox PrenomnputErrorHbox;

    @FXML
    private TextField Profession;

    @FXML
    private Text ProffesionInputError;

    @FXML
    private HBox ProffesionInputErrorHbox;

    @FXML
    private TextField Sexe;

    @FXML
    private Text SexenputError;

    @FXML
    private HBox SexenputErrorHbox;

    @FXML
    private Text StatutnputError;

    @FXML
    private HBox StatutnputErrorHbox;

    @FXML
    private ComboBox<String> TypeCompte;

    @FXML
    private Text TypeInputError;

    @FXML
    private HBox TypeInputErrorHbox;

    @FXML
    private Text TypeCiInputError;
    @FXML
    private ComboBox<String> StatutMarital;

    @FXML
    private HBox TypeCinInputErrorHbox;

    @FXML
    private TextField conformation_email;

    @FXML
    private ComboBox<String> typeCin;

    @FXML
    void ajouterCompte(MouseEvent event) throws SQLException, IOException {
        ServiceCompte serviceCompte = new ServiceCompte();
        boolean champsVides = false;

        // Vérification de chaque champ
        if (Email.getText().isEmpty()) {
            EmailInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            EmailInputErrorHbox.setVisible(false);
        }

        if (conformation_email.getText().isEmpty()) {
            ConfEmailInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            ConfEmailInputErrorHbox.setVisible(false);
        }

        if (typeCin.getSelectionModel().isEmpty()) {
            TypeCinInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            TypeCinInputErrorHbox.setVisible(false);
        }

        if (NumCin.getText().isEmpty()) {
            NumCinInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            NumCinInputErrorHbox.setVisible(false);
        }

        if (NumTel.getText().isEmpty()) {
            NumTelInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            NumTelInputErrorHbox.setVisible(false);
        }

        if (Nom.getText().isEmpty()) {
            NomInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            NomInputErrorHbox.setVisible(false);
        }

        if (Prenom.getText().isEmpty()) {
            PrenomnputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            PrenomnputErrorHbox.setVisible(false);
        }

        if (TypeCompte.getSelectionModel().isEmpty()) {
            TypeInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            TypeInputErrorHbox.setVisible(false);
        }

        if (MontantDepot.getText().isEmpty()) {
            MontantInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            MontantInputErrorHbox.setVisible(false);
        }

        if (Nationalite.getText().isEmpty()) {
            NatioInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            NatioInputErrorHbox.setVisible(false);
        }

        if (Profession.getText().isEmpty()) {
            ProffesionInputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            ProffesionInputErrorHbox.setVisible(false);
        }

        if (StatutMarital.getSelectionModel().isEmpty()) {
            StatutnputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            StatutnputErrorHbox.setVisible(false);
        }

        if (Sexe.getText().isEmpty()) {
            SexenputErrorHbox.setVisible(true);
            champsVides = true;
        } else {
            SexenputErrorHbox.setVisible(false);
        }

        LocalDate dateDelivranceCIN = DatedelCin.getValue();
        LocalDate dateNaissance = DateNaiss.getValue();

        // Vérification si les dates sont après 2024
        if (dateDelivranceCIN != null && dateDelivranceCIN.isAfter(LocalDate.of(2024, 12, 31))) {
            // Afficher un message d'erreur ou prendre une autre action
            DateInputErrorHbox.setVisible(true);
            return; // Sortir de la méthode
        } else {
            DateInputErrorHbox.setVisible(false);
        }

        if (dateNaissance != null && dateNaissance.isAfter(LocalDate.of(2024, 12, 31))) {
            // Afficher un message d'erreur ou prendre une autre action
            DateNaissnputErrorHbox.setVisible(true);
            return; // Sortir de la méthode
        } else {
            DateNaissnputErrorHbox.setVisible(false);
        }
        // Si au moins un champ est vide, afficher les messages d'erreur
        if (champsVides) {
            return;
        }
        LocalDate selectedDate = LocalDate.now();

        // Récupération des données des champs
        String email = Email.getText();
        String confirmationEmail = conformation_email.getText();
        String typeCIN = typeCin.getSelectionModel().getSelectedItem();
        int numCIN = Integer.parseInt(NumCin.getText());
        String numTel = NumTel.getText();
        String nom = Nom.getText();
        String prenom = Prenom.getText();
        String typeCompte = TypeCompte.getSelectionModel().getSelectedItem();
        double montant = Double.parseDouble(MontantDepot.getText());
        String nationalite = Nationalite.getText();
        String profession = Profession.getText();
        String statutMarital = StatutMarital.getSelectionModel().getSelectedItem();
        String sexe = Sexe.getText();
        Date sqlDate = java.sql.Date.valueOf(selectedDate);
        int statut=1;


        // Création du compte
        Compte compte = new Compte(email, confirmationEmail, numCIN, sqlDate, nom, prenom, sexe, sqlDate, profession, typeCompte, montant, statutMarital, nationalite, numTel, typeCIN,statut);

        // Ajout du compte
        serviceCompte.ajouter(compte);

        // Réinitialiser les champs après l'ajout
        resetFields();

    }

    // Méthode pour réinitialiser les champs après l'ajout
    private void resetFields() {
        Email.clear();
        conformation_email.clear();
        typeCin.getSelectionModel().clearSelection();
        NumCin.clear();
        NumTel.clear();
        Nom.clear();
        Prenom.clear();
        TypeCompte.getSelectionModel().clearSelection();
        MontantDepot.clear();
        Nationalite.clear();
        Profession.clear();
        DateNaiss.setValue(null);
        DatedelCin.setValue(null);
        StatutMarital.getSelectionModel().clearSelection();
        Sexe.clear();


        // Masquer tous les messages d'erreur
        EmailInputErrorHbox.setVisible(false);
        ConfEmailInputErrorHbox.setVisible(false);
        TypeCinInputErrorHbox.setVisible(false);
        NumCinInputErrorHbox.setVisible(false);
        NumTelInputErrorHbox.setVisible(false);
        NomInputErrorHbox.setVisible(false);
        PrenomnputErrorHbox.setVisible(false);
        TypeInputErrorHbox.setVisible(false);
        MontantInputErrorHbox.setVisible(false);
        NatioInputErrorHbox.setVisible(false);
        ProffesionInputErrorHbox.setVisible(false);
        StatutnputErrorHbox.setVisible(false);
        SexenputErrorHbox.setVisible(false);
        DateNaissnputErrorHbox.setVisible(false);
        DateInputErrorHbox.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EmailInputErrorHbox.setVisible(false);
        ConfEmailInputErrorHbox.setVisible(false);
        TypeCinInputErrorHbox.setVisible(false);
        NumCinInputErrorHbox.setVisible(false);
        NumTelInputErrorHbox.setVisible(false);
        NomInputErrorHbox.setVisible(false);
        PrenomnputErrorHbox.setVisible(false);
        DateInputErrorHbox.setVisible(false);
        DateNaissnputErrorHbox.setVisible(false);
        TypeInputErrorHbox.setVisible(false);
        NatioInputErrorHbox.setVisible(false);
        ProffesionInputErrorHbox.setVisible(false);
        StatutnputErrorHbox.setVisible(false);
        SexenputErrorHbox.setVisible(false);
        MontantInputErrorHbox.setVisible(false);







        ObservableList<String> TypeComptes = FXCollections.observableArrayList(
                "Epargne",
                "Courant"
        );
        ObservableList<String> TypeCin = FXCollections.observableArrayList(
                "Cin",
                "Passeport"
        );
        ObservableList<String> StatutMaritals = FXCollections.observableArrayList(
                "Célibataire",
                "Marié",
                "Divorcé"
        );


        TypeCompte.setItems(TypeComptes);

        typeCin.setItems(TypeCin);

        StatutMarital.setItems(StatutMaritals);




    }

}

//        ServiceCompte serviceCompte = new ServiceCompte();
//        if (Email.getText().isEmpty()) {
//            EmailInputErrorHbox.setVisible(true);
//            if (conformation_email.getText().isEmpty()) {
//                ConfEmailInputErrorHbox.setVisible(true);
//                if (typeCin.getSelectionModel().isEmpty()) {
//                    TypeCinInputErrorHbox.setVisible(true);
//                    if (NumCin.getText().isEmpty()) {
//                        NumCinInputErrorHbox.setVisible(true);
//                        if (NumTel.getText().isEmpty()) {
//                            NumTelInputErrorHbox.setVisible(true);
//                            if (Nom.getText().isEmpty()) {
//                                NomInputErrorHbox.setVisible(true);
//                                if (Prenom.getText().isEmpty()) {
//                                    PrenomnputErrorHbox.setVisible(true);
//                                    if (TypeCompte.getSelectionModel().isEmpty()) {
//                                        TypeInputErrorHbox.setVisible(true);
//                                        if (MontantDepot.getText().isEmpty()) {
//                                            MontantInputErrorHbox.setVisible(true);
//                                            if (Nationalite.getText().isEmpty()) {
//                                                NatioInputErrorHbox.setVisible(true);
//                                                if (Profession.getText().isEmpty()) {
//                                                    ProffesionInputErrorHbox.setVisible(true);
//                                                    if (StatutMarital.getText().isEmpty()) {
//                                                        StatutnputErrorHbox.setVisible(true);
//                                                        if (Sexe.getText().isEmpty()) {
//                                                            SexenputErrorHbox.setVisible(true);
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    if (typeCin.getSelectionModel().isEmpty()) {
//                        TypeCinInputErrorHbox.setVisible(true);
//                        if (NumCin.getText().isEmpty()) {
//                            NumCinInputErrorHbox.setVisible(true);
//                            if (NumTel.getText().isEmpty()) {
//                                NumTelInputErrorHbox.setVisible(true);
//                                if (Nom.getText().isEmpty()) {
//                                    NomInputErrorHbox.setVisible(true);
//                                    if (Prenom.getText().isEmpty()) {
//                                        PrenomnputErrorHbox.setVisible(true);
//                                        if (TypeCompte.getSelectionModel().isEmpty()) {
//                                            TypeInputErrorHbox.setVisible(true);
//                                            if (MontantDepot.getText().isEmpty()) {
//                                                MontantInputErrorHbox.setVisible(true);
//                                                if (Nationalite.getText().isEmpty()) {
//                                                    NatioInputErrorHbox.setVisible(true);
//                                                    if (Profession.getText().isEmpty()) {
//                                                        ProffesionInputErrorHbox.setVisible(true);
//                                                        if (StatutMarital.getText().isEmpty()) {
//                                                            StatutnputErrorHbox.setVisible(true);
//                                                            if (Sexe.getText().isEmpty()) {
//                                                                SexenputErrorHbox.setVisible(true);
//                                                            }
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    } else {
//                if (NumCin.getText().isEmpty()) {
//                    NumCinInputErrorHbox.setVisible(true);
//                    if (NumTel.getText().isEmpty()) {
//                        NumTelInputErrorHbox.setVisible(true);
//                        if (Nom.getText().isEmpty()) {
//                            NomInputErrorHbox.setVisible(true);
//                            if (Prenom.getText().isEmpty()) {
//                                PrenomnputErrorHbox.setVisible(true);
//                                if (TypeCompte.getSelectionModel().isEmpty()) {
//                                    TypeInputErrorHbox.setVisible(true);
//                                    if (MontantDepot.getText().isEmpty()) {
//                                        MontantInputErrorHbox.setVisible(true);
//                                        if (Nationalite.getText().isEmpty()) {
//                                            NatioInputErrorHbox.setVisible(true);
//                                            if (Profession.getText().isEmpty()) {
//                                                ProffesionInputErrorHbox.setVisible(true);
//                                                if (StatutMarital.getText().isEmpty()) {
//                                                    StatutnputErrorHbox.setVisible(true);
//                                                }
//




