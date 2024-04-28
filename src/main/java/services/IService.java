/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Entities.Evenement;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sanabenfadhel
 */
public interface IService<T> {

    void ajouter(T t) throws SQLException;
  void ajouter1(T t,int projectId) throws SQLException;

    void modifier (T t) throws SQLException;

    public Evenement getOneEvenement(int idEvenement) throws SQLException;


  public void supprimer(int id) throws SQLException;
  public List<T> afficher() throws SQLException;

}
