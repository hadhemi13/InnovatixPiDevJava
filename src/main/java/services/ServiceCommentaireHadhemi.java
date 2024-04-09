package services;

import Entities.CommentaireHadhemi;
import utils.MyDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommentaireHadhemi  implements IServiceCommentaireHadhemi<CommentaireHadhemi> {

    private Connection connection;

    public ServiceCommentaireHadhemi() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(CommentaireHadhemi commentaire) throws SQLException {
        String req = "INSERT INTO commentaire_hadhemi (contenu, date_creation, nom_aut_com, article_id, image_u) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, commentaire.getContenu());
            preparedStatement.setObject(2, commentaire.getDate_creation());
            preparedStatement.setString(3, commentaire.getNom_aut_com());
            preparedStatement.setInt(4, commentaire.getArticle_id());
            preparedStatement.setString(5, commentaire.getImage_u());

            preparedStatement.executeUpdate();
            System.out.println("Commentaire ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du commentaire : " + e.getMessage());
        }
    }

    @Override
    public void supprimer(CommentaireHadhemi commentaire) throws SQLException {
        try {
            String req = "DELETE FROM commentaire_hadhemi WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, commentaire.getId());
            preparedStatement.executeUpdate();
            System.out.println("Commentaire supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression du commentaire : " + ex.getMessage());
        }
    }

    @Override
    public void modifier(CommentaireHadhemi commentaire) throws SQLException {
        String req = "UPDATE commentaire_hadhemi SET contenu=?, date_creation=?, nom_aut_com=?, image_u=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, commentaire.getContenu());
            preparedStatement.setObject(2, commentaire.getDate_creation());
            preparedStatement.setString(3, commentaire.getNom_aut_com());
            preparedStatement.setString(4, commentaire.getImage_u());
            preparedStatement.setInt(5, commentaire.getId());
            preparedStatement.executeUpdate();
            System.out.println("Commentaire modifié avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du commentaire : " + ex.getMessage());
        }
    }

    @Override
    public List<CommentaireHadhemi> afficher() throws SQLException {
        List<CommentaireHadhemi> commentaires = new ArrayList<>();
        String req = "SELECT * FROM commentaire_hadhemi";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(req)) {
            while (rs.next()) {
                CommentaireHadhemi commentaire = new CommentaireHadhemi();
                commentaire.setId(rs.getInt("id"));
                commentaire.setContenu(rs.getString("contenu"));
                commentaire.setDate_creation(rs.getObject("date_creation", LocalDateTime.class));
                commentaire.setNom_aut_com(rs.getString("nom_aut_com"));
                commentaire.setArticle_id(rs.getInt("article_id"));
                commentaire.setImage_u(rs.getString("image_u"));
                commentaires.add(commentaire);
            }
        }
        return commentaires;
    }

    public CommentaireHadhemi getCommentaireById(int id) throws SQLException {
        String req = "SELECT * FROM commentaire_hadhemi WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return mapResultSetToCommentaire(rs);
            }
        }
        throw new SQLException("Commentaire non trouvé avec l'ID : " + id);
    }

    private CommentaireHadhemi mapResultSetToCommentaire(ResultSet rs) throws SQLException {
        return new CommentaireHadhemi(
                rs.getInt("id"),
                rs.getString("contenu"),
                rs.getObject("date_creation", LocalDateTime.class),
                rs.getString("nom_aut_com"),
                rs.getInt("article_id"),
                rs.getString("image_u")
        );
    }

}
