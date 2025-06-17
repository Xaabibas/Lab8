package ProcessEngine.GraphicCore;

import ProcessEngine.DataCore.AuthCheck;
import ProcessEngine.GraphicCore.MainWindow.MainWindow;
import ProcessEngine.GraphicCore.SignWindow.SignWindow;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import resources.Localizator;

import java.util.Locale;
import java.util.ResourceBundle;

public class GraphicRun extends Application {

    protected Stage stage;
    protected volatile AuthCheck authCheckData = new AuthCheck();
    protected static NetworkManager netManager;
    public static Localizator localizator = new Localizator(ResourceBundle.getBundle("resources.Resource", new Locale("ru")));

    public static void main(NetworkManager networkManager) {
        netManager = networkManager;
        Application.launch(GraphicRun.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        SignWindow startSignWindow = new SignWindow(stage, authCheckData, netManager);
        startSignWindow.getAuth();

        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        new Thread(runSpecialThreadTask(authCheckData, stage)).start();
    }

    public static Task<Void> runSpecialThreadTask(AuthCheck authCheckData, Stage stage) {
        Task<Void> authTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                System.out.println(">> Запущен спец поток");
                while (!authCheckData.getAuthSuccess()) {
                    Thread.sleep(1);
                }
                return null;
            }
        };

        authTask.setOnSucceeded(event -> {
            System.out.println(">> Авторизация пройдена");
            runMainWindow(authCheckData, stage);
        });

        return authTask;
    }

    protected static void runMainWindow(AuthCheck authCheckData, Stage stage) {
        System.out.println(">> Запущено главное окно");
        new MainWindow(authCheckData, netManager, stage).window();
    }

}
