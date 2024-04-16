package services;

import java.sql.SQLException;
import java.util.List;

import Entities.User;

public interface Iservice <T> {
    public void ajouter(T t) throws SQLException;
    public void modifier(T t) throws SQLException;
    public void supprimer(T t) throws SQLException;
    public List<T> afficher() throws SQLException;

    public User getOneUser(String email) throws SQLException;

    public User getUserById(int id) throws SQLException;
}

