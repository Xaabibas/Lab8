import ProcessEngine.GraphicCore.GraphicRun;
import ProcessEngine.ProcessCore.ProcessRun;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    protected static final int port = 46789;
    protected static final String host = "localhost";

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            NetworkManager networkManager = new NetworkManager(port, InetAddress.getByName(host));
            ProcessRun runner = new ProcessRun(scanner, networkManager);

            new Thread(runner::interactiveMode).start();

            startGraphicWindow(networkManager);

        } catch (UnknownHostException e) {
            System.out.println("[ERROR] Неизвестный хост");
            System.exit(1);
        }
    }

    private static void startGraphicWindow(NetworkManager networkManager) {
        GraphicRun.main(networkManager);
    }

}
