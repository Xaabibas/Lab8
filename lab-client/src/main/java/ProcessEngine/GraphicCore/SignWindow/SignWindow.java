package ProcessEngine.GraphicCore.SignWindow;

import resources.Localizator;
import ProcessEngine.DataCore.AuthCheck;
import ProcessEngine.GraphicCore.SignWindow.SignUpWindow.SignUpWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.stage.Stage;
import network.Request;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignWindow {

    protected Stage stage;
    protected AuthCheck authCheckData;
    protected static NetworkManager netManager;


    public SignWindow(Stage stage, AuthCheck authCheckData, NetworkManager networkManager) {
        this.stage = stage;
        this.authCheckData = authCheckData;
        netManager = networkManager;
    }

    public void getAuth() {
        SignUpWindow.signUpWindow(stage, authCheckData);
        System.out.println(">> Запущено окно авторизации");
    }

    public static boolean checkAuthInfo(String login, String password, String typeRequestLogPass) {
        Request requestPack = new Request();
        requestPack.setUser(login);
        requestPack.setPassword(Arrays.toString(password
                .chars()
                .mapToObj(c -> String.valueOf((char) c))
                .toArray(String[]::new))
        );
        requestPack.setCommandName(typeRequestLogPass);
        requestPack.setTokens(typeRequestLogPass);

        String networkResponse = netManager.sendAndReceive(requestPack);

        return (networkResponse.trim().equals("Успешная авторизация")) || (networkResponse.trim().equals("Успешная регистрация"));
    }

}
