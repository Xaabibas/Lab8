package ProcessEngine.GraphicCore.SignWindow;

import ProcessEngine.GraphicCore.SignWindow.SignUpWindow.SignUpWindow;
import ProcessEngine.GraphicCore.SignWindow.SignInWindow.SignInWindow;

import javafx.stage.Stage;

public class SignWindow {

    Stage stage;
    protected AuthCheck authCheckData = new AuthCheck(false);
    
    public SignWindow(Stage stage) {
        this.stage = stage;
    }

    public void getAuth() {
        SignUpWindow.signUpWindow(stage);
        // while (!authCheckData.getAuthSuccess()) {

        // }
    }

    public class AuthCheck {
        private boolean authSuccess;
        private String login;
        private String password;

        public AuthCheck(boolean authSuccess) {
            this.authSuccess = authSuccess;
        }


        protected void setLogin(String login, String password) {
            this.login = login;
            this.password = password;
        }


        protected boolean getAuthSuccess() {
            return authSuccess;
        }

        protected String getLogin() {
            return login;
        }

        protected String getPassword() {
            return password;
        }
    }

}
