package services;

import Entities.OffreDeStage;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceOffreDeStage implements IService<OffreDeStage>{
    private Connection connection;
    public ServiceOffreDeStage() {
        connection = MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(OffreDeStage offreDeStage) throws SQLException {

        String req = "insert into offre_stage " +
                "(title,domaine,type_offre,poste_propose,experience,description,exigence_offre)" +
                "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,offreDeStage.getTitle());
            ps.setString(2,offreDeStage.getDomaine());
            ps.setString(3,offreDeStage.getTypeOffre());
            ps.setInt(4,offreDeStage.getPostePropose());
            ps.setString(5,offreDeStage.getExperience());

//            ps.setString(6,offreDeStage.getLanguage());
            ps.setString(6,offreDeStage.getDescription());
            ps.setString(7,offreDeStage.getExigenceOffre());
            ps.executeUpdate();
            System.out.println("ajouté");

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void modifier(OffreDeStage offreDeStage) throws SQLException {
        String req = "Update offre_stage set title=?,domaine=?,type_offre=?,poste_propose=?,experience=?,description=?,exigence_offre=? where id=?)" +
                "values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,offreDeStage.getTitle());
            ps.setString(2,offreDeStage.getDomaine());
            ps.setString(3,offreDeStage.getTypeOffre());
            ps.setInt(4,offreDeStage.getPostePropose());
            ps.setString(5,offreDeStage.getExperience());

//            ps.setString(6,offreDeStage.getLanguage());
            ps.setString(6,offreDeStage.getDescription());
            ps.setString(7,offreDeStage.getExigenceOffre());
            ps.setInt(8,offreDeStage.getId());
            ps.executeUpdate();
            System.out.println("ajouté");

        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "delete from offre_stage where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Offfre Stage avec ID "+ id + "est supprimer");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<OffreDeStage> afficher() throws SQLException {
        List<OffreDeStage> list = new ArrayList<>();
        try {
            String req = "select * from offre_stage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                list.add(new OffreDeStage(rs.getInt("poste_propose"),rs.getString("title"),rs.getString("domaine"), rs.getString("type_offre"),rs.getString("experience"),rs.getString("description"),rs.getString("exigence_offre"),rs.getDate("date_postu")));
                //LocalDateTime dt = list.getDate("date").toLocalDate;
                //System.out.println(list);
            }
//            for (OffreDeStage i : list){
//                System.out.println("DemandeStage{" +
//                        ", id=" + i.getPostePropose() +
//                        "nom='" + i.getTitle() + '\'' +
////                        ", prenom='" + i.getExigenceOffre() + '\'' +
//                        ", email='" + i.getExperience() + '\'' +
//                        ", cv='" + i.getDatePostu() + '\'' +
//                        '}');
//            }
        }catch (SQLException e){
            System.err.println("e.getMessage()");
        }
        return list;
    }

    @Override
    public void afficheUne(int id) throws SQLException {

    }
}
