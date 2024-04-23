package services;

import Entities.Cheque;
import Entities.Credit;
import Entities.RDV;

import java.sql.SQLException;
import java.util.List;

public interface IServiceRdv<T> {
    void ajouter(Cheque cheque) throws SQLException;

    void modifier(Cheque cheque) throws SQLException;

    void supprimer(int id) throws SQLException;

    List<RDV> afficher() throws SQLException;
}
