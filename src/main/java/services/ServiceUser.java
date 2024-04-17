package services;
import Entities.User;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ServiceUser  implements Iservice<User>{
    static Connection connection;
    public ServiceUser(){
        connection=MyDatabase.getInstance().getConnection();
    }

    @Override
    public  void ajouter(User user) throws SQLException {
        String req = "INSERT INTO user (email, name,roles ,password,cin, date_naissance, adresse,profession ,photo,is_blocked,is_verified, poste, salaire, tel) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? ,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
          //  preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setArray(3, connection.createArrayOf("VARCHAR", user.getRoles().toArray()));
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getCin());
            preparedStatement.setString(6, user.getDate_naissance());
            preparedStatement.setString(7, user.getPhoto());
            preparedStatement.setString(8, user.getAdresse());
            preparedStatement.setString(9, user.getProfession());
            preparedStatement.setInt(10, user.getIs_blocked());
            preparedStatement.setInt(11, user.getIs_verified());
            preparedStatement.setInt(12, user.getSalaire());
            preparedStatement.setString(13, user.getPoste());
            preparedStatement.setString(14, user.getTel());



            preparedStatement.executeUpdate();
            System.out.println("Utilisateur  ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }



    @Override
    public void modifier(User user) throws SQLException {
        String req="update user set name=?,email=?,adresse=?,cin=?,date_naissance=?,profession=?  where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getAdresse());
            preparedStatement.setString(3, user.getCin());
            preparedStatement.setString(3, user.getProfession());
            preparedStatement.setString(3, user.getDate_naissance());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("modifie");
        }
        catch (SQLException e){
            System.out.println("Erreur lors de la modification  : " + e.getMessage());
        }


    }

    @Override
    public void supprimer(User user) throws SQLException {
        try {
            String req = "DELETE FROM user WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();
            System.out.println("utilisateur  supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression  : " + ex.getMessage());
        }

    }

    @Override
    public List<User> afficher() throws SQLException {
        List<User>  users= new ArrayList<>();
        String req="select * from user";
        Statement statement= connection.createStatement();

        ResultSet rs= statement.executeQuery(req);
        while (rs.next()){
            User user= new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("CIN"));
            user.setDate_naissance(rs.getString("date_naissance"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("role"));
            user.setPhoto(rs.getString("photo"));


            users.add(user);
        }


        return users;

    }

    @Override
    public User getOneUser(String email) throws SQLException {
        String req = "SELECT * FROM `user` where email = ?";
        PreparedStatement ps =connection.prepareStatement(req);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getString("date_naissance"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));
            user.setIs_blocked(rs.getInt("is_blocked"));
            user.setIs_verified(rs.getInt("is_verified"));
        }
        ps.close();
        return user;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        String req = "SELECT * FROM `user` where id = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setInt(1,id );

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);
        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("cin"));
            user.setDate_naissance(rs.getString("date_naissance"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));
            user.setIs_blocked(rs.getInt("is_blocked"));
            user.setIs_verified(rs.getInt("is_verified"));
        }
        ps.close();
        return user;


    }



    }

