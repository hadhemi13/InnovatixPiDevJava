package services;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public interface ICredit <T> {
    void initialize(URL url, ResourceBundle resourceBundle);

    void savecredit(T t) throws SQLException;



}
