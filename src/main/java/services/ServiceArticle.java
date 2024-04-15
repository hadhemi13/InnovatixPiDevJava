package services;

import Entities.Article;
import utils.MyDatabase;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceArticle  implements IServiceArticle<Article> {
    Connection connection;

    public ServiceArticle() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Article article) throws SQLException {
        String req = "INSERT INTO article (nom_aut_art, adr_aut_art, date_pub_art, duree_art, categorie_art, titre_art, contenu_art, piecejointe_art, image_art,likes ,dislikes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? ,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, article.getNom_aut_art());
            preparedStatement.setString(2, article.getAdr_aut_art());
            preparedStatement.setObject(3, article.getDate_pub_art());
            preparedStatement.setInt(4, article.getDuree_art());
            preparedStatement.setString(5, article.getCategorie_art());
            preparedStatement.setString(6, article.getTitre_art());
            preparedStatement.setString(7, article.getContenu_art());
            preparedStatement.setString(8, article.getPiecejointe_art());
            preparedStatement.setString(9, article.getImage_art());
            preparedStatement.setInt(10, article.getLikes());
            preparedStatement.setInt(11, article.getDislikes());


            preparedStatement.executeUpdate();
            System.out.println("Article ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'article : " + e.getMessage());
        }
    }
//@Override
//public void ajouter(Article article) throws SQLException {
//    String req = "INSERT INTO article ( date_pub_art, duree_art, categorie_art, titre_art, contenu_art) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? ,?)";
//    try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
//
//        preparedStatement.setObject(3, article.getDate_pub_art());
//        preparedStatement.setInt(4, article.getDuree_art());
//        preparedStatement.setString(5, article.getCategorie_art());
//        preparedStatement.setString(6, article.getTitre_art());
//        preparedStatement.setString(7, article.getContenu_art());
//
//
//        preparedStatement.executeUpdate();
//        System.out.println("Article ajouté");
//    } catch (SQLException e) {
//        System.out.println("Erreur lors de l'ajout de l'article : " + e.getMessage());
//    }
//}

    @Override
    public void supprimer(Article article) throws SQLException {
        try {
            String req = "DELETE FROM article WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1, article.getId());
            preparedStatement.executeUpdate();
            System.out.println("Article supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de l'article : " + ex.getMessage());
        }
    }

    @Override
    public void modifier(Article article) throws SQLException {
        String req = "UPDATE article SET nom_aut_art=?, adr_aut_art=?, date_pub_art=?, duree_art=?, categorie_art=?, titre_art=?, contenu_art=?, piecejointe_art=?, image_art=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, article.getNom_aut_art());
            preparedStatement.setString(2, article.getAdr_aut_art());
            preparedStatement.setObject(3, article.getDate_pub_art());
            preparedStatement.setInt(4, article.getDuree_art());
            preparedStatement.setString(5, article.getCategorie_art());
            preparedStatement.setString(6, article.getTitre_art());
            preparedStatement.setString(7, article.getContenu_art());
            preparedStatement.setString(8, article.getPiecejointe_art());
            preparedStatement.setString(9, article.getImage_art());
            preparedStatement.setInt(10, article.getId());

            preparedStatement.executeUpdate();
            System.out.println("Article modifié avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de l'article : " + ex.getMessage());
        }
    }

    @Override
    public List<Article> afficher() throws SQLException {
        List<Article> articles = new ArrayList<>();
        String req = "SELECT * FROM article";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(req)) {
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getInt("id"));
                article.setNom_aut_art(rs.getString("nom_aut_art"));
                article.setAdr_aut_art(rs.getString("adr_aut_art"));
                article.setDate_pub_art(rs.getTimestamp("date_pub_art").toLocalDateTime());
                article.setDuree_art(rs.getInt("duree_art"));
                article.setCategorie_art(rs.getString("categorie_art"));
                article.setTitre_art(rs.getString("titre_art"));
                article.setContenu_art(rs.getString("contenu_art"));
                article.setPiecejointe_art(rs.getString("piecejointe_art"));
                article.setImage_art(rs.getString("image_art"));

                articles.add(article);
            }
        }
        return articles;
    }
    public Article getArticleById(int id) throws SQLException {
        String req = "SELECT * FROM article WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return mapResultSetToArticle(rs);
            }
        }
        throw new SQLException("Article non trouvé avec l'ID : " + id);
    }

    private Article mapResultSetToArticle(ResultSet rs) throws SQLException {
        return new Article(
                rs.getInt("id"),
                rs.getString("nom_aut_art"),
                rs.getString("adr_aut_art"),
                rs.getTimestamp("date_pub_art").toLocalDateTime(),
                rs.getInt("duree_art"),
                rs.getString("categorie_art"),
                rs.getString("titre_art"),
                rs.getString("contenu_art"),
                rs.getString("piecejointe_art"),
                rs.getString("image_art"),
                rs.getInt("likes"),
                rs.getInt("dislikes")


        );
    }

}

