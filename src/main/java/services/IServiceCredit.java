package services;

import Entities.Cheque;
import Entities.Credit;

import java.sql.SQLException;
import java.util.List;

public interface IServiceCredit<T> {
    void ajouter(Cheque cheque) throws SQLException;

    void modifier(Cheque cheque) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<Credit> afficher() throws SQLException;
}
