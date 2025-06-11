package ProcessEngine.GraphicCore;

import ProcessEngine.DataCore.AuthCheck;
import ProcessEngine.GraphicCore.MainWindow.MainWindow;
import ProcessEngine.GraphicCore.SignWindow.SignWindow;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.concurrent.Task;

public class GraphicRun extends Application  {

    protected Stage stage;
    protected volatile AuthCheck authCheckData = new AuthCheck();

    public static void main() {
        Application.launch(GraphicRun.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        SignWindow startSignWindow = new SignWindow(stage, authCheckData);

        startSignWindow.getAuth();

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
            runMainWindow();
        });

        new Thread(authTask).start();
    }

    protected void runMainWindow() {
        System.out.println(">> Запущено главное окно");
        new MainWindow().window(stage);
        // вся остальная логика и запуск главного окна
    }

    protected void getCollectionData() {

    }

}
