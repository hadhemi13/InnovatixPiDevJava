package services;

import Entities.DemandeStage;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDemandeStage implements IService<DemandeStage> {
    private Connection connection;
    public ServiceDemandeStage() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(DemandeStage demandeStage) throws SQLException {


        String req = "insert into demandestage " +
                "(nom,prenom,email,numeroTelephone,lettremotivation,cv,domaine,etat,date)" +
                "values(?,?,?,?,?,?,?,?)";
        // LocalDateTime dateTime = LocalDateTime.now();

// Définir la date dans l'objet demandeStage
//        demandeStage.setDate(dateTime);
//        demandeStage.setEtat("encours");
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,demandeStage.getNom());
            ps.setString(2,demandeStage.getPrenom());
            ps.setString(3,demandeStage.getEmail());
            ps.setInt(4,demandeStage.getNumeroTelephone());
            ps.setString(5,demandeStage.getLettremotivation());
            ps.setString(6,demandeStage.getCv());
            ps.setString(7,demandeStage.getDomaine());
            ps.setString(8,demandeStage.getEtat());
            ps.setDate(9,demandeStage.getDate());
            ps.executeUpdate();
            System.out.println("ajouté");

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "delete from demandestage where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Demande Stage avec ID "+ id + "est supprimer");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void modifier(DemandeStage demandeStage) throws SQLException {
        String req = "update demandestage set nom=?,prenom=?,email=?,numeroTelephone=?,lettremotivation=?,cv=?,domaine=?,etat=? WHERE id=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,demandeStage.getNom());
            ps.setString(2,demandeStage.getPrenom());
            ps.setString(3,demandeStage.getEmail());
            ps.setInt(4,demandeStage.getNumeroTelephone());
            ps.setString(5,demandeStage.getLettremotivation());
            ps.setString(6,demandeStage.getCv());
            ps.setString(7,demandeStage.getDomaine());
            ps.setString(8,demandeStage.getEtat());
            ps.setInt(9,demandeStage.getId());
            ps.executeUpdate();
            System.out.println("modification avec succées");
            System.out.println(demandeStage);

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public List<DemandeStage> afficher() throws SQLException {
        List<DemandeStage> list = new ArrayList<>();
        try {
            String req = "select * from demandestage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                list.add(new DemandeStage(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getString("lettremotivation"),rs.getString("cv"),rs.getString("domaine"),rs.getInt("numerotelephone"),rs.getInt("score"), rs.getDate("date")));
                //LocalDateTime dt = list.getDate("date").toLocalDate;
                //System.out.println(list);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        for (DemandeStage i : list){

            System.out.println("Offre de stage{" +
                    ", id=" + i.getId() +
                    "nom='" + i.getNom() + '\'' +
                    ", prenom='" + i.getPrenom() + '\'' +
                    ", email='" + i.getEmail() + '\'' +
                    ", cv='" + i.getCv() + '\'' +
                    ", domaine='" + i.getDomaine() + '\'' +
                    ", date='" + i.getDate() + '\'' +
                    '}');
        }
        return list;
    }

    @Override
    public void afficheUne(int id) throws SQLException {

    }
}
