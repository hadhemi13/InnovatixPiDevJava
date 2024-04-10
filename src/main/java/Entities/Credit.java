package Entities;

import java.util.Date;

public class Credit {
    private int id;
    private int id_client;
    private double montant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public Date getDateDebut() {
        return datedebut;
    }

    public void setDateDebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Double getMensualite() {
        return mensualite;
    }

    public void setMensualite(double mensualite) {
        this.mensualite = mensualite;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getFraisRetard() {
        return fraisretard;
    }

    public void setFraisRetard(double fraisretard) {
        this.fraisretard = fraisretard;
    }

    private double taux;
    private Date datedebut;
    private double mensualite;
    private int duree; // Durée en mois
    private double fraisretard;

}
