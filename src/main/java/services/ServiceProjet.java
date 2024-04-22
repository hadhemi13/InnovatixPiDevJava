package services;

import Entities.Project;
import utils.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ServiceProjet implements IService<Project> {
    @Override
    public void ajouter(Project projet) throws SQLException {
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement("INSERT INTO project (nomprojet, img, categorie, descriptionprojet, budgetprojet, datecreation, dureeprojet, statutprojet) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, projet.getNomProjet());
            preparedStatement.setString(2, projet.getImg());
            preparedStatement.setString(3, projet.getCategorie());
            preparedStatement.setString(4, projet.getDescriptionProjet());
            preparedStatement.setDouble(5, projet.getBudgetProjet());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(projet.getDateCreation()));
            preparedStatement.setInt(7, projet.getDureeProjet());
            preparedStatement.setInt(8, projet.getStatutProjet());
            preparedStatement.executeUpdate();
        }
    }


    public Project getOneProject(int id) throws SQLException {
        String req = "SELECT * FROM `project` where id = ?";
        PreparedStatement ps = DataSource.getInstance().getCon().prepareStatement(req);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Project project = new Project();
        project.setId(-999);

        while (rs.next()) {
            project.setId(rs.getInt("id"));
            project.setNomProjet(rs.getString("nomprojet"));
            project.setDescriptionProjet(rs.getString("descriptionprojet"));
            project.setCategorie(rs.getString("categorie"));
            project.setBudgetProjet(rs.getInt("budgetprojet"));
            project.setDateCreation(rs.getTimestamp("datecreation").toLocalDateTime());
            project.setDureeProjet(rs.getInt("dureeprojet"));
            project.setStatutProjet(rs.getInt("statutprojet"));
            project.setImg(rs.getString("img"));

        }
        ps.close();
        return project;
    }


    public List<Project> getAllProject() throws SQLException {
        List<Project> projets = new ArrayList<>();
        String query = "SELECT * FROM project";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Project projet = new Project();
                projet.setId(resultSet.getInt("id"));

                projet.setNomProjet(resultSet.getString("nomprojet"));
                projet.setImg(resultSet.getString("img"));
                projet.setCategorie(resultSet.getString("categorie"));
                projet.setDescriptionProjet(resultSet.getString("descriptionprojet"));
                projet.setBudgetProjet(resultSet.getDouble("budgetprojet"));
                projet.setDateCreation(resultSet.getTimestamp("datecreation").toLocalDateTime());
                projet.setDureeProjet(resultSet.getInt("dureeprojet"));
                projet.setStatutProjet(resultSet.getInt("statutprojet"));
                projets.add(projet);
            }
        }
        return projets;
    }

    @Override
    public void ajouter1(Project project, int projectId) throws SQLException {

    }

    @Override
    public void modifier(Project projet) throws SQLException {
        String query = "UPDATE project SET nomprojet=?, img=?, categorie=?, descriptionprojet=?, budgetprojet=?, datecreation=?, dureeprojet=?, statutprojet=? WHERE id=?";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setString(1, projet.getNomProjet());
            preparedStatement.setString(2, projet.getImg());
            preparedStatement.setString(3, projet.getCategorie());
            preparedStatement.setString(4, projet.getDescriptionProjet());
            preparedStatement.setDouble(5, projet.getBudgetProjet());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(projet.getDateCreation()));
            preparedStatement.setInt(7, projet.getDureeProjet());
            preparedStatement.setInt(8, projet.getStatutProjet());
            preparedStatement.setInt(9, projet.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String query = "DELETE FROM project WHERE id=?";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Project> afficher() throws SQLException {
        List<Project> projets = new ArrayList<>();
        String query = "SELECT * FROM project";
        try (PreparedStatement preparedStatement = (PreparedStatement) DataSource.getInstance().getCon().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Project projet = new Project();
                projet.setId(resultSet.getInt("id"));

                projet.setNomProjet(resultSet.getString("nomprojet"));
                projet.setImg(resultSet.getString("img"));
                projet.setCategorie(resultSet.getString("categorie"));
                projet.setDescriptionProjet(resultSet.getString("descriptionprojet"));
                projet.setBudgetProjet(resultSet.getDouble("budgetprojet"));
                projet.setDateCreation(resultSet.getTimestamp("datecreation").toLocalDateTime());
                projet.setDureeProjet(resultSet.getInt("dureeprojet"));
                projet.setStatutProjet(resultSet.getInt("statutprojet"));
                projets.add(projet);
            }
        }
        return projets;
    }

}
