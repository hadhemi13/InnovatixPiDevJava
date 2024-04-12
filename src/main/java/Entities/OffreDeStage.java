package Entities;

import java.sql.Array;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class OffreDeStage {
    int id,postePropose;
    int  user_id;
    String title,domaine,typeOffre,experience,description,exigenceOffre,pfeBook;
    Date datePostu;
    List niveau,language,motsCles;

    public OffreDeStage() {}

    public OffreDeStage(int id, int postePropose, int user_id, String title, String domaine, String typeOffre, String experience, String description, String exigenceOffre, String pfeBook, Date datePostu, List niveau, List language, List motsCles) {
        this.id = id;
        this.postePropose = postePropose;
        this.user_id = user_id;
        this.title = title;
        this.domaine = domaine;
        this.typeOffre = typeOffre;
        this.experience = experience;
        this.description = description;
        this.exigenceOffre = exigenceOffre;
        this.pfeBook = pfeBook;
        this.datePostu = datePostu;
        this.niveau = niveau;
        this.language = language;
        this.motsCles = motsCles;
    }

    public OffreDeStage(int id, int postePropose, String title, String domaine, String typeOffre, String experience, String description, String exigenceOffre, Date datePostu, List niveau, List language, List motsCles) {
        this.id = id;
        this.postePropose = postePropose;
        this.title = title;
        this.domaine = domaine;
        this.typeOffre = typeOffre;
        this.experience = experience;
        this.description = description;
        this.exigenceOffre = exigenceOffre;
        this.datePostu = datePostu;
        this.niveau = niveau;
        this.language = language;
        this.motsCles = motsCles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostePropose() {
        return postePropose;
    }

    public void setPostePropose(int postePropose) {
        this.postePropose = postePropose;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getTypeOffre() {
        return typeOffre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExigenceOffre() {
        return exigenceOffre;
    }

    public void setExigenceOffre(String exigenceOffre) {
        this.exigenceOffre = exigenceOffre;
    }

    public String getPfeBook() {
        return pfeBook;
    }

    public void setPfeBook(String pfeBook) {
        this.pfeBook = pfeBook;
    }

    public Date getDatePostu() {
        return datePostu;
    }

    public void setDatePostu(Date datePostu) {
        this.datePostu = datePostu;
    }

    public List getNiveau() {
        return niveau;
    }

    public void setNiveau(List niveau) {
        this.niveau = niveau;
    }

    public List getLanguage() {
        return language;
    }

    public void setLanguage(List language) {
        this.language = language;
    }

    public List getMotsCles() {
        return motsCles;
    }

    public void setMotsCles(List motsCles) {
        this.motsCles = motsCles;
    }

    @Override
    public String toString() {
        return "OffreDeStage{" +
                "id=" + id +
                ", postePropose=" + postePropose +
                ", user_id=" + user_id +
                ", title='" + title + '\'' +
                ", domaine='" + domaine + '\'' +
                ", typeOffre='" + typeOffre + '\'' +
                ", experience='" + experience + '\'' +
                ", description='" + description + '\'' +
                ", exigenceOffre='" + exigenceOffre + '\'' +
                ", pfeBook='" + pfeBook + '\'' +
                ", datePostu=" + datePostu +
                ", niveau=" + niveau +
                ", language=" + language +
                ", motsCles=" + motsCles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OffreDeStage that = (OffreDeStage) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
