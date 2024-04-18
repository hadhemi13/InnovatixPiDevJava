package utils;

public final class UserSession {

    private static UserSession instance;
    private String email;

    private UserSession() {

    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void cleanUserSession() {
        email = "";
    }

}