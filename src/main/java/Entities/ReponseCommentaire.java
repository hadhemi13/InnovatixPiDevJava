package Entities;

import java.time.LocalDateTime;

public class ReponseCommentaire {
    private Integer id;
    private String contenu_rep_com;
    private String nom_rep_com;
    private LocalDateTime date_rep_com;
    private Integer commentaire_id;
    private Integer user_id;

    public ReponseCommentaire() {
    }

    public ReponseCommentaire(Integer id, String contenu_rep_com, String nom_rep_com, LocalDateTime date_rep_com, Integer commentaire_id, Integer user_id) {
        this.id = id;
        this.contenu_rep_com = contenu_rep_com;
        this.nom_rep_com = nom_rep_com;
        this.date_rep_com = date_rep_com;
        this.commentaire_id = commentaire_id;
        this.user_id = user_id;
    }

    public ReponseCommentaire(Integer id, String contenu_rep_com, String nom_rep_com, LocalDateTime date_rep_com, Integer commentaire_id) {
        this.id = id;
        this.contenu_rep_com = contenu_rep_com;
        this.nom_rep_com = nom_rep_com;
        this.date_rep_com = date_rep_com;
        this.commentaire_id = commentaire_id;
    }

    public ReponseCommentaire(String contenu_rep_com, String nom_rep_com, LocalDateTime date_rep_com, Integer commentaire_id) {
        this.contenu_rep_com = contenu_rep_com;
        this.nom_rep_com = nom_rep_com;
        this.date_rep_com = date_rep_com;
        this.commentaire_id = commentaire_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu_rep_com() {
        return contenu_rep_com;
    }

    public void setContenu_rep_com(String contenu_rep_com) {
        this.contenu_rep_com = contenu_rep_com;
    }

    public String getNom_rep_com() {
        return nom_rep_com;
    }

    public void setNom_rep_com(String nom_rep_com) {
        this.nom_rep_com = nom_rep_com;
    }

    public LocalDateTime getDate_rep_com() {
        return date_rep_com;
    }

    public void setDate_rep_com(LocalDateTime date_rep_com) {
        this.date_rep_com = date_rep_com;
    }

    public Integer getCommentaire_id() {
        return commentaire_id;
    }

    public void setCommentaire_id(Integer commentaire_id) {
        this.commentaire_id = commentaire_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "ReponseCommentaire{" +
                "id=" + id +
                ", contenu_rep_com='" + contenu_rep_com + '\'' +
                ", nom_rep_com='" + nom_rep_com + '\'' +
                ", date_rep_com=" + date_rep_com +
                ", commentaire_id=" + commentaire_id +
                ", user_id=" + user_id +
                '}';
    }
}
