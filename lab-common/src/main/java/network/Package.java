package network;

import java.net.Socket;

public class Package {
    private final Request request;
    private final Socket client;

    public Package(Request request, Socket client) {
        this.request = request;
        this.client = client;
    }

    public Socket getClient() {
        return client;
    }

    public Request getRequest() {
        return request;
    }
}
