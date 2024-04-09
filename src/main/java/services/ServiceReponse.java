package services;

import Entities.Reponse;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ServiceReponse implements IServiceReponse <Reponse> {
    Connection connection ;

    public ServiceReponse() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Reponse reponse) throws SQLException {
        String req = "INSERT INTO reponse (reclamation_id, adr_rep, date_rep, contenu_rep, piece_jrep) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, reponse.getReclamation_id());
            preparedStatement.setString(2, reponse.getAdr_rep());
            preparedStatement.setObject(3, reponse.getDate_rep());
            preparedStatement.setString(4, reponse.getContenu_rep());
            preparedStatement.setString(5, reponse.getPiece_jrep());

            preparedStatement.executeUpdate();
            System.out.println("Réponse ajoutée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réponse : " + e.getMessage());
        }

    }

    @Override
    public void supprimer(Reponse reponse) throws SQLException {
        try {
            String req = "DELETE FROM reponse WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, reponse.getId());
            preparedStatement.executeUpdate();
            System.out.println("Réponse supprimée");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de la réponse : " + ex.getMessage());

        }
    }
    @Override
    public void modifier(Reponse reponse) throws SQLException {
        String req = "UPDATE reponse SET contenu_rep=?, adr_rep=?, date_rep=?, piece_jrep=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, reponse.getContenu_rep());
            preparedStatement.setString(2, reponse.getAdr_rep());
            preparedStatement.setObject(3, reponse.getDate_rep());
            preparedStatement.setString(4, reponse.getPiece_jrep());
            preparedStatement.setInt(5, reponse.getId());
            preparedStatement.executeUpdate();
            System.out.println("Réponse modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de la réponse : " + ex.getMessage());
        }
    }

    @Override
    public List<Reponse> afficher() throws SQLException {

        List<Reponse> reponses = new ArrayList<>();
        String req = "SELECT * FROM reponse";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(req)) {
            while (rs.next()) {
                Reponse reponse = new Reponse();
                reponse.setReclamation_id(rs.getInt("reclamation_id"));
                reponse.setAdr_rep(rs.getString("adr_rep"));
                reponse.setDate_rep(rs.getTimestamp("date_rep").toLocalDateTime());
                reponse.setContenu_rep(rs.getString("contenu_rep"));
                reponse.setPiece_jrep(rs.getString("piece_jrep"));
                reponses.add(reponse);
            }
        }
        return reponses;
    }
    public Reponse getReponseById(int id) throws SQLException {
        String req = "SELECT * FROM reponse WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return mapResultSetToReponse(rs);
            }
        }
        throw new SQLException("Reponse non trouvée avec l'ID : " + id);
    }

    private Reponse mapResultSetToReponse(ResultSet rs) throws SQLException {
        return new Reponse(
                rs.getInt("id"),
                rs.getInt("reclamation_id"),
                rs.getInt("user_id"),
                rs.getString("adr_rep"),
                rs.getTimestamp("date_rep").toLocalDateTime(),
                rs.getString("contenu_rep"),
                rs.getString("piece_jrep")
        );
    }

}

