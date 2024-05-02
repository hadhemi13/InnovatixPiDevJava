package services;
import Entities.Cheque;
import utils.MyDatabase;
import Entities.Virement;
import utils.MyDatabase;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceVirement implements IServiceVirement <Virement> {

    private Connection connection;
    public Statement statement;
    public ServiceVirement() {
        connection = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Virement virement) throws SQLException {
        String req = "INSERT INTO virement "
                + "(compte_id, user_id, nomet_prenom, type_virement, transferez_a, num_beneficiare, "
                + "montant, cin, rib, email, decision_v, actions_v, actions_em, photo_cin_v, phone_number) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, virement.getCompte_id());
            ps.setInt(2, virement.getUser_id());
            ps.setString(3, virement.getNomet_prenom());
            ps.setString(4, virement.getType_virement());
            ps.setString(5, virement.getTransferez_a());
            ps.setInt(6, virement.getNum_beneficiare());
            ps.setString(7, virement.getMontant());
            ps.setInt(8, virement.getCin());
            ps.setInt(9, virement.getRib());
            ps.setString(10, virement.getEmail());
            ps.setString(11, virement.getDecision_v());
            ps.setString(12, virement.getPhoto_cin_v());
            ps.setString(13, virement.getPhone_number());

            ps.executeUpdate();
            System.out.println("Virement ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }
    // Virement virement = new Virement(typee,montant,aa,transferez,Cin,Nom,image,decisionV);
    public void ajouterV(Virement virement) throws SQLException {
        String req = "INSERT INTO virement "
                + "(nomet_prenom, type_virement, transferez_a, "
                + "montant, cin, decision_v,photo_cin_v, phone_number,num_beneficiare, qrCode) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, virement.getNomet_prenom());
            ps.setString(2, virement.getType_virement());
            ps.setString(3, virement.getTransferez_a());
            ps.setString(4, virement.getMontant());
            ps.setInt(5, virement.getCin());
            ps.setString(6, virement.getDecision_v());
            ps.setString(7, virement.getPhoto_cin_v());
            ps.setString(8, virement.getPhone_number());
            ps.setInt(9, virement.getNum_beneficiare());
            ps.setString(10, virement.getQrCode()); // Set the qrCode field

            ps.executeUpdate();
            System.out.println("Virement ajouté avec succès !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }



    @Override
    public void modifier(Virement virement) throws SQLException {
        String req = "UPDATE virement SET  nomet_prenom=?, type_virement=?, "
                + "transferez_a=?, num_beneficiare=?, montant=?, cin=?, rib=?, email=?, decision_v=?, "
                + "photo_cin_v=?, phone_number=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setString(1, virement.getNomet_prenom());
            ps.setString(2, virement.getType_virement());
            ps.setString(3, virement.getTransferez_a());
            ps.setInt(4, virement.getNum_beneficiare());
            ps.setString(5, virement.getMontant());
            ps.setInt(6, virement.getCin());
            ps.setInt(7, virement.getRib());
            ps.setString(8, virement.getEmail());
            ps.setString(9, virement.getDecision_v());
            ps.setString(10, virement.getPhoto_cin_v());
            ps.setString(11, virement.getPhone_number());
            ps.setInt(12, virement.getId());

            ps.executeUpdate();
            System.out.println("Virement avec ID " + virement.getId() + " modifié !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification du virement : " + e.getMessage());
        }
    }


    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM virement WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(req)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Virement avec ID " + id + " supprimé !");
            } else {
                System.out.println("Aucun virement trouvé avec l'ID " + id);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du virement : " + e.getMessage());
        }
    }

//    @Override
//    public List<Virement> afficher() throws SQLException {
//        List<Virement> list = new ArrayList<>();
//
//        try {
//            String req = "SELECT * FROM virement";
//            Statement st = connection.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                list.add(new Virement(rs.getInt("id"), rs.getInt("compte_id"),
//                        rs.getInt("user_id"), rs.getString("nomet_prenom"),
//                        rs.getString("type_virement"), rs.getString("transferez_a"),
//                        rs.getInt("num_beneficiare"), rs.getString("montant"),
//                        rs.getInt("cin"), rs.getInt("rib"), rs.getString("email"),
//                        rs.getString("decision_v") , rs.getString("photo_cin_v"),
//                        rs.getString("phone_number")));
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return list;
//    }

    @Override
    public List<Virement> afficher() throws SQLException {
        List<Virement> list = new ArrayList<>();
        String req = "SELECT * FROM virement";
        try (Statement st = connection.createStatement(); ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                Virement virement = new Virement(
                        rs.getInt("id"),
                        rs.getInt("compte_id"),
                        rs.getInt("user_id"),
                        rs.getString("nomet_prenom"),
                        rs.getString("type_virement"),
                        rs.getString("transferez_a"),
                        rs.getInt("num_beneficiare"),
                        rs.getString("montant"),
                        rs.getInt("cin"),
                        rs.getInt("rib"),
                        rs.getString("email"),
                        rs.getString("decision_v"),
                        rs.getString("photo_cin_v"),
                        rs.getString("phone_number"),
                        rs.getString("qrCode")
                );
                list.add(virement);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des virements : " + e.getMessage());
        }
        System.out.println(list);
        return list;
    }

    public Virement getById(int id) throws SQLException {
        Virement virement = new Virement();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = "SELECT * FROM virement WHERE id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        // Exécuter la requête
        rs = statement.executeQuery();

        // Parcourir les résultats
        if (rs.next()) {
            // Construire un objet Cheque à partir des données de la base de données
            virement = new Virement(
                    rs.getInt("id"),
                    rs.getInt("compte_id"),
                    rs.getInt("user_id"),
                    rs.getString("nomet_prenom"),
                    rs.getString("type_virement"),
                    rs.getString("transferez_a"),
                    rs.getInt("num_beneficiare"),
                    rs.getString("montant"),
                    rs.getInt("cin"),
                    rs.getInt("rib"),
                    rs.getString("email"),
                    rs.getString("decision_v"),
                    rs.getString("photo_cin_v"),
                    rs.getString("phone_number")
            );
        }

        return virement;
    }



}

