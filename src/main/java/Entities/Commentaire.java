package Entities;

public class Commentaire {
  private int id;

  private String contenu;
  private String date;
  private String nomuser;
  private String img;

  private Evenement evenement;

  public Commentaire(int id, String contenu, String date, String nomuser, String img) {
    this.id = id;
    this.contenu = contenu;
    this.date = date;
    this.nomuser = nomuser;
    this.img = img;
  }

  public Commentaire(int id) {
    this.id = id;
  }
  public Commentaire() {

  }

  public Evenement getEvenement() {
    return evenement;
  }

  public void setEvenement(Evenement evenement) {
    this.evenement = evenement;
  }

  @Override
  public String toString() {
    return "Commentaire{" +
      "id=" + id +
      ", contenu='" + contenu + '\'' +
      ", date='" + date + '\'' +
      ", nomuser='" + nomuser + '\'' +
      ", img='" + img + '\'' +
      '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getContenu() {
    return contenu;
  }

  public void setContenu(String contenu) {
    this.contenu = contenu;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getNomuser() {
    return nomuser;
  }

  public void setNomuser(String nomuser) {
    this.nomuser = nomuser;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }
}
