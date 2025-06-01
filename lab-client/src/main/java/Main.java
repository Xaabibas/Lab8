import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import ProcessEngine.GraphicCore.StartWindow;
import ProcessEngine.ProcessRun;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    protected static final int port = 46789;
    protected static final String host = "localhost";

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            NetworkManager networkManager = new NetworkManager(port, InetAddress.getByName(host));
            ProcessRun runner = new ProcessRun(scanner, networkManager);
            new Thread(runner::interactiveMode).start();
        } catch (UnknownHostException e) {
            System.out.println("[ERROR] Неизвестный хост");
            System.exit(1);
        }
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        new StartWindow().registerWindow(stage);
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }
}
