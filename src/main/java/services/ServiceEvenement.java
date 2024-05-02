package services;

import Entities.Evenement;
import Entities.Project;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEvenement implements IService<Evenement> {

    @Override
    public void ajouter(Evenement evenement) throws SQLException {
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement("INSERT INTO evenement (nom,img, description, date_debut, date_fin, lieu, organisateur, prix ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, evenement.getNom());
            preparedStatement.setString(2, evenement.getImg());
            preparedStatement.setString(3, evenement.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(evenement.getDateDebut()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(evenement.getDateFin()));
            preparedStatement.setString(6, evenement.getLieu());
            preparedStatement.setString(7, evenement.getOrganisateur());
            preparedStatement.setDouble(8, evenement.getPrix());
            preparedStatement.executeUpdate();
            System.out.println("Evenement ajouté");
        }
    }

    public static List<Evenement> searchEvenement(String search) {
        List<Evenement> evenements = new ArrayList<>();
        try {
            String query = "SELECT * FROM evenement WHERE nom LIKE ? OR description LIKE ? OR prix LIKE ?";
            PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement(query);
            preparedStatement.setString(1, "%" + search + "%");
            preparedStatement.setString(2, "%" + search + "%");
            preparedStatement.setString(3, "%" + search + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Evenement evenement = new Evenement();
                evenement.setId(resultSet.getInt("id"));
                evenement.setNom(resultSet.getString("nom"));
                evenement.setImg(resultSet.getString("img"));
                evenement.setDescription(resultSet.getString("description"));
                evenement.setDateDebut(resultSet.getTimestamp("date_debut").toLocalDateTime());
                evenement.setDateFin(resultSet.getTimestamp("date_fin").toLocalDateTime());
                evenement.setLieu(resultSet.getString("lieu"));
                evenement.setOrganisateur(resultSet.getString("organisateur"));
                evenement.setPrix(resultSet.getDouble("prix"));
                evenement.setLikes(resultSet.getInt("likes"));
                evenement.setDislikes(resultSet.getInt("dislikes"));
                evenement.setProjectId(resultSet.getInt("project_id"));
                evenements.add(evenement);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evenements;
    }

    @Override
    public void ajouter1(Evenement evenement, int projectId) throws SQLException {
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement("INSERT INTO evenement (nom,img, description, date_debut, date_fin, lieu, organisateur, prix, project_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, evenement.getNom());
            preparedStatement.setString(2, evenement.getImg());
            preparedStatement.setString(3, evenement.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(evenement.getDateDebut()));
            preparedStatement.setTimestamp(5, Timestamp.valueOf(evenement.getDateFin()));
            preparedStatement.setString(6, evenement.getLieu());
            preparedStatement.setString(7, evenement.getOrganisateur());
            preparedStatement.setDouble(8, evenement.getPrix());
            preparedStatement.setInt(9, projectId); // Set the project_id
            preparedStatement.executeUpdate();
            System.out.println("Evenement ajouté");
        }
    }

    public List<String> getAllProjectNames() throws SQLException {
        List<String> projectNames = new ArrayList<>();
        try (Statement statement = DataSource.getInstance().getCon().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT nomProjet FROM project")) {
            while (resultSet.next()) {
                projectNames.add(resultSet.getString("nomProjet"));
            }
        }
        return projectNames;
    }

    public int getProjectIdByName(String projectName) throws SQLException {
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement("SELECT id FROM project WHERE nomProjet = ?")) {
            preparedStatement.setString(1, projectName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            } else {
                throw new SQLException("Project not found: " + projectName);
            }
        }
    }


    @Override
    public void modifier(Evenement evenement) throws SQLException {
        String query = "UPDATE evenement SET nom=?, description=?, date_debut=?, date_fin=?, lieu=?, organisateur=?, prix=?, likes=?, dislikes=? WHERE id=?";
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setString(1, evenement.getNom());
            preparedStatement.setString(2, evenement.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(evenement.getDateDebut()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(evenement.getDateFin()));
            preparedStatement.setString(5, evenement.getLieu());
            preparedStatement.setString(6, evenement.getOrganisateur());
            preparedStatement.setDouble(7, evenement.getPrix());
            preparedStatement.setInt(8, evenement.getLikes());
            preparedStatement.setInt(9, evenement.getDislikes());
            preparedStatement.setInt(10, evenement.getId());
            preparedStatement.executeUpdate();
            System.out.println("Project updated successfully");
            preparedStatement.close();
        }
    }
    @Override
    public Evenement getOneEvenement(int idEvenement) throws SQLException {
        String req = "SELECT * FROM `evenement` where id = ?";
        PreparedStatement ps = DataSource.getInstance().getCon().prepareStatement(req);
        ps.setInt(1, idEvenement);

        ResultSet resultSet = ps.executeQuery();
        Evenement evenement = new Evenement();
        while (resultSet.next()) {
            evenement.setId(resultSet.getInt("id"));
            evenement.setNom(resultSet.getString("nom"));
            evenement.setImg(resultSet.getString("img"));
            evenement.setDescription(resultSet.getString("description"));
            evenement.setDateDebut(resultSet.getTimestamp("date_debut").toLocalDateTime());
            evenement.setDateFin(resultSet.getTimestamp("date_fin").toLocalDateTime());
            evenement.setLieu(resultSet.getString("lieu"));
            evenement.setOrganisateur(resultSet.getString("organisateur"));
            evenement.setPrix(resultSet.getDouble("prix"));
            evenement.setLikes(resultSet.getInt("likes"));
            evenement.setDislikes(resultSet.getInt("dislikes"));
            evenement.setProjectId(resultSet.getInt("project_id"));
        }
        ps.close();
        return evenement;
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM evenement WHERE id=?";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Evenement supprimée avec succès");
        }
    }



    @Override
    public Evenement afficher1(int id) throws SQLException {
        String query = "SELECT * FROM evenement WHERE id = ?";
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Evenement evenement = new Evenement();
                    evenement.setId(resultSet.getInt("id"));
                    evenement.setNom(resultSet.getString("nom"));
                    evenement.setImg(resultSet.getString("img"));
                    evenement.setDescription(resultSet.getString("description"));
                    evenement.setDateDebut(resultSet.getTimestamp("date_debut").toLocalDateTime());
                    evenement.setDateFin(resultSet.getTimestamp("date_fin").toLocalDateTime());
                    evenement.setLieu(resultSet.getString("lieu"));
                    evenement.setOrganisateur(resultSet.getString("organisateur"));
                    evenement.setPrix(resultSet.getDouble("prix"));
                    evenement.setLikes(resultSet.getInt("likes"));
                    evenement.setDislikes(resultSet.getInt("dislikes"));
                    evenement.setProjectId(resultSet.getInt("project_id"));
                    return evenement;
                } else {
                    throw new SQLException("Evenement not found");
                }

            }
        }
    }

    @Override
    public List<Evenement> afficher() throws SQLException {
        List<Evenement> evenements = new ArrayList<>();
        String query = "SELECT * FROM evenement";
        try (PreparedStatement preparedStatement = DataSource.getInstance().getCon().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Evenement evenement = new Evenement();
                evenement.setId(resultSet.getInt("id"));
                evenement.setNom(resultSet.getString("nom"));
                evenement.setImg(resultSet.getString("img"));
                evenement.setDescription(resultSet.getString("description"));
                evenement.setDateDebut(resultSet.getTimestamp("date_debut").toLocalDateTime());
                evenement.setDateFin(resultSet.getTimestamp("date_fin").toLocalDateTime());
                evenement.setLieu(resultSet.getString("lieu"));
                evenement.setOrganisateur(resultSet.getString("organisateur"));
                evenement.setPrix(resultSet.getDouble("prix"));
                evenement.setLikes(resultSet.getInt("likes"));
                evenement.setDislikes(resultSet.getInt("dislikes"));
                evenement.setProjectId(resultSet.getInt("project_id"));
                evenements.add(evenement);
            }
        }
        return evenements;
    }

}
