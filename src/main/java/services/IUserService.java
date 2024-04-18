package services;

import Entities.User;

import java.sql.SQLException;

public interface IUserService {

    public void ajouter(User user) throws SQLException;

    public User getOneUser(String email) throws SQLException;

    public User getUserById(int email) throws SQLException;
}
