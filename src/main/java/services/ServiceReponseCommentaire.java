package services;

import Entities.ReponseCommentaire;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceReponseCommentaire  implements IServiceReponseCommentaire<ReponseCommentaire> {
    Connection connection;

    public ServiceReponseCommentaire() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(ReponseCommentaire reponseCommentaire) throws SQLException {
        String req = "INSERT INTO reponse_commentaire (contenu_rep_com, nom_rep_com, date_rep_com, commentaire_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, reponseCommentaire.getContenu_rep_com());
            preparedStatement.setString(2, reponseCommentaire.getNom_rep_com());
            preparedStatement.setObject(3, reponseCommentaire.getDate_rep_com());
            preparedStatement.setInt(4, reponseCommentaire.getCommentaire_id());
            preparedStatement.executeUpdate();
            System.out.println("Réponse au commentaire ajoutée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réponse au commentaire : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(ReponseCommentaire reponseCommentaire) throws SQLException {
        try {
            String req = "DELETE FROM reponse_commentaire WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, reponseCommentaire.getId());
            preparedStatement.executeUpdate();
            System.out.println("Réponse au commentaire supprimée");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de la réponse au commentaire : " + ex.getMessage());
        }
    }

    @Override
    public void modifier(ReponseCommentaire reponseCommentaire) throws SQLException {
        String req = "UPDATE reponse_commentaire SET contenu_rep_com=?, nom_rep_com=?, date_rep_com=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, reponseCommentaire.getContenu_rep_com());
            preparedStatement.setString(2, reponseCommentaire.getNom_rep_com());
            preparedStatement.setObject(3, reponseCommentaire.getDate_rep_com());
            preparedStatement.setInt(4, reponseCommentaire.getId());
            preparedStatement.executeUpdate();
            System.out.println("Réponse au commentaire modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de la réponse au commentaire : " + ex.getMessage());
        }
    }

    @Override
    public List<ReponseCommentaire> afficher() throws SQLException {
        List<ReponseCommentaire> reponses = new ArrayList<>();
        String req = "SELECT * FROM reponse_commentaire";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(req)) {
            while (rs.next()) {
                ReponseCommentaire reponseCommentaire = new ReponseCommentaire();
                reponseCommentaire.setContenu_rep_com(rs.getString("contenu_rep_com"));
                reponseCommentaire.setNom_rep_com(rs.getString("nom_rep_com"));
                reponseCommentaire.setDate_rep_com(rs.getTimestamp("date_rep_com").toLocalDateTime());
                reponseCommentaire.setCommentaire_id(rs.getInt("commentaire_id"));
                reponses.add(reponseCommentaire);
            }
        }
        return reponses;
    }

    public ReponseCommentaire getReponseCommentaireById(int id) throws SQLException {
        String req = "SELECT * FROM reponse_commentaire WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return mapResultSetToReponseCommentaire(rs);
            }
        }
        throw new SQLException("Réponse au commentaire non trouvée avec l'ID : " + id);
    }

    private ReponseCommentaire mapResultSetToReponseCommentaire(ResultSet rs) throws SQLException {
        return new ReponseCommentaire(
                rs.getInt("id"),
                rs.getString("contenu_rep_com"),
                rs.getString("nom_rep_com"),
                rs.getTimestamp("date_rep_com").toLocalDateTime(),
                rs.getInt("commentaire_id")
        );
    }
}