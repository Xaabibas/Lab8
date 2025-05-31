import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import ProcessEngine.ProcessRun;
import ProcessEngine.ProcessCore.networkModule.NetworkManager;

public final class Client {
    private final int port;
    private final String host;

    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void run() {
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
