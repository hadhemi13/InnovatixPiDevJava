package Entities;

import java.sql.Date;

public class DemandeStage {
    String nom,prenom,email,lettremotivation,cv,domaine,etat;
    int id,numeroTelephone,score,id_offre;
    Date date;
    public DemandeStage(){}

    public DemandeStage(String nom, String prenom, String email, String lettremotivation, String cv, String domaine, String etat, int id, int numeroTelephone, int score, int id_offre, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.id = id;
        this.numeroTelephone = numeroTelephone;
        this.score = score;
        this.id_offre = id_offre;
        this.date = date;
    }

    public DemandeStage(String nom, String prenom, String email, String lettremotivation, String cv, String domaine, String etat, int numeroTelephone, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.numeroTelephone = numeroTelephone;
        this.date = date;
    }

    public DemandeStage(String nom, String prenom, String email, String lettremotivation, String cv, String domaine, String etat, int numeroTelephone, int score, int id_offre, Date date) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.lettremotivation = lettremotivation;
        this.cv = cv;
        this.domaine = domaine;
        this.etat = etat;
        this.numeroTelephone = numeroTelephone;
        this.score = score;
        this.id_offre = id_offre;
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLettremotivation() {
        return lettremotivation;
    }

    public void setLettremotivation(String lettremotivation) {
        this.lettremotivation = lettremotivation;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(int numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
