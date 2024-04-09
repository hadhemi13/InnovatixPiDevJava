package services;

import Entities.Reclamation;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceReclamation implements IServiceReclamation<Reclamation> {
    Connection connection;

    public ServiceReclamation(){
        connection= MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Reclamation reclamation) throws SQLException {
        String req = "INSERT INTO reclamation (objet_rec, contenu_rec, adr_rec, nom_aut_rec, dep_rec, statut_rec, piece_jrec, date_rec) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, reclamation.getObjet_rec());
            preparedStatement.setString(2, reclamation.getContenu_rec());
            preparedStatement.setString(3, reclamation.getAdr_rec());
            preparedStatement.setString(4, reclamation.getNom_aut_rec());
            preparedStatement.setString(5, reclamation.getDep_rec());
            preparedStatement.setString(6, reclamation.getStatut_rec());
            preparedStatement.setString(7, reclamation.getPiece_jrec());
            preparedStatement.setTimestamp(8, Timestamp.valueOf(reclamation.getDate_rec()));


            preparedStatement.executeUpdate();
            System.out.println("Réclamation ajoutée");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réclamation : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(Reclamation reclamation) throws SQLException {
        try {
            String req = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,reclamation.getId());
            preparedStatement.executeUpdate();
            System.out.println("Réclamation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }




    @Override
    public List<Reclamation> afficher() throws SQLException {
        List<Reclamation> reclamations= new ArrayList<>();
        String req="select * from reclamation";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(req);
        while (rs.next()){
            Reclamation reclamation= new Reclamation();
            reclamation.setId(rs.getInt("id"));
            reclamation.setObjet_rec(rs.getString("objet_rec"));
            reclamation.setContenu_rec(rs.getString("contenu_rec"));
            reclamation.setAdr_rec(rs.getString("adr_rec"));
            reclamation.setNom_aut_rec(rs.getString("nom_aut_rec"));
            reclamation.setDep_rec(rs.getString("dep_rec"));
            reclamation.setStatut_rec(rs.getString("statut_rec"));
            reclamation.setPiece_jrec(rs.getString("piece_jrec"));
            reclamation.setDate_rec(rs.getTimestamp("date_rec").toLocalDateTime());

            reclamations.add(reclamation);
        }
        return reclamations;
    }

}


