package ProcessEngine.DataCore;

public class AuthCheck {
    private boolean authSuccess;
    private String login;
    private String password;

    public AuthCheck() {
    }

    public void setAuthSuccess(boolean authSuccess) {
        this.authSuccess = authSuccess;
    }

    public void setLoginPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public boolean getAuthSuccess() {
        return authSuccess;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
