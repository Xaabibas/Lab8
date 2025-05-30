import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import ProcessEngine.ProcessRun;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;

public class Main {

    protected static final int port = 46789;
    protected static final String host = "localhost";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            NetworkManager networkManager = new NetworkManager(port, InetAddress.getByName(host));
            ProcessRun runner = new ProcessRun(scanner, networkManager);

            runner.interactiveMode();
        } catch (UnknownHostException e) {
            System.out.println("[ERROR] Неизвестный хост");
        }
    }
}
