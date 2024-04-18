package Entities;

public class User {

    private int id;
    private String fullname;
    private String email;
    private String password;
    private String tel;
    private String token;
    private Boolean isVerified;
    private Boolean state;
    private String description;
    private String fbLink;
    private String twitterLink;
    private String instaLink;
    private String imgUrl;
    private int point;
    private int verificationCode;
    private String roles;

    public User() {
    }

    public User(String fullname, String email, String tel) {
        this.fullname = fullname;
        this.email = email;
        this.tel = tel;
    }

    public User(String fullname, String email, String tel, String token, String imgUrl, String roles, String password,
                int point) {
        this.fullname = fullname;
        this.email = email;
        this.tel = tel;
        this.token = token;
        this.imgUrl = imgUrl;
        this.roles = roles;
        this.password = password;
        this.point = point;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public void setInstaLink(String instaLink) {
        this.instaLink = instaLink;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getVerificationCode() {
        return this.verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getToken() {
        return token;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public Boolean getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public String getFbLink() {
        return fbLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public String getInstaLink() {
        return instaLink;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getPoint() {
        return point;
    }

    public String getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fullname=" + fullname + ", email=" + email + ", password=" + password
                + ", tel=" + tel + ", token=" + token + ", isVerified=" + isVerified + ", state=" + state
                + ", description=" + description + ", fbLink=" + fbLink + ", twitterLink=" + twitterLink
                + ", instaLink=" + instaLink + ", imgUrl=" + imgUrl + ", point=" + point + ", roles=" + roles
                + ", code_verification=" + verificationCode + '}';
    }

}
