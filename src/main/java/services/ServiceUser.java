package services;
import Entities.User;
import utils.MyDatabase;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser  implements Iservice<User>{
    static Connection connection;
    public ServiceUser(){
        connection=MyDatabase.getInstance().getConnection();
    }

    @Override
    public void ajouter(User user) throws SQLException {
        String req = "INSERT INTO user (email, name, roles, password, cin, date_naissance, adresse, profession, photo, is_blocked, is_verified, poste, salaire, tel) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String y = "ROLE_CLIENT";
        String a = "[\"" + y + "\"]";
        System.out.println(a);
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            String[] rolesArray = user.getRoles().split(","); // Supposons que les rôles sont séparés par des virgules
            preparedStatement.setString(3, "[\"" + user.getRoles() + "\"]"); // Définir le tableau JDBC dans la colonne roles
            String salt = BCrypt.gensalt(); // Générer un sel avec la version par défaut de BCrypt
            String hashedPassword = BCrypt.hashpw(user.getPassword(), salt); // Hacher le mot de passe avec le sel généré
            preparedStatement.setString(4, hashedPassword);
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
            System.out.println("Utilisateur ajouté");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e);
        }
    }



    @Override
    public void modifier(User user) throws SQLException {
        String req="update user set name=?,email=?,adresse=?,cin=?,date_naissance=?,profession=?,photo=?, is_blocked=?,tel=? where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getAdresse());
            preparedStatement.setString(4, user.getCin());
            preparedStatement.setString(5, user.getProfession());
            preparedStatement.setString(6, user.getDate_naissance());
            preparedStatement.setString(7, user.getPhoto());
            preparedStatement.setInt(8,user.getIs_blocked());

            preparedStatement.setString(9,user.getTel());
            preparedStatement.setInt(10, 12);
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
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));


            users.add(user);
        }
        for (User i : users ){

            System.out.println(i.getRoles());
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
    public ArrayList<User> getAllUser() throws SQLException {
        String req = "SELECT * FROM `user` WHERE roles = ? OR roles = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "[\"ROLE_CLIENT\"]");
        ps.setString(2, "[\"ROLE_ADMIN\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("CIN"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }
    public ArrayList<User> getAllClient() throws SQLException {
        String req = "SELECT * FROM `user` WHERE roles = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "[\"ROLE_CLIENT\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("CIN"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }

    public ArrayList<User> getAllAdmin() throws SQLException {
        String req = "SELECT * FROM `user` WHERE roles = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "[\"ROLE_Admin\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setTel(rs.getString("tel"));
            user.setCin(rs.getString("CIN"));
            user.setEmail(rs.getString("email"));
            user.setAdresse(rs.getString("adresse"));
            user.setProfession(rs.getString("profession"));
            user.setRoles(rs.getString("roles"));
            user.setPhoto(rs.getString("photo"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }
    @Override
    public void signUp(User user) {
        // Assurez-vous que l'email n'est pas déjà utilisé
        if (!isEmailUsed(user.getEmail())) {
            // Définir le rôle par défaut sur ROLE_CLIENT
            user.setRoles("ROLE_CLIENT");

            // Ajouter l'utilisateur en utilisant votre méthode add existante
            try {
                ajouter(user); // Méthode pour ajouter un utilisateur
                System.out.println("User signed up successfully.");

            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Email is already used.");
        }}

    @Override
    public boolean isEmailUsed(String email) {
        // Vérifiez si l'email est déjà utilisé en consultant votre base de données
        // Vous devrez remplacer cette partie par l'accès réel à votre base de données

        // Par exemple, vous pouvez supposer qu'il y a une liste d'utilisateurs déjà enregistrés
        List<User> userList = getUsersFromDatabase(); // Méthode hypothétique pour obtenir la liste des utilisateurs depuis la base de données

        // Parcourez la liste des utilisateurs pour vérifier si l'email est déjà utilisé
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                // L'email est déjà utilisé
                return true;
            }
        }

        // Si l'email n'est pas trouvé dans la liste des utilisateurs, il n'est pas utilisé
        return false;
    }

    // Méthode fictive pour récupérer la liste des utilisateurs depuis la base de données
    private List<User> getUsersFromDatabase() {
        // Ici, vous devriez écrire le code pour récupérer les utilisateurs depuis votre base de données
        // et renvoyer la liste des utilisateurs
        // Par exemple :
        // return userDao.getAllUsers(); // userDao est une instance de votre classe DAO pour gérer les utilisateurs
        return new ArrayList<>(); // Pour l'exemple, nous retournons une liste vide
    }
    public int getActiveNB() throws SQLException {
        String req = "SELECT * FROM `user` where is_blocked = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "1");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while (rs.next()) {
            count++;
        }
        ps.close();
        return count;
    }
    public int getunActiveNB() throws SQLException {
        String req = "SELECT * FROM `user` where is_blocked = ?";
        PreparedStatement ps = connection.prepareStatement(req);
        ps.setString(1, "0");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while (rs.next()) {
            count++;
        }
        ps.close();
        return count;
    }

}







