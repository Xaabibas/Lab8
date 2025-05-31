package managers;

import network.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class RequestManager {

    public Request readRequest(Socket client) {
        try {
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            return (Request) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
