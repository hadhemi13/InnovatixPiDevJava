package services;///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Service;
//
//import Utils.DataSource;
//
//import java.sql.*;
//import java.util.List;
//
///**
// * @author Ahmed Abdallah
// */
//public class ServiceUser implements IService<User> {
//
//
//    @Override
//    public void ajouter(User user) throws SQLException {
//            try{
//                PreparedStatement pre=DataSource.getInstance().getCon().prepareStatement("INSERT INTO `user`(`id`, `Type`, `nom`, `prenom`, `sexe`, `email`, `date_nais`, `pass`) VALUES (?,?,?,?,?,?,?,?)");
//                pre.setInt(1, user.getId());
//                pre.setString(2, user.getType());
//                pre.setString(3, user.getNom());
//                pre.setString(4, user.getPrenom());
//                pre.setString(5, user.getSexe());
//                pre.setString(6, user.getEmail());
//                pre.setDate(7, (Date) user.getDateNaissance());
//                pre.setString(8, user.getPassword());
//                pre.executeUpdate();
//                System.out.println("User ajouté");
//            }catch (SQLException ex){
//               ex.printStackTrace();
//            }
//    }
//
//  @Override
//  public void ajouter1(User user, int projectId) throws SQLException {
//
//  }
//
//    @Override
//    public void modifier(User user) throws SQLException {
//            try{
//                PreparedStatement pre=DataSource.getInstance().getCon().prepareStatement("UPDATE `user` SET `Type`=?,`nom`=?,`prenom`=?,`sexe`=?,`email`=?,`date_nais`=?,`pass`=? WHERE `id`=?");
//                pre.setString(1, user.getType());
//                pre.setString(2, user.getNom());
//                pre.setString(3, user.getPrenom());
//                pre.setString(4, user.getSexe());
//                pre.setString(5, user.getEmail());
//                pre.setDate(6, (Date) user.getDateNaissance());
//                pre.setString(7, user.getPassword());
//                pre.setInt(8, user.getId());
//                pre.executeUpdate();
//                System.out.println("User modifié");
//            }catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//    }
//
//
//
//  @Override
//  public void supprimer(int id) throws SQLException {
//
//  }
//
//  @Override
//  public List<User> afficher() throws SQLException {
//    return null;
//  }
//
//}
