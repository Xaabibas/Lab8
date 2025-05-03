import managers.*;
import network.Request;

import network.Response;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public final class Server extends Thread {
    public static final Logger logger = Logger.getLogger("ServerLogger");
    private final RequestManager requestManager;
    private final ResponseManager responseManager;
    private final NetworkManager networkManager;
    private final CommandManager commandManager;
    private final ExecutorService readPool = Executors.newCachedThreadPool();

    public Server(RequestManager requestManager, ResponseManager responseManager,
                  NetworkManager networkManager, CommandManager commandManager) {
        this.requestManager = requestManager;
        this.responseManager = responseManager;
        this.networkManager = networkManager;
        this.commandManager = commandManager;

    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = networkManager.connectToClient();
                readPool.submit(() -> {
                    Request request;
                    Response response;

                    request = requestManager.readRequest(client);
                    logger.info("The request from the user was successfully received");

                    response = commandManager.processRequest(request); // Обрабатываем запрос, формируем ответ
                    logger.info("The request was successfully processed and a response was generated");

                    responseManager.sendToClient(response, client);

                    try {
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (NullPointerException e) {
                logger.warning("Couldn't process the request");
            }
        }
    }
}