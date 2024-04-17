package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User {
    int id , salaire;
    String name,password,tel,email,adresse,profession,cin,photo, poste;
    String roles;
    String date_naissance;
    private int is_verified=0;

    public User(String name, String password, String tel, String email) {
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.email = email;
    }

    private int is_blocked=0;

    //update user set name=?,email=?,adresse=?,cin=?,date_naissance=?,profession=?  where id=?

    public User(int id, String name, String email, String adresse, String profession, String cin, String date_naissance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adresse = adresse;
        this.profession = profession;
        this.cin = cin;
        this.date_naissance = date_naissance;
    }



    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public int getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(int is_blocked) {
        this.is_blocked = is_blocked;
    }

    public User() {

    }

    public User(int id, String name, String password, String tel, String email, String adresse, String profession, String roles, String cin, String photo,String date_naissance , int is_blocked,int is_verified, String poste , int salaire) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.adresse = adresse;
        this.profession = profession;
        this.roles = roles;
        this.cin = cin;
        this.photo = photo;
        this.date_naissance= date_naissance;
        this.is_blocked= is_blocked;
        this.is_blocked=is_blocked;
        this.poste= poste;
        this.salaire= salaire;
    }
    public User(String name, String password, String tel, String email, String adresse, String profession, String roles, String cin, String photo,String date_naissance , int is_blocked,int is_verified, String poste , int salaire) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.adresse = adresse;
        this.profession = profession;
        this.roles = roles;
        this.cin = cin;
        this.photo = photo;
        this.date_naissance= date_naissance;
        this.is_blocked= is_blocked;
        this.is_blocked=is_blocked;
        this.poste= poste;
        this.salaire= salaire;
    }


    //email, name,roles ,password,cin, date_naissance, adresse,profession ,photo,is_blocked,is_verified, poste, salaire, tel

    public User(String name, String password, String tel, String email, String adresse, String profession, String roles, String cin, String photo) {
        this.name = name;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.adresse = adresse;
        this.profession = profession;
        this.roles = roles;
        this.cin = cin;
        this.photo = photo;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getProfession() {
        return profession;
    }

    public String getRoles() {
        return roles;
    }

    public String getCin() {
        return cin;
    }

    public String getPhoto() {
        return photo;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(tel, user.tel) && Objects.equals(email, user.email) && Objects.equals(adresse, user.adresse) && Objects.equals(profession, user.profession) && Objects.equals(roles, user.roles) && Objects.equals(cin, user.cin) && Objects.equals(photo, user.photo) && Objects.equals(date_naissance, user.date_naissance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, tel, email, adresse, profession, roles, cin, photo, date_naissance);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", profession='" + profession + '\'' +
                ", roles='" + roles + '\'' +
                ", cin='" + cin + '\'' +
                ", photo='" + photo + '\'' +
                ", date_naissance=" + date_naissance+
                '}';
    }

}
