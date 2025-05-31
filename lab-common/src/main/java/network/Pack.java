package network;

import java.net.Socket;

public class Pack {
    private final Request request;
    private final Socket client;

    public Pack(Request request, Socket client) {
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
