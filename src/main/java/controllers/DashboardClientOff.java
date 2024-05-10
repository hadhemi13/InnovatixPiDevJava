package FXML;

import Entities.Cheque;
import Entities.Virement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ServiceCheque;
import services.ServiceVirement;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardClientOff implements Initializable {

    @FXML
    private Pane content_area;

    @FXML
    private PieChart stat1;

    @FXML
    private PieChart stat2;

    private int compteurVEcoresponsabilite = 0; // Déclaration et initialisation du compteur

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            statistiqueVirement(); // Appel de la méthode pour afficher les statistiques des virements
            statistiqueCheque(); // Appel de la méthode pour afficher les statistiques des chèques
            checkAndAwardFidelityPoints(); // Appel de la méthode pour vérifier et attribuer des points de fidélité
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void statistiqueVirement() throws SQLException {
        ServiceVirement serviceVirement = new ServiceVirement();

        List<Virement> virements = serviceVirement.afficher();

        int compteurVEcoresponsabilite = 0;
        int compteurPersonne = 0;

        for (Virement virement : virements) {
            String typeVirement = virement.getType_virement();
            if (typeVirement.equals("VEcoresponsabilite")) {
                compteurVEcoresponsabilite++;
            } else if (typeVirement.equals("Personne")) {
                compteurPersonne++;
            }
        }

        // Ajouter les données au diagramme circulaire
        stat1.getData().add(new PieChart.Data("VEcoresponsabilite", compteurVEcoresponsabilite));
        stat1.getData().add(new PieChart.Data("Personne", compteurPersonne));

        // Afficher les nombres dans le diagramme circulaire
        for (PieChart.Data data : stat1.getData()) {
            data.setName(data.getName() + " (" + (int)data.getPieValue() + ")");
        }
    }

    private void statistiqueCheque() throws SQLException {
        ServiceCheque serviceCheque = new ServiceCheque();

        List<Cheque> cheques = serviceCheque.afficher();

        int compteurPaiement = 0;
        int compteurPaiementEco = 0;

        for (Cheque cheque : cheques) {
            String beneficiaire = cheque.getBeneficiaire();
            if (beneficiaire.equals("Paiement")) {
                compteurPaiement++;
            } else if (beneficiaire.equals("PaiementEco")) {
                compteurPaiementEco++;
            }
        }

        // Ajouter les données au diagramme circulaire
        stat2.getData().add(new PieChart.Data("Paiement", compteurPaiement));
        stat2.getData().add(new PieChart.Data("Paiement Eco", compteurPaiementEco));

        // Afficher les nombres dans le diagramme circulaire
        for (PieChart.Data data : stat2.getData()) {
            if (data.getName().equals("Paiement")) {
                data.getNode().setStyle("-fx-pie-color: #00FF00;");
            } else if (data.getName().equals("Paiement Eco")) {
                data.getNode().setStyle("-fx-pie-color: #00DD00;");
            }

            data.setName(data.getName() + " (" + (int) data.getPieValue() + ")");
        }
    }

    private void checkAndAwardFidelityPoints() {
        // Vérifier si le nombre total de virements de type "VEcoresponsabilite" dépasse un multiple de 10
        if (compteurVEcoresponsabilite % 10 == 0 && compteurVEcoresponsabilite > 0) {
            // Appeler la méthode pour attribuer un pack de points de fidélité
            attribuerPointsFidelite();
        }
    }

    private void attribuerPointsFidelite() {
        // Mettre en œuvre la logique pour attribuer un pack de points de fidélité au compte bancaire
        // Cette méthode doit accéder au compte bancaire et ajouter le pack de points de fidélité
        // Implémentez cette méthode selon la logique et la structure de votre application
        // Par exemple, vous pouvez appeler un service qui gère les comptes bancaires pour ajouter les points de fidélité.
        // Ici, je vais simplement afficher un message pour simuler l'attribution des points.
        System.out.println("Pack de points de fidélité ajouté au compte bancaire !");
    }
}