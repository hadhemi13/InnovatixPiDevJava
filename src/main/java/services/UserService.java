package services;

import Entities.User;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;

public class UserService implements IUserService {

    public Connection conx;
    public Statement stm;

    public UserService() {
        conx = MyDB.getInstance().getConx();
    }

    @Override
    public void ajouter(User user) throws SQLException {
        String req = "INSERT INTO `user`(`full_name`, `email`, `tel`, `token`,`img_url`, `roles`, `password`, `point`, `verification_code`) VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, user.getFullname());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getTel());
        ps.setString(4, user.getToken());
        ps.setString(5, user.getImgUrl());
        ps.setString(6, user.getRoles());
        ps.setString(7, user.getPassword());
        ps.setInt(8, user.getPoint());
        ps.setInt(9, user.getVerificationCode());
        ps.executeUpdate();
        System.out.println("User added successfully");
        ps.close();
    }

    public void update(User user) throws SQLException {
        String req = "UPDATE `user` SET `full_name`=?,`tel`=?,`token`=?,`is_verified`=?,`state`=?,`description`=?,`fb_link`=?,`twitter_link`=?,`insta_link`=?,`img_url`=?,`password`=?,`point`=?,`verification_code`=? WHERE email=?";

        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, user.getFullname());
        ps.setString(2, user.getTel());
        ps.setString(3, user.getToken());
        ps.setBoolean(4, user.getIsVerified());
        ps.setBoolean(5, user.getState());
        ps.setString(6, user.getDescription());
        ps.setString(7, user.getFbLink());
        ps.setString(8, user.getTwitterLink());
        ps.setString(9, user.getInstaLink());
        ps.setString(10, user.getImgUrl());
        ps.setString(11, user.getPassword());
        ps.setInt(12, user.getPoint());
        ps.setInt(13, user.getVerificationCode());
        ps.setString(14, user.getEmail());

        ps.executeUpdate();
        System.out.println("User updated successfully");
        ps.close();
    }

    public User getOneUser(String email) throws SQLException {
        String req = "SELECT * FROM `user` where email = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);

        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setFullname(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setTel(rs.getString("tel"));
            user.setToken(rs.getString("token"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setState(rs.getBoolean("state"));
            user.setDescription(rs.getString("description"));
            user.setFbLink(rs.getString("fb_link"));
            user.setTwitterLink(rs.getString("twitter_link"));
            user.setInstaLink(rs.getString("insta_link"));
            user.setImgUrl(rs.getString("img_url"));
            user.setRoles(rs.getString("roles"));
            user.setPassword(rs.getString("password"));
            user.setPoint(rs.getInt("point"));
            user.setVerificationCode(rs.getInt("verification_code"));
        }
        ps.close();
        return user;
    }

    public User getUserById(int id) throws SQLException {
        String req = "SELECT * FROM `user` where id = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        User user = new User();
        user.setId(-999);

        while (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setFullname(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setTel(rs.getString("tel"));
            user.setToken(rs.getString("token"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setState(rs.getBoolean("state"));
            user.setDescription(rs.getString("description"));
            user.setFbLink(rs.getString("fb_link"));
            user.setTwitterLink(rs.getString("twitter_link"));
            user.setInstaLink(rs.getString("insta_link"));
            user.setImgUrl(rs.getString("img_url"));
            user.setRoles(rs.getString("roles"));
            user.setPassword(rs.getString("password"));
            user.setPoint(rs.getInt("point"));
            user.setVerificationCode(rs.getInt("verification_code"));
        }
        ps.close();
        return user;
    }

    public ArrayList<User> getAllUser() throws SQLException {
        String req = "SELECT * FROM `user` where roles = ? or roles = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, "[\"ROLE_USER\"]");
        ps.setString(2, "[\"ROLE_ASSOCIATION\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setFullname(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setTel(rs.getString("tel"));
            user.setToken(rs.getString("token"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setState(rs.getBoolean("state"));
            user.setDescription(rs.getString("description"));
            user.setFbLink(rs.getString("fb_link"));
            user.setTwitterLink(rs.getString("twitter_link"));
            user.setInstaLink(rs.getString("insta_link"));
            user.setImgUrl(rs.getString("img_url"));
            user.setRoles(rs.getString("roles"));
            user.setPassword(rs.getString("password"));
            user.setPoint(rs.getInt("point"));
            user.setVerificationCode(rs.getInt("verification_code"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }

    public ArrayList<User> getAllIndiv() throws SQLException {
        String req = "SELECT * FROM `user` where roles = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, "[\"ROLE_USER\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setFullname(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setTel(rs.getString("tel"));
            user.setToken(rs.getString("token"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setState(rs.getBoolean("state"));
            user.setDescription(rs.getString("description"));
            user.setFbLink(rs.getString("fb_link"));
            user.setTwitterLink(rs.getString("twitter_link"));
            user.setInstaLink(rs.getString("insta_link"));
            user.setImgUrl(rs.getString("img_url"));
            user.setRoles(rs.getString("roles"));
            user.setPassword(rs.getString("password"));
            user.setPoint(rs.getInt("point"));
            user.setVerificationCode(rs.getInt("verification_code"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }

    public ArrayList<User> getAllAssoc() throws SQLException {
        String req = "SELECT * FROM `user` where roles = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, "[\"ROLE_ASSOCIATION\"]");

        ResultSet rs = ps.executeQuery();
        ArrayList<User> userList = new ArrayList<>();

        while (rs.next()) {
            User user = new User();

            user.setId(rs.getInt("id"));
            user.setFullname(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setTel(rs.getString("tel"));
            user.setToken(rs.getString("token"));
            user.setIsVerified(rs.getBoolean("is_verified"));
            user.setState(rs.getBoolean("state"));
            user.setDescription(rs.getString("description"));
            user.setFbLink(rs.getString("fb_link"));
            user.setTwitterLink(rs.getString("twitter_link"));
            user.setInstaLink(rs.getString("insta_link"));
            user.setImgUrl(rs.getString("img_url"));
            user.setRoles(rs.getString("roles"));
            user.setPassword(rs.getString("password"));
            user.setPoint(rs.getInt("point"));
            user.setVerificationCode(rs.getInt("verification_code"));

            userList.add(user);
        }
        ps.close();
        return userList;
    }

    public int getIndivNB() throws SQLException {
        String req = "SELECT * FROM `user` where roles = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, "[\"ROLE_USER\"]");
        // ps.setString(2, "[\"ROLE_ASSOCIATION\"]");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while (rs.next()) {
            count++;
        }
        ps.close();
        return count;
    }

    public int getAssociationNB() throws SQLException {
        String req = "SELECT * FROM `user` where roles = ?";
        PreparedStatement ps = conx.prepareStatement(req);
        ps.setString(1, "[\"ROLE_ASSOCIATION\"]");

        ResultSet rs = ps.executeQuery();
        int count = 0;

        while (rs.next()) {
            count++;
        }
        ps.close();
        return count;
    }

    public int getActiveNB() throws SQLException {
        String req = "SELECT * FROM `user` where state = ?";
        PreparedStatement ps = conx.prepareStatement(req);
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
        String req = "SELECT * FROM `user` where state = ?";
        PreparedStatement ps = conx.prepareStatement(req);
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
