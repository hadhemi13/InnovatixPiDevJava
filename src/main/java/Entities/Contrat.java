package Entities;

import java.sql.Date;
import java.util.Objects;

public class Contrat {
    int id,id_user;
    Date date_Debut,datefin;
    String dure;

    public Contrat(){

    }
    public Contrat(int id, int id_user, Date date_Debut, Date datefin, String dure) {
        this.id = id;
        this.id_user = id_user;
        this.date_Debut = date_Debut;
        this.datefin = datefin;
        this.dure = dure;
    }

    public Contrat(int id_user, Date date_Debut, Date datefin, String dure) {
        this.id_user = id_user;
        this.date_Debut = date_Debut;
        this.datefin = datefin;
        this.dure = dure;
    }

    @Override
    public String toString() {
        return "Contrat{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", date_Debut=" + date_Debut +
                ", datefin=" + datefin +
                ", dure='" + dure + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrat contrat = (Contrat) o;
        return id == contrat.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
