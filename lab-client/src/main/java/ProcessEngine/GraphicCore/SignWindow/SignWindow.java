package ProcessEngine.GraphicCore.SignWindow;

import ProcessEngine.GraphicCore.SignWindow.SignUpWindow.SignUpWindow;
import ProcessEngine.DataCore.AuthCheck;

import javafx.stage.Stage;

public class SignWindow {

    protected Stage stage;
    protected AuthCheck authCheckData;
    
    public SignWindow(Stage stage, AuthCheck authCheckData) {
        this.stage = stage;
        this.authCheckData = authCheckData;
    }

    public void getAuth() {
        SignUpWindow.signUpWindow(stage, authCheckData);
        System.out.println(">> Запущено окно авторизации");
    }

    public static boolean checkAuthInfo(String login, String password) {
        return true;
    }

}
